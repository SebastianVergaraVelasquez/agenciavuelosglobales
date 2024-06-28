package com.fabiansebastianj1.validations;


public class Register {

    // Método estático para pedir al usuario que ingrese una opción sí o no
    public static boolean yesOrNo(String message) {
        InputVali inputVali = new InputVali();

        boolean value = true;

        while (true) {
            int choice = inputVali.readInt(message);
            switch (choice) {
                case 1:
                    value = true;
                    return value;
                    case 2:
                    value = false;
                    return value;
                default:
                    System.out.println("Ingrese una opción válida");
                    break;
            }
        }
    }
}
