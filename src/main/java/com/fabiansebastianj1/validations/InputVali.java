package com.fabiansebastianj1.validations;

import java.util.Scanner;

public class InputVali {
    private Scanner scanner;
    
    // Constructor que inicializa el scanner para leer desde la entrada estándar
    public InputVali() {
        this.scanner = new Scanner(System.in);
    }

    // Método para leer un entero desde la entrada estándar
    public int readInt(String mensaje) {
        int valor;
        while (true) {
            System.out.println(mensaje);
            try {
                valor = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Debe ingresar un dato valido, ");
            }
        }
        return valor;
    }

    // Método para leer un número decimal (double) desde la entrada estándar
    public Double readDouble(String mensaje) {
        Double valor;
        while (true) {
            // System.out.println(mensaje);
            try {
                valor = Double.parseDouble(mensaje);
                break;
            } catch (NumberFormatException e) {
                System.out.print("Debe ingresar un dato valido, ");
            }
        }
        return valor;
    }

    // Método para leer una cadena que no puede ser nula o vacía desde la entrada estándar
    public String stringNotNull(String message) {
        System.out.print(message);
        String value;
        while (true) {
            value = scanner.nextLine();
            if (value.isBlank()) {
                System.out.print("Este es un campo obligatorio, " + message.toLowerCase());
            } else {
                break;
            }
        }
        return value;
    }

    // Método para leer una cadena que no puede ser nula o vacía y debe tener una longitud máxima especificada
    public String stringWithLeght(String message, int length) {
        String value;
        while (true) {
            value = this.stringNotNull(message);
            if (value.length() > length) {
                System.out.print("Error, ");
            } else {
                break;
            }
        }
        return value;
    }

    // Método para leer una cadena que puede ser nula desde la entrada estándar
    public String stringNull(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
