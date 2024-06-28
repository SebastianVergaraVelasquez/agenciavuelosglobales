package com.fabiansebastianj1.connection.adapters.in;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.airport.domain.models.Airport;
import com.fabiansebastianj1.connection.application.ConnectionService;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.Register;
import com.fabiansebastianj1.validations.ValidationExist;
import com.fabiansebastianj1.connection.domain.models.*;
import com.fabiansebastianj1.planes.domain.models.Plane;

public class ConnectionConsoleAdapter {

    private final ConnectionService connectionService;

    public ConnectionConsoleAdapter(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public void start() {
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("\n*** Modulo de conexion ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            int choice = inputVali.readInt(
                    "1. Visualizar información del trayecto \n2. Actualizar escala \n3. Eliminar escala \n0. Salir");
            System.out.println(" ");

            switch (choice) {
                case 1:

                    ConnectionDTO trip = ValidationExist.transformAndValidateObj(
                            () -> connectionService.findTripByTripId(
                                    inputVali.readInt(("Ingrese la id del vuelo: -> "))));
                    System.out.println(String.format("id_vuelo: %s\n" +
                            "salida: %s \n" +
                            "llegada: %s \n" +
                            "Fecha: %s", trip.getTripId(), trip.getStartAirport(), trip.getArriveAirport(),
                            trip.getTripDate()));
                    showConnections(trip.getTripId());
                    break;
                case 2:
                    System.out.println("*** Actualizar información de escala ***");
                    Connections connection = returnConnection(inputVali);
                    showConnectionInfoById(connection.getId());
                    updateInfoConnection(connection);
                    System.out.println("* Actualización exitosa *");
                    break;
                case 3:
                    System.out.println("*** Eliminar escala ***");
                    Connections connectionToDelete = returnConnection(inputVali);
                    connectionService.deleteCOnnection(connectionToDelete.getId());
                    break;
                case 0:
                    executing = false;
                    System.out.println("Saliendo del modulo de conexion");
                    break;
                default:
                    System.out.println("Ingrese una opción válida");
                    break;
            }
        }
    }

    public void updateInfoConnection(Connections connection) {
        InputVali inputVali = new InputVali();
        boolean newInput;
        newInput = Register.yesOrNo("Desea modificar connection_number? Ingrese el valor numérico: 1 (si) 2(no)");
        if (newInput) {
            connection.setConnection_number(inputVali.stringNotNull("Ingrese connection_number: -> "));
        }
        newInput = Register.yesOrNo("Desea modificar trip_id? Ingrese el valor numérico: 1 (si) 2(no)");
        if (newInput) {
            connection.setId_trip(inputVali.readInt(("Ingrese nuevo id_trip: -> ")));
        }
        newInput = Register.yesOrNo("Desea modificar el aeropuerto? Ingrese el valor numérico: 1 (si) 2(no)");
        if (newInput) {
            System.out.println("*** Lista de aeropuertos ***");
            finAllAirports();
            connection.setId_airport(inputVali.stringNotNull("Ingrese nuevo id_airport: -> "));
        }
        newInput = Register.yesOrNo("Desea modificar el avion a usar? Ingrese el valor numérico: 1 (si) 2(no)");
        if (newInput) {

            Plane plane = ValidationExist.transformAndValidateObj(
                    () -> connectionService
                            .findPlaneByPlates(inputVali.stringNotNull("Ingrese las placas del avión: -> ")));
            connection.setId_plane(plane.getId());
        }
        connectionService.updateConnection(connection);
    }

    public void finAllAirports() {
        List<Airport> airports = connectionService.findAllAirports();
        for (Airport airport : airports) {
            System.out.println(String.format("id: %s, nombre: %s", airport.getId(), airport.getName()));
        }
    }

    public Connections returnConnection(InputVali inputVali) {
        Connections connection = ValidationExist.transformAndValidateObj(
                () -> connectionService
                        .getConnectionById(inputVali.readInt(("Ingrese el id de la escala: -> "))));
        return connection;
    }

    public void showConnectionInfoById(int id) {
        Optional<ConnectionDTO> connectionFinded = connectionService.findConnectionInfoById(id);
        ConnectionDTO connection = connectionFinded.get();
        System.out.println(String.format("id_connection: %s \n" +
                "connection_number: %s \n" +
                "trip_id: %s \n" +
                "airport: %s \n" +
                "plane_id: %s \n" +
                "plane_plates_ %s \n", connection.getConnectionId(), connection.getConnectionNumber(),
                connection.getTripId(), connection.getStartAirport(), connection.getPlaneId(),
                connection.getPlanePlates()));
    }

    public void showConnections(int tripId) {
        List<ConnectionDTO> connections = connectionService.findConnectionsByTripId(tripId);
        if (!connections.isEmpty()) {
            System.out.println("Conexiones de este vuelo");
            for (ConnectionDTO connectionDTO : connections) {
                System.out.println(String.format("Connection_number: %s,  airport: %s",
                        connectionDTO.getConnectionNumber(), connectionDTO.getStartAirport()));
            }
        } else {
            System.out.println("Este vuelo no tiene escalas");
        }
    }
}
