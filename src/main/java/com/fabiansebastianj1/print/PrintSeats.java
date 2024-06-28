package com.fabiansebastianj1.print;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fabiansebastianj1.planes.domain.models.Plane;

public class PrintSeats {

    public static void printSeats(List<String> occupiedSeats, Plane planeOrigin){

        int capacidad = planeOrigin.getCapacity();
        String[] numerosFormateados = new String[capacidad];

        for (int i = 0; i < capacidad; i++) {
            // Formatear el número con ceros a la izquierda
            numerosFormateados[i] = String.format("%03d", i + 1);
        }

        // Convertir la lista de asientos ocupados a un conjunto para optimizar la búsqueda
        Set<String> occupiedSet = new HashSet<>(occupiedSeats);

        // Cambiar los números ocupados a "xxx"
        for (int i = 0; i < numerosFormateados.length; i++) {
            if (occupiedSet.contains(numerosFormateados[i])) {
                numerosFormateados[i] = "xxx";
            }
        }


        // Imprimir el array en el formato solicitado
        imprimirArrayFormateado(numerosFormateados);
    }

    public static void cambiarNumero(String[] array, String numero, String nuevoValor) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(numero)) {
                array[i] = nuevoValor;
                break; // Si solo se quiere cambiar la primera coincidencia
            }
        }
    }

    public static void imprimirArrayFormateado(String[] array) {
        int filas = array.length / 6; // Calcula el número de filas (2 columnas principales * 3 subcolumnas)
        for (int i = 0; i < filas; i++) {
            // Imprime la primera línea de cada fila
            for (int j = 0; j < 6; j++) {
                if (j % 3 == 0) {
                    System.out.print("\t-------------------");
                }
            }
            System.out.println();

            // Imprime la segunda línea de cada fila
            for (int j = 0; j < 6; j++) {
                int index = i * 6 + j;
                if (j % 3 == 0) {
                    System.out.print("\t| " + array[index] + " |\t");
                } else {
                    System.out.print(array[index] + " | ");
                }
            }
            System.out.println();
        }

        // Manejo de la última fila si no es completa
        int elementosRestantes = array.length % 6;
        if (elementosRestantes > 0) {
            for (int j = 0; j < elementosRestantes; j++) {
                if (j % 3 == 0) {
                    System.out.print("\t-------------------");
                }
            }
            System.out.println();

            for (int j = 0; j < elementosRestantes; j++) {
                int index = filas * 6 + j;
                if (j % 3 == 0) {
                    System.out.print("\t| " + array[index] + " |\t");
                } else {
                    System.out.print(array[index] + " | ");
                }
            }
            System.out.println();
        }
        System.out.print("\t-------------------\t-------------------");
    }
}
