package com.fabiansebastianj1.planes.adapters.in;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.fabiansebastianj1.airlines.domain.models.Airline;
import com.fabiansebastianj1.country.domain.models.Country;
import com.fabiansebastianj1.model.domain.models.Model;
import com.fabiansebastianj1.planes.application.PlaneService;
import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.planes.domain.models.PlaneDTO;
import com.fabiansebastianj1.status.domain.models.Status;
import com.fabiansebastianj1.validations.dateValidation;
import com.fabiansebastianj1.validations.InputVali;
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
            System.out.println("1.Registrar avión \n2.Eliminar avión \n3. Consultar Avion \n4. Salir");
            int choice = scanner.nextInt();
            System.out.println(" ");

            switch (choice) {
                case 1:
                    System.out.println("*** Registro de aviones ***");
                    System.out.println(" ");
                    scanner.nextLine();

                    String plates = verificarAvion(inputVali);

                    int capacity = inputVali.readInt(inputVali.stringNotNull("Ingrese la capacidad del avión"));
                    scanner.nextLine();
                    System.out.println("Ingrese la fecha de fabricación en formato yyyy-MM-dd:");
                    // Date fabricationDate = dateValidation.dateCheck(); //No sé de qué manera
                    // dejarlo
                    String fabricationDate = scanner.nextLine();
                    // Acá deberíamos mostrar las opciones que tiene de estado, listarlas y que en
                    mostrarStatuses();

                    Status showStatus = ValidationExist.transformAndValidateObj(
                            () -> planeService.findStatusById(inputVali.readInt("Ingrese la id del estado del avión")));
                    int statusId = showStatus.getId();

                    mostrarAirlines();

                    Airline showAirline = ValidationExist.transformAndValidateObj(
                            () -> planeService.findAirlineById(inputVali.readInt(
                                    inputVali.stringNotNull("Ingrese el id de la aerolínea a la que pertenece"))));
                    int airlineId = showAirline.getId();

                    mostrarModels();

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
                    showPlaneInfo(inputVali);

                    break;
                case 4:
                    executing = false;
                    System.out.println("Saliendo del módulo de aviones");
                    break;

                default:
                    break;
            }
        }
    }

    public String verificarAvion(InputVali inputVali) {
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

    public void mostrarStatuses() {
        System.out.println("Lista de estados");
        List<Status> statuses = planeService.findAllStatuses();
        for (Status status : statuses) {
            System.out.println(String.format("id: %s, nombre: %s", status.getId(), status.getName()));
        }
    }

    public void mostrarAirlines() {
        System.out.println("Lista de aerolineas");
        List<Airline> airlines = planeService.findAllAirlines();
        for (Airline airline : airlines) {
            System.out.println(String.format("id: %s, nombre: %s", airline.getId(), airline.getName()));
        }
    }

    public void mostrarModels() {
        System.out.println("Lista de modelos");
        List<Model> models = planeService.findAllModels();
        for (Model model : models) {
            System.out.println(String.format("id: %s, nombre: %s", model.getId(), model.getName()));
        }
    }

    public void showPlaneInfo(InputVali inputVali) {
        Plane showPlane = ValidationExist.transformAndValidateObj(
                () -> planeService.findPlaneByPlate(
                        inputVali.stringNotNull("Ingrese el id del avión")));

        Optional<PlaneDTO> planeDTO = planeService.findPlaneInfoAdditional(showPlane.getPlates());
        PlaneDTO planeFinded = planeDTO.get();

        String leftAlignFormat = "| %-4d | %-10s | %-8d | %-15s | %-9d | %-8d | %-7d | %-15s |%n";
        System.out.format(
                "+------+------------+----------+-----------------+-----------+----------+---------+%n");
        System.out.format(
                "| ID   | Plates     | Capacity | Fabrication Date| Status | Airline| Model |%n");
        System.out.format(
                "+------+------------+----------+-----------------+-----------+----------+---------+%n");
        System.out.format(leftAlignFormat,planeFinded.getId(), planeFinded.getPlates(), planeFinded.getCapacity(),
                planeFinded.getFabricationDate(), planeFinded.getStatusName(), planeFinded.getAirlineName(),
                planeFinded.getModelName());
    }

}
