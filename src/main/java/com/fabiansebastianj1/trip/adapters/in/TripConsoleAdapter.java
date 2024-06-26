package com.fabiansebastianj1.trip.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.trip.application.TripService;
import com.fabiansebastianj1.trip.domain.models.Trip;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.Register;
import com.fabiansebastianj1.validations.ValidationExist;

public class TripConsoleAdapter {
    private final TripService tripService;

    public TripConsoleAdapter(TripService tripService) {
        this.tripService = tripService;
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("*** Modulo de cliente ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            System.out.println(
                    "1.Registrar trayecto? \n2.Asignar avión a trayecto \n3.Actualizar información de trayecto \n4.Eliminar trayecto \n5.Salir");
            int choice = scanner.nextInt();
            System.out.println(" ");

            switch (choice) {
                case 1:
                    break;
                case 2:
                    System.out.println("*** Asignar avión a trayecto ***");
                    System.out.println("*** Lista de vuelos disponibles ***");
                    showTrips();
                    Trip tripToAsignPlane = returnTrip(inputVali);
                    asignPlaneToTrip(tripToAsignPlane);
                    break;
                case 3:
                    System.out.println("*** Actualizar información de trayecto ***");
                    Trip tripToUpdate = returnTrip(inputVali);
                    showTrip(tripToUpdate);
                    updateTrip(tripToUpdate);
                    System.out.println("Actualización exitosa");
                case 4:
                    System.out.println("*** Eliminar trayecto ***");
                    Trip tripToDelete = returnTrip(inputVali);
                    tripService.deleteTrip(tripToDelete.getId());
                    Optional<ConnectionDTO> tripAsConnection = tripService.findTripAsConnectionByTripId(tripToDelete.getId());
                    List<Connections> connectionsToDelete = tripService.findAllConnectionsByTripId(tripAsConnection.get().getTripId());
                    for (Connections connections : connectionsToDelete) {
                        tripService.deleteConnection(connections.getId());
                    }
                    System.out.println("Trayecto eliminado exitosamente");
                    break;
                default:
                    break;
            }
        }
    }

    public void updateTrip(Trip trip){
        boolean newInput;
        InputVali inputVali = new InputVali();
        newInput = Register.yesOrNo("Desea cambiar la fecha del trayecto? Ingrese el valor numérico 1(si) 2(no)");
        if (newInput) {
            String newDate = inputVali.stringNotNull("Ingrese la nueva fecha");
            trip.setDate(newDate);
        }
        newInput = Register.yesOrNo("Desea cambiar el precio? Ingrese el valor numérico 1(si) 2(no)");
        if (newInput) {
            Double newPrice = inputVali.readDouble(inputVali.stringNotNull("Ingrese el precio"));
            trip.setPrice(newPrice);
        }
        tripService.updateTrip(trip);
    }

    public void asignPlaneToTrip(Trip trip){
        boolean reAsign;
        InputVali inputVali = new InputVali();
        Optional<ConnectionDTO> tripAsConnection = tripService.findTripAsConnectionByTripId(trip.getId());
        if (tripAsConnection.get().getPlaneId() == 0) {
            System.out.println("Este trayecto no tiene un avión asignado");
           showAvailablePlanes();
           Plane plane = returnPlane(inputVali);
           Connections connection = tripService.findConnectionById(tripAsConnection.get().getConnectionId()).get();
           connection.setId_plane(plane.getId());
           tripService.updateConnection(connection);
        }
        else{
            System.out.println("Este trayecto ya tiene un avión asignado");
            reAsign = Register.yesOrNo("Desea reasignarlo? Ingrese el valor numérico 1(si) 2(no)");
            if (reAsign) {
                showAvailablePlanes();
                Plane plane = returnPlane(inputVali);
                Connections connection = tripService.findConnectionById(tripAsConnection.get().getConnectionId()).get();
                connection.setId_plane(plane.getId());
                tripService.updateConnection(connection);
            }
            else{System.out.println("Asignación cancelada");}
        }
    }

    public void showAvailablePlanes() {
        System.out.println("***Listado de aviones disponibles ***");
        List<Plane> planes = tripService.findAllAvailable();
        for (Plane plane : planes) {
            System.out.println(String.format("id: %s , plates: %s, capacity: %s", plane.getId(), plane.getPlates(),
                    plane.getCapacity()));
        }
    }

    public Plane returnPlane(InputVali inputVali) {
        Plane plane = ValidationExist.transformAndValidateObj(
                () -> tripService.findPlaneById(
                        inputVali.readInt(inputVali.stringNotNull("Ingrese las placas del avión para asignarlo"))));
        return plane;
    }

    public Trip returnTrip(InputVali inputVali) {
        Trip trip = ValidationExist.transformAndValidateObj(
                () -> tripService
                        .findTripById(inputVali.readInt(inputVali.stringNotNull("Ingrese el id del trayecto"))));
        return trip;
    }

    public void showTrips() {
        List<ConnectionDTO> trips = tripService.findAllTripsAsConnectionDTO();
        String format = "| %-10s | %-13s | %-18s | %-15s | %-15s | %-12s |%n";
        System.out.format(
                "+------------+---------------+--------------------+---------------+---------------+--------------+%n");
        System.out.format(
                "| Trip ID    | Connection ID | Connection Number  | Start Airport | Arrive Airport| Trip Date    |%n");
        System.out.format(
                "+------------+---------------+--------------------+---------------+---------------+--------------+%n");

        for (ConnectionDTO trip : trips) {
            System.out.format(format,
                    trip.getTripId(),
                    trip.getConnectionId(),
                    trip.getConnectionNumber(),
                    trip.getStartAirport(),
                    trip.getArriveAirport(),
                    trip.getTripDate());
        }

        System.out.format(
                "+------------+---------------+--------------------+---------------+---------------+--------------+%n");
    }

    public void showTrip(Trip trip) {
        Optional<ConnectionDTO> tripAsConnection = tripService.findTripAsConnectionByTripId(trip.getId());
        String format = "| %-10s | %-13s | %-18s | %-15s | %-15s | %-12s | %-12s |%n";
        System.out.format(
                "+------------+---------------+--------------------+---------------+---------------+--------------+--------------+%n");
        System.out.format(
                "| Trip ID    | Connection ID | Connection Number  | Start Airport | Arrive Airport| Trip Date    | Price        |%n");
        System.out.format(
                "+------------+---------------+--------------------+---------------+---------------+--------------+--------------+%n");

        
            System.out.format(format,
            tripAsConnection.get().getTripId(),
            tripAsConnection.get().getConnectionId(),
            tripAsConnection.get().getConnectionNumber(),
            tripAsConnection.get().getStartAirport(),
            tripAsConnection.get().getArriveAirport(),
            tripAsConnection.get().getTripDate());
            trip.getPrice();
            System.out.format(
                "+------------+---------------+--------------------+---------------+---------------+--------------+--------------+%n");
    }
}
