package com.fabiansebastianj1.validations;

import java.util.Scanner;

public class Register {

    // Método estático para pedir al usuario que ingrese una opción sí o no
    public static boolean yesOrNo(String message) {
        Scanner scanner = new Scanner(System.in);
        boolean value = true;

        while (true) {
            System.out.println(message);
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
