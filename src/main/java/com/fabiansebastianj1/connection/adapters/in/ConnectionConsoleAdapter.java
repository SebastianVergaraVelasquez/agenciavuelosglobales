package com.fabiansebastianj1.connection.adapters.in;

import java.util.List;
import java.util.Scanner;

import com.fabiansebastianj1.connection.application.ConnectionService;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.ValidationExist;
import com.fabiansebastianj1.connection.domain.models.*;
public class ConnectionConsoleAdapter {
    
    private final ConnectionService connectionService;

    
    public ConnectionConsoleAdapter(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();
        
        while (executing) {
            System.out.println("*** Modulo de cliente ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            System.out.println("1.Visualizar información del trayecto \n2.Salir");
            int choice = scanner.nextInt();
            System.out.println(" ");

            switch (choice) {
                case 1:
                    
                    ConnectionDTO trip = ValidationExist.transformAndValidateObj(
                        () -> connectionService.findTripByTripId(inputVali.readInt(inputVali.stringNotNull("Ingrese la id del vuelo")))
                    );
                    System.out.println(String.format("id_vuelo: %s\n"+
                    "salida: %s \n"+
                    "llegada: %s \n"+
                    "Fecha: %s", trip.getTripId(),trip.getStartAirport(),trip.getArriveAirport(),trip.getTripDate()));
                    showConnections(trip.getTripId());
                    break;

                default:
                    break;
            }
        }
    }
    
    public void showConnections(int tripId){
        System.out.println("Conexiones de este vuelo");
        List<ConnectionDTO> connections = connectionService.findConnectionsByTripId(tripId);
        for (ConnectionDTO connectionDTO : connections) {
            System.out.println(String.format("Connection_number: %s, airport: %", connectionDTO.getConnectionNumber(), connectionDTO.getStartAirport()));
        }
    }
}
