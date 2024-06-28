package com.fabiansebastianj1.tripbookingdetails.adapters.in;

import java.util.List;
import java.util.Scanner;

import com.fabiansebastianj1.tripbookingdetails.application.TripBookingDetailsService;
import com.fabiansebastianj1.tripbookingdetails.domain.models.TripBookingDetailsDTO;
import com.fabiansebastianj1.validations.InputVali;

public class TripBookingDetailsAdapter {

    private final TripBookingDetailsService tripBookingDetailsService;

    public TripBookingDetailsAdapter(TripBookingDetailsService tripBookingDetailsService) {
        this.tripBookingDetailsService = tripBookingDetailsService;
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("*** Consulta de reservas ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            int choice = inputVali.readInt("1.Consulta de reservas \n2. Salir");
            System.out.println(" ");
            
            switch (choice) {
                case 1:
                    String idFlightOrCustomer = inputVali.stringNotNull("Ingrese el id del cliente o del vuelo para ver sus reservas");
                    try {
                        int idFlight = Integer.parseInt(idFlightOrCustomer);
                        showBookingByTripId(idFlight);
                    } catch (NumberFormatException e) {
                        showBookingByCustomerId(idFlightOrCustomer);
                    }
                    break;
                case 2:
                    executing = false;
                    System.out.println("Saliendo del modulo");
                    break;
                default:
                    System.out.println("Ingrese una opción válida");
                    break;
            }
        }
    }

    public void showBookingByCustomerId(String id){
        System.out.println("Lista de reservas por id cliente");
        List<TripBookingDetailsDTO> tripBookingDetails = tripBookingDetailsService.findTripBookingByCustomerId(id);
        if (!tripBookingDetails.isEmpty()) {
            for (TripBookingDetailsDTO tripBookingDetailsDTO : tripBookingDetails) {
                System.out.println(String.format("booking_detail_id: %s \n"+
                "booking_id: %s, "+
                "trip_id: %s, \n"+
                "customer_id: %s, \n"+
                "customer_name: %s, \n"+
                "fare_description: %s", tripBookingDetailsDTO.getId(),tripBookingDetailsDTO.getTripBookingId(),tripBookingDetailsDTO.getIdTrip(), tripBookingDetailsDTO.getCustomerId(), tripBookingDetailsDTO.getCustomerName(), tripBookingDetailsDTO.getFareName()));
            }
        }
        else{System.out.println("No hay reservas");}
        
    }

    public void showBookingByTripId(int id){
        System.out.println("Lista de reservas por id vuelo");
        List<TripBookingDetailsDTO> tripBookingDetails = tripBookingDetailsService.findTripBookingByTripId(id);
        if (!tripBookingDetails.isEmpty()) {
            for (TripBookingDetailsDTO tripBookingDetailsDTO : tripBookingDetails) {
                System.out.println(String.format("booking_detail_id: %s \n"+
                "booking_id: %s, "+
                "trip_id: %s, \n"+
                "customer_id: %s, \n"+
                "customer_name: %s, \n"+
                "fare_description: %s", tripBookingDetailsDTO.getId(),tripBookingDetailsDTO.getTripBookingId(),tripBookingDetailsDTO.getIdTrip(), tripBookingDetailsDTO.getCustomerId(), tripBookingDetailsDTO.getCustomerName(), tripBookingDetailsDTO.getFareName()));
            }
        }
        else{System.out.println("No hay reservas");}
    }
}
