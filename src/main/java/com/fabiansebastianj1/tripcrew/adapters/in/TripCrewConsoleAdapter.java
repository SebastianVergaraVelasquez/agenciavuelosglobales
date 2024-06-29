package com.fabiansebastianj1.tripcrew.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.employee.domain.models.Employee;
import com.fabiansebastianj1.employee.domain.models.EmployeeDTO;
import com.fabiansebastianj1.planes.domain.models.Plane;
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
                    System.out.println("*** Lista de trayectos disponibles ***\n");
                    List<ConnectionDTO> flights = tripCrewService.findFlightNoConnection();
                    mostrarVuelos(flights);
                    // Consulto que el id connection exista en la tabla connection
                    Connections showConnections = ValidationExist.transformAndValidateObj(
                            () -> tripCrewService.findConnectionById(
                                    inputVali.readInt(
                                            ("Ingrese la id_escala mostrada para el vuelo al que desea asignar la tripulación: -> "))));
                    int idConnection = showConnections.getId();

                    Optional<Plane> plane = tripCrewService.findPlaneById(showConnections.getId_plane());

                    boolean employeesExists = verificarEmpleados(plane.get().getAirlineId());
                    if (employeesExists) {
                        registrarEmpleados(idConnection, scanner, inputVali, plane.get().getAirlineId());
                        System.out.println("Tripulantes asignados exitosamente");
                    } else {
                        System.out.println("No hay empleados por asignar");
                        break;
                    }
                    break;

                case 2:
                    System.out.println("*** Consulta de tripulantes ***");

                    // Consulto el tripId y extraigo el tripId
                    Trip searchedTrip = ValidationExist.transformAndValidateObj(
                            () -> tripCrewService.findTripById(inputVali.readInt("Ingrese el id del vuelo: -> ")));

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

        System.out.println("\n*** Consulta de tripulantes ***");

        // Consulto el tripId y extraigo el tripId
        Trip searchedTrip = ValidationExist.transformAndValidateObj(
                () -> tripCrewService.findTripById(inputVali.readInt("Ingrese el id del vuelo: -> ")));

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
            System.out.println(String.format("id_employee: %s,  name: %s, role: %s", tripCrewDTO.getEmployeeId(),
                    tripCrewDTO.getEmployeeName(), tripCrewDTO.getEmployeeRole()));
        }
    }

    // Método para registrar empleados en un vuelo específico
    public void registrarEmpleados(int idConnection, Scanner scanner, InputVali inputVali, int airlineId) {
        mostrarEmpleados(airlineId);
        boolean continueAdd = true;

        while (continueAdd) {
            Employee showEmployee = ValidationExist.transformAndValidateObj(
                    () -> tripCrewService.findEmployeeById(inputVali.stringNotNull("Ingrese la id del empleado: -> ")));
            String employeeId = showEmployee.getId();
            TripCrew tripCrew = new TripCrew(employeeId, idConnection);
            tripCrewService.createTripCrew(tripCrew);
            continueAdd = Register.yesOrNo(
                    "Desea hacer un nuevo registro? Ingrese el valor numérico: 1 (Si) o 2(no)");
        }
    }

    // Método para mostrar la lista de empleados disponibles
    public void mostrarEmpleados(int airlineId) {
        System.out.println("** Lista de empleados **");
        List<EmployeeDTO> employees = tripCrewService.listTripulation(airlineId);
        for (EmployeeDTO employee : employees) {
            System.out.println(String.format("id: %s,  name: %s,  airline: %s", employee.getId(), employee.getName(), employee.getAirlineName()));
        }
    }

    // Método para verificar si existen empleados disponibles para asignar
    public boolean verificarEmpleados(int airlineId) {
        boolean employeesExists = true;
        List<EmployeeDTO> employees = tripCrewService.listTripulation(airlineId);
        if (!employees.isEmpty()) {
            return employeesExists;
        } else {
            return !employeesExists;
        }
    }

    // Método para mostrar la lista de vuelos disponibles
    public void mostrarVuelos(List<ConnectionDTO> flights) {
        String format = "| %-10s | %-13s | %-18s | %-13s | %-13s | %-12s | %-12f | %-18s |%n";
        System.out.format(
                "+------------+---------------+--------------------+---------------+---------------+--------------+--------------+--------------------+%n");
        System.out.format(
                "| Trip ID    | Connection ID | Connection Number  | Start Airport | Arrive Airport| Trip Date    | Price        | Airline            |%n");
        System.out.format(
                "+------------+---------------+--------------------+---------------+---------------+--------------+--------------+--------------------+%n");

        for (ConnectionDTO connectionDTO : flights) {
            System.out.format(format,
                    connectionDTO.getTripId(),
                    connectionDTO.getConnectionId(),
                    connectionDTO.getConnectionNumber(),
                    connectionDTO.getStartAirport(),
                    connectionDTO.getArriveAirport(),
                    connectionDTO.getTripDate(),
                    connectionDTO.getPrice(),
                    connectionDTO.getAirlineName());
            System.out.format(
                "+------------+---------------+--------------------+---------------+---------------+--------------+--------------+--------------------+%n");
    }
    }
}
