package com.fabiansebastianj1.validations;

import java.util.Scanner;

public class NewRegister {

    public static boolean yesOrNo(Scanner scanner){

        boolean value = true;

        while (true) {
            System.out.println("Desea hacer otro registro. Ingrese 1. Para sí, 2. Para no");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    value = true;
                    break;
                case 2:
                    value = false;
                    break;
                default:
                    System.out.println("Ingrese una opción válida");
                    break;
            }
            break;
        }
        return value;
    }
}
