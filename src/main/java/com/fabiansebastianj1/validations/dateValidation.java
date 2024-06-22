package com.fabiansebastianj1.validations;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class dateValidation {

    public static java.sql.Date dateCheck(){
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        formato.setLenient(false); // Asegura que solo se acepten fechas válidas
        java.util.Date fechaUtil = null;

        while (fechaUtil == null) {
            
            String fechaString = scanner.nextLine();
            try {
                // Intentar convertir la cadena a java.util.Date
                fechaUtil = formato.parse(fechaString);
            } catch (ParseException e) {
                System.out.println("Formato de fecha inválido. Por favor, intente nuevamente.");
            }
        }
        return new java.sql.Date(fechaUtil.getTime());
    }
}
