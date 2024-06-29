package com.fabiansebastianj1.tripcrew.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.employee.domain.models.Employee;
import com.fabiansebastianj1.trip.domain.models.Trip;
import com.fabiansebastianj1.tripcrew.application.TripCrewService;
import com.fabiansebastianj1.tripcrew.domain.models.TripCrew;
import com.fabiansebastianj1.tripcrew.domain.models.TripCrewDTO;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.Register;
import com.fabiansebastianj1.validations.ValidationExist;

public class TripCrewConsoleAdapter {

    private final TripCrewService tripCrewService;

    public TripCrewConsoleAdapter(TripCrewService tripCrewService) {
        this.tripCrewService = tripCrewService;
    }

    // Método principal que inicia la interfaz de consola para gestionar
    // tripulaciones
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("*** Modulo de tripulantes ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            int choice = inputVali
                    .readInt("1. Asignar Tripulación \n2. Consultar asignación de tripulación \n3. Salir");
            System.out.println(" ");

            switch (choice) {
                case 1:
                    System.out.println("*** Asignar Tripulación ***");
                    System.out.println("*** Lista de trayectos disponibles ***");
                    mostrarVuelos();
                    // Consulto que el id connection exista en la tabla connection
                    Connections showConnections = ValidationExist.transformAndValidateObj(
                            () -> tripCrewService.findConnectionById(
                                    inputVali.readInt(
                                            ("Ingrese la id_escala mostrada para el vuelo al que desea asignar la tripulación"))));
                    int idConnection = showConnections.getId();

                    boolean employeesExists = verificarEmpleados();
                    if (employeesExists) {
                        registrarEmpleados(idConnection, scanner, inputVali);
                        System.out.println("Tripulantes asignados exitosamente");
                    } else {
                        System.out.println("No hay empleados por asignar");
                        break;
                    }
                    break;

                case 2:
                    System.out.println("***Consulta de tripulantes***");

                    // Consulto el tripId y extraigo el tripId
                    Trip searchedTrip = ValidationExist.transformAndValidateObj(
                            () -> tripCrewService.findTripById(inputVali.readInt("Ingrese el id del vuelo")));

                    // Consulto el connectionId y extraigo el connectionId si existe
                    Optional<ConnectionDTO> searchedTripAsConnection = tripCrewService
                            .findTripAsConnectionByTripId(searchedTrip.getId());

                    if (searchedTripAsConnection.isEmpty()) {
                        System.out.println("No se cuenta con tripulantes asignados a este vuelo");
                    } else {
                        // Si encontramos un connectionId válido, mostramos la tripulación
                        showTripCrew(searchedTripAsConnection.get().getConnectionId());
                    }
                    break;

                case 3:
                    System.out.println("Saliendo del modulo de tripulantes");
                    executing = false;
                    break;

                default:
                    System.out.println("Ingrese una opción válida");
                    break;
            }
        }
    }

    public void startMenuCliente() {

        InputVali inputVali = new InputVali();

        System.out.println("\n***Consulta de tripulantes***");

        // Consulto el tripId y extraigo el tripId
        Trip searchedTrip = ValidationExist.transformAndValidateObj(
                () -> tripCrewService.findTripById(inputVali.readInt("Ingrese el id del vuelo")));

        // Consulto el connectionId y extraigo el connectionId si existe
        Optional<ConnectionDTO> searchedTripAsConnection = tripCrewService
                .findTripAsConnectionByTripId(searchedTrip.getId());

        if (searchedTripAsConnection.isEmpty()) {
            System.out.println("No se cuenta con tripulantes asignados a este vuelo");
        } else {
            // Si encontramos un connectionId válido, mostramos la tripulación
            showTripCrew(searchedTripAsConnection.get().getConnectionId());
        }
    }

    // Método para mostrar la lista de tripulantes de un vuelo específico
    public void showTripCrew(int id) {
        List<TripCrewDTO> tripCrewList = tripCrewService.listTripCrewDTOByConnectionId(id);
        for (TripCrewDTO tripCrewDTO : tripCrewList) {
            System.out.println(String.format("id_employee: %s, name: %s, role: %s", tripCrewDTO.getEmployeeId(),
                    tripCrewDTO.getEmployeeName(), tripCrewDTO.getEmployeeRole()));
        }
    }

    // Método para registrar empleados en un vuelo específico
    public void registrarEmpleados(int idConnection, Scanner scanner, InputVali inputVali) {
        mostrarEmpleados();
        boolean continueAdd = true;

        while (continueAdd) {
            Employee showEmployee = ValidationExist.transformAndValidateObj(
                    () -> tripCrewService.findEmployeeById(inputVali.stringNotNull("ingrese la id del empleado")));
            String employeeId = showEmployee.getId();
            TripCrew tripCrew = new TripCrew(employeeId, idConnection);
            tripCrewService.createTripCrew(tripCrew);
            continueAdd = Register.yesOrNo(
                    "Desea hacer un nuevo registro? Ingrese el valor numérico: 1 (Si) o 2(no)");
        }
    }

    // Método para mostrar la lista de empleados disponibles
    public void mostrarEmpleados() {
        System.out.println("Lista de empleados");
        List<Employee> employees = tripCrewService.listEmployees();
        for (Employee employee : employees) {
            System.out.println(String.format("id: %s, name: %s", employee.getId(), employee.getName()));
        }
    }

    // Método para verificar si existen empleados disponibles para asignar
    public boolean verificarEmpleados() {
        boolean employeesExists = true;
        List<Employee> employees = tripCrewService.listEmployees();
        if (!employees.isEmpty()) {
            return employeesExists;
        } else {
            return !employeesExists;
        }
    }

    // Método para mostrar la lista de vuelos disponibles
    public void mostrarVuelos() {
        List<ConnectionDTO> flights = tripCrewService.findFlightNoConnection();
        for (ConnectionDTO flight : flights) {
            System.out.println(String.format("id_vuelo: %s, id_escala: %s aeropuerto_salida %s, " +
                    "aeropuerto llegada: %s, " +
                    "fecha: %s", flight.getTripId(), flight.getConnectionId(), flight.getStartAirport(),
                    flight.getArriveAirport(), flight.getTripDate()));
        }
    }
}
