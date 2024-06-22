package com.fabiansebastianj1.planes.adapters.in;

import java.sql.Date;
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
            int choice;
            System.out.println("*** Modulo de aviones ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            System.out.println("1.Registrar avión \n2.Eliminar avión");
            choice = scanner.nextInt();
            System.out.println(" ");

            switch (choice) {
                case 1:
                    System.out.println("*** Registro de aviones ***");
                    System.out.println(" ");
                    System.out.println("Ingrese la matrícula del avión");
                    String plates = scanner.nextLine();
                    System.out.println("Ingrese la capacidad del avión");
                    int capacity = scanner.nextInt();
                    System.out.println("Ingrese la fecha de fabricación en formato yyyy-MM-dd:");
                    Date fabricationDate = dateValidation.dateCheck();
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
                    
                    break;
                case 3:
                    executing = false;
                    System.out.println("Saliendo del módulo de aviones");
                    break;
            
                default:
                    break;
            }
        }
    }
}
