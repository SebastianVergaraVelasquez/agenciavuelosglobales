package com.fabiansebastianj1.planes.adapters.in;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.fabiansebastianj1.planes.application.PlaneService;
import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.validations.dateValidation;

public class PlaneConsoleAdapter {

    private final PlaneService planeService;

    public PlaneConsoleAdapter(PlaneService planeService) {
        this.planeService = planeService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean executing = true;

        while (executing) {
            System.out.println("*** Modulo de aviones ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            System.out.println("1.Registrar avión \n2.Eliminar avión");
            int choice = scanner.nextInt();
            System.out.println(" ");

            switch (choice) {
                case 1:
                    System.out.println("*** Registro de aviones ***");
                    System.out.println(" ");
                    scanner.nextLine();
                    System.out.println("Ingrese la matrícula del avión");
                    String plates = scanner.nextLine();
                    //Verificar si ya existe
                    System.out.println("Ingrese la capacidad del avión");
                    int capacity = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese la fecha de fabricación en formato yyyy-MM-dd:");
                    // Date fabricationDate = dateValidation.dateCheck();
                    String fabricationDate = scanner.nextLine();
                    System.out.println("Ingrese la id del estado del avión");
                    //Acá deberíamos mostrar las opciones que tiene de estado, listarlas y que en el input ponga el id
                    //Luego verificar que se ingrese un estado correcto
                    int statusId = scanner.nextInt();
                    System.out.println("Ingrese el id de la aerolínea a la que pertenece");
                    int airlineId = scanner.nextInt();
                    //Falta agregar las mismas validaciones que con status
                    System.out.println("Ingrese la id del modelo del avión");
                    //verificar igual que status
                    int modelId = scanner.nextInt();
                    Plane newPlane = new Plane(plates, capacity, fabricationDate, statusId,airlineId,modelId);
                    planeService.createPlane(newPlane);
                    break;
                case 2:
                    System.out.println("*** Eliminar avión ***");
                    System.out.println(" ");
                    List<Plane> planes = planeService.findAllPlanes();
                    String leftAlignFormat = "| %-4d | %-10s | %-8d | %-15s | %-9d | %-8d | %-7d | %-15s |%n";
                    System.out.format("+------+------------+----------+-----------------+-----------+----------+---------+%n");
                    System.out.format("| ID   | Plates     | Capacity | Fabrication Date| Status ID | AirlineID| ModelID |%n");
                    System.out.format("+------+------------+----------+-----------------+-----------+----------+---------+%n");
                        
                    for (Plane plane : planes) {
                        System.out.format(leftAlignFormat, plane.getId(), plane.getPlates(), plane.getCapacity(), plane.getFabricationDate(), plane.getStatusId(), plane.getAirlineId(), plane.getModelId());
                    }
                        
                    System.out.format("+------+------------+----------+-----------------+-----------+----------+---------+-----------------+%n");
                    int id = scanner.nextInt();
                    planeService.deletePlane(id);
                    break;
                case 3:
                    executing = false;
                    System.out.println("Saliendo del módulo de aviones");
                    break;
            
                default:
                    break;
            }
        }
}}
