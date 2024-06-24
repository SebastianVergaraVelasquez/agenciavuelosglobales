package com.fabiansebastianj1.planes.adapters.in;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.fabiansebastianj1.airlines.domain.models.Airline;
import com.fabiansebastianj1.customer.domain.models.Customer;
import com.fabiansebastianj1.model.domain.models.Model;
import com.fabiansebastianj1.planes.application.PlaneService;
import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.planes.domain.models.PlaneDTO;
import com.fabiansebastianj1.status.domain.models.Status;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.Register;
import com.fabiansebastianj1.validations.ValidationExist;

public class PlaneConsoleAdapter {

    private final PlaneService planeService;

    public PlaneConsoleAdapter(PlaneService planeService) {
        this.planeService = planeService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("*** Modulo de aviones ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            System.out.println(
                    "1.Registrar avión \n2.Eliminar avión \n3. Consultar Avion \n4.Actualizar avión \n5. Salir");
            int choice = scanner.nextInt();
            System.out.println(" ");

            switch (choice) {
                case 1:
                    System.out.println("*** Registro de aviones ***");
                    System.out.println(" ");
                    scanner.nextLine();

                    String plates = verifyPlatesUntilOk(inputVali);

                    int capacity = inputVali.readInt(inputVali.stringNotNull("Ingrese la capacidad del avión"));
                    scanner.nextLine();
                    System.out.println("Ingrese la fecha de fabricación en formato yyyy-MM-dd:");
                    // Date fabricationDate = dateValidation.dateCheck(); //No sé de qué manera
                    // dejarlo
                    String fabricationDate = scanner.nextLine();
                    // Acá deberíamos mostrar las opciones que tiene de estado, listarlas y que en
                    showStatuses();

                    Status showStatus = ValidationExist.transformAndValidateObj(
                            () -> planeService.findStatusById(inputVali.readInt("Ingrese la id del estado del avión")));
                    int statusId = showStatus.getId();

                    showAirlines();

                    Airline showAirline = ValidationExist.transformAndValidateObj(
                            () -> planeService.findAirlineById(inputVali.readInt(
                                    inputVali.stringNotNull("Ingrese el id de la aerolínea a la que pertenece"))));
                    int airlineId = showAirline.getId();

                    showModels();

                    Model showModel = ValidationExist.transformAndValidateObj(
                            () -> planeService.findModelById(inputVali.readInt("Ingrese la id del modelo del avión")));

                    int modelId = showModel.getId();

                    Plane newPlane = new Plane(plates, capacity, fabricationDate, statusId, airlineId, modelId);
                    planeService.createPlane(newPlane);

                    break;

                case 2:
                    System.out.println("*** Eliminar avión ***");
                    System.out.println(" ");
                    List<Plane> planes = planeService.findAllPlanes();
                    String leftAlignFormat = "| %-4d | %-10s | %-8d | %-15s | %-9d | %-8d | %-7d | %-15s |%n";
                    System.out.format(
                            "+------+------------+----------+-----------------+-----------+----------+---------+%n");
                    System.out.format(
                            "| ID   | Plates     | Capacity | Fabrication Date| Status ID | AirlineID| ModelID |%n");
                    System.out.format(
                            "+------+------------+----------+-----------------+-----------+----------+---------+%n");

                    for (Plane plane : planes) {
                        System.out.format(leftAlignFormat, plane.getId(), plane.getPlates(), plane.getCapacity(),
                                plane.getFabricationDate(), plane.getStatusId(), plane.getAirlineId(),
                                plane.getModelId());
                    }

                    System.out.format(
                            "+------+------------+----------+-----------------+-----------+----------+---------+-----------------+%n");

                    Plane showPlane = ValidationExist.transformAndValidateObj(
                            () -> planeService.findPlaneById(
                                    inputVali.readInt(inputVali.stringNotNull("Ingrese el id del avión"))));
                    int id = showPlane.getId();

                    planeService.deletePlane(id);
                    break;
                case 3:
                    System.out.println("*** Consultar avión ***");
                    System.out.println(" ");
                    scanner.nextLine();
                    Plane PlaneFinded = returnPlane(inputVali);
                    showPlaneInfo(PlaneFinded);

                    break;
                case 4:
                    System.out.println("Actualizar info avión");

                    break;

                default:
                    executing = false;
                    System.out.println("Saliendo del módulo de aviones");
                    break;
            }
        }
    }

    public String verifyPlatesUntilOk(InputVali inputVali) {
        String plates;
        while (true) {
            plates = inputVali.stringNotNull("Ingrese la matrícula del avión");
            Optional<Plane> planeFinded = planeService.findPlaneByPlate(plates);
            if (planeFinded.isPresent()) {
                Plane plane = planeFinded.get();
                System.out.println("Este avión ya ha sido registrado");
                System.out.println(String.format("id: %s , plates: %s", plane.getId(), plane.getPlates()));
                System.out.println("Ingrese una placa que no se haya registrado");
            } else {
                System.out.println("Este avión aún no se ha registado");
                break;
            }
        }
        return plates;
    }

    public void showStatuses() {
        System.out.println("Lista de estados");
        List<Status> statuses = planeService.findAllStatuses();
        for (Status status : statuses) {
            System.out.println(String.format("id: %s, nombre: %s", status.getId(), status.getName()));
        }
    }

    public void showAirlines() {
        System.out.println("Lista de aerolineas");
        List<Airline> airlines = planeService.findAllAirlines();
        for (Airline airline : airlines) {
            System.out.println(String.format("id: %s, nombre: %s", airline.getId(), airline.getName()));
        }
    }

    public void showModels() {
        System.out.println("Lista de modelos");
        List<Model> models = planeService.findAllModels();
        for (Model model : models) {
            System.out.println(String.format("id: %s, nombre: %s", model.getId(), model.getName()));
        }
    }

    public void updatePlane(InputVali inputVali) {
        Plane showPlane = returnPlane(inputVali);
        showPlaneInfo(showPlane);
        boolean newInput;
        int newIdStatus, newIdAirline;
        // verificar si desea cambiar el estado
        newInput = Register.yesOrNo("Desea cambiar el estado del avión? Ingrese el valor numerico 1 (si) o 2(no)");
        if (newInput == true) {
            showModels();
            newIdStatus = inputVali.readInt(inputVali.stringNotNull("Ingrese el nuevo nombre"));
        } else {
            newIdStatus = showPlane.getStatusId();
        }
        // verificar si desea cambiar la aerolinea
        newInput = Register.yesOrNo("Desea cambiar la edad del cliente? Ingrese el valor numerico 1 (si) o 2(no)");
        if (newInput == true) {
            newIdAirline = inputVali.readInt(inputVali.stringNotNull("Ingrese la edad"));
        } else {
            newIdAirline = showPlane.getAirlineId();
        }
        Plane updatedPlane = new Plane(showPlane.getId(),showPlane.getPlates(),showPlane.getCapacity(),showPlane.getFabricationDate(),newIdStatus,newIdAirline,showPlane.getModelId());
        planeService.updatePlane(updatedPlane);
    }

    public Plane returnPlane(InputVali inputVali) {
        Plane showPlane = ValidationExist.transformAndValidateObj(
                () -> planeService.findPlaneByPlate(
                        inputVali.stringNotNull("Ingrese la placa del avión")));
        return showPlane;
    }

    public void showPlaneInfo(Plane showPlane) {
        Optional<PlaneDTO> planeDTO = planeService.findPlaneInfoAdditional(showPlane.getPlates());
        PlaneDTO planeFinded = planeDTO.get();

        String leftAlignFormat = "| %-4d | %-10s | %-8d | %-15s | %-9d | %-8d | %-7d | %-15s |%n";
        System.out.format(
                "+------+------------+----------+-----------------+-----------+----------+---------+%n");
        System.out.format(
                "| ID   | Plates     | Capacity | Fabrication Date| Status | Airline| Model |%n");
        System.out.format(
                "+------+------------+----------+-----------------+-----------+----------+---------+%n");
        System.out.format(leftAlignFormat, planeFinded.getId(), planeFinded.getPlates(), planeFinded.getCapacity(),
                planeFinded.getFabricationDate(), planeFinded.getStatusName(), planeFinded.getAirlineName(),
                planeFinded.getModelName());
    }
}
