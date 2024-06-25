package com.fabiansebastianj1.validations;

import java.util.Scanner;

public class InputVali {
    private Scanner scanner;
    
    public InputVali() {
        this.scanner = new Scanner(System.in);
    }

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

   public Double readDouble (String mensaje){
    Double valor;
        while (true) {
            System.out.println(mensaje);
            try {
                valor = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Debe ingresar un dato valido, ");
            }
        }
        return valor;
   }

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

    public String stringWithLeght(String message, int lenght) {
        String value;
        while (true) {
            value = this.stringNotNull(message);
            if (value.length() > lenght) {
                System.out.print("Error, ");
            } else {
                break;
            }
        }
        return value;
    }

    public String stringNull(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
