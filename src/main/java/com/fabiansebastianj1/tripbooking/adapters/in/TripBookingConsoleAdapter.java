package com.fabiansebastianj1.tripbooking.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.customer.domain.models.Customer;
import com.fabiansebastianj1.fare.domain.models.Fare;
import com.fabiansebastianj1.tripbooking.application.TripBookingService;
import com.fabiansebastianj1.tripbooking.domain.models.TripBooking;
import com.fabiansebastianj1.tripbookingdetails.domain.models.TripBookingDetails;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.ValidationExist;

public class TripBookingConsoleAdapter {
    
    private final TripBookingService tripBookingService;

    public TripBookingConsoleAdapter(TripBookingService tripBookingService) {
        this.tripBookingService = tripBookingService;
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("*** Modulo de reserva ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            System.out.println("1.Crear reserva \n2.Consultar reservas \n3.Salir");
            int choice = scanner.nextInt();
            System.out.println(" ");

            switch (choice) {
                case 1:
                    //Cliente, trayecto, fecha, tarifa
                    System.out.println("*** Creación de reserva ***");
                    Customer showCustomer = ValidationExist.transformAndValidateObj(
                      () -> tripBookingService.findCostumergById(inputVali.stringNotNull("Ingrese el id del cliente"))  
                    );
                    String customerId = showCustomer.getId();
                    mostrarVuelos();
                    Connections showConnection = ValidationExist.transformAndValidateObj(
                      () -> tripBookingService.findFlightById(inputVali.readInt(inputVali.stringNotNull("Ingrese el id de la tarifa")))
                    );
                    int flightId = showConnection.getId_trip();
                    String tripDate = inputVali.stringNotNull("Ingrese la fecha de la reserva");
                    mostrarTarifas();
                    Fare showFare = ValidationExist.transformAndValidateObj(
                      () -> tripBookingService.findFareById(inputVali.readInt(inputVali.stringNotNull("Ingrese el id de la tarifa")))  
                    );
                    int fareId = showFare.getId();
                    TripBooking newTripBooking = new TripBooking(tripDate, flightId);
                    tripBookingService.createTripBooking(newTripBooking);
                    //Lo guardo y tengo que llamarlo para saber que ID se le dio y luego esa ID ponerla en el bookingDetails
                    Optional<TripBooking> tripBookingSaved = tripBookingService.findLastTripBooking();
                    TripBooking lasTripBooking = tripBookingSaved.get();
                    TripBookingDetails tripBookingDetails = new TripBookingDetails(lasTripBooking.getId(),customerId,fareId);
                    tripBookingService.createTripBookingDetail(tripBookingDetails);
                    break;
                case 2:
                    break;
                case 3:
                    executing = false;
                    System.out.println("Saliendo del módulo de reserva");
                    
                    break;
                default:
                    System.out.println("Seleccione una opción válida");
                    break;
            }

        }
    }

     public void mostrarVuelos() {
        List<ConnectionDTO> flights = tripBookingService.listFlights();
        for (ConnectionDTO flight : flights) {
            System.out.println(String.format("id_vuelo: %s, id_escala: %s aeropuerto_salida %s, " +
                    "aeropuerto llegada: %s, " +
                    "fecha: %", flight.getTripId(), flight.getConnectionId(), flight.getStartAirport(),
                    flight.getArriveAirport(), flight.getTripDate()));
        }
    }

    public void mostrarTarifas() {
        List<Fare> fares = tripBookingService.listFares();
        for (Fare fare : fares) {
            System.out.println(String.format("id: %s, descripción: %, valor: %s", fare.getId(), fare.getDescription(), fare.getValue()));
        }
    }


}
