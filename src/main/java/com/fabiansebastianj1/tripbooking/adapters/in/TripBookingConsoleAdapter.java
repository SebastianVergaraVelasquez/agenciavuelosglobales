package com.fabiansebastianj1.tripbooking.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.customer.domain.models.Customer;
import com.fabiansebastianj1.fare.domain.models.Fare;
import com.fabiansebastianj1.passenger.domain.models.Passenger;
import com.fabiansebastianj1.trip.domain.models.Trip;
import com.fabiansebastianj1.tripbooking.application.TripBookingService;
import com.fabiansebastianj1.tripbooking.domain.models.TripBooking;
import com.fabiansebastianj1.tripbookingdetails.domain.models.TripBookingDetails;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.Register;
import com.fabiansebastianj1.validations.ValidationExist;

public class TripBookingConsoleAdapter {

    private final TripBookingService tripBookingService;
    private final TripBookingConsoleUtils tripBookingConsoleUtils;

    public TripBookingConsoleAdapter(TripBookingService tripBookingService,
            TripBookingConsoleUtils tripBookingConsoleUtils) {
        this.tripBookingService = tripBookingService;
        this.tripBookingConsoleUtils = tripBookingConsoleUtils;
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("*** Modulo de reserva ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            int choice = inputVali.readInt("1.Crear reserva\n2. Eliminar reservas \n0. Salir");
            System.out.println(" ");

            switch (choice) {
                case 1:
                    // Cliente, trayecto, fecha, tarifa
                    System.out.println("*** Creación de reserva ***");
                    Customer showCustomer = ValidationExist.transformAndValidateObj(
                            () -> tripBookingService
                                    .findCostumerById(inputVali.stringNotNull("Ingrese el id del cliente")));
                    String customerId = showCustomer.getId();
                    tripBookingConsoleUtils.showTrips();
                    Connections showConnection = ValidationExist.transformAndValidateObj(
                            () -> tripBookingService.findFlightById(
                                    inputVali.readInt(("Ingrese el id del vuelo"))));
                    int flightId = showConnection.getId_trip();
                    String tripDate = inputVali.stringNotNull("Ingrese la fecha de la reserva");
                    tripBookingConsoleUtils.showFares();
                    Fare showFare = ValidationExist.transformAndValidateObj(
                            () -> tripBookingService.findFareById(
                                    inputVali.readInt(("Ingrese el id de la tarifa"))));
                    int fareId = showFare.getId();
                    TripBooking newTripBooking = new TripBooking(tripDate, flightId);
                    tripBookingService.createTripBooking(newTripBooking);
                    // Lo guardo y tengo que llamarlo para saber que ID se le dio y luego esa ID
                    // ponerla en el bookingDetails
                    Optional<TripBooking> tripBookingSaved = tripBookingService.findLastTripBooking();
                    TripBooking lasTripBooking = tripBookingSaved.get();
                    TripBookingDetails tripBookingDetails = new TripBookingDetails(lasTripBooking.getId(), customerId,
                            fareId, 1);
                    tripBookingService.createTripBookingDetail(tripBookingDetails);
                    break;
                case 2:
                    System.out.println("*** Eliminar reserva ***");
                    System.out.println(" ");
                    List<TripBooking> tripBookings = tripBookingService.findAllTripBookings();
                    String leftAlignFormat = "| %-4d | %-10s | %-8d |%n";
                    System.out.format(
                            "+------+------------+----------+%n");
                    System.out.format(
                            "| ID   | Date       | Id_Trip  |%n");
                    System.out.format(
                            "+------+------------+----------+%n");

                    for (TripBooking tripBooking : tripBookings) {
                        System.out.format(leftAlignFormat, tripBooking.getId(), tripBooking.getDate(),
                                tripBooking.getId_trip());
                    }

                    System.out.format(
                            "+------+------------+----------+%n");

                    TripBooking showTripBooking = ValidationExist.transformAndValidateObj(
                            () -> tripBookingService.findTripBookingById(
                                    inputVali.readInt(("Ingrese el id de la reserva"))));
                    int tripBookingId = showTripBooking.getId();
                    // TripBooking bookingToDelete =
                    // tripBookingConsoleUtils.returnTripBooking(inputVali); // Verificar que
                    // // exista el
                    // // booking
                    // List<Passenger> passengersToDelete = tripBookingService
                    // .getPassengersByBookingId(bookingToDelete.getId()); // Listar los pasajeros
                    // asociados
                    // for (Passenger passenger : passengersToDelete) {
                    // tripBookingService.deleletePassengers(passenger.getNif()); // Eliminar uno
                    // por uno de la base
                    // }
                    tripBookingService.deleteTripBookingDetailForId(tripBookingId);
                    tripBookingService.deleteTripBooking(tripBookingId);

                    break;
                case 0:
                    executing = false;
                    System.out.println("Saliendo del módulo de reserva");
                    break;
                default:
                    System.out.println("Seleccione una opción válida");
                    break;
            }

        }
    }

    public void bookingCustomerMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("*** Menú de reservas para clientes ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            int choice = inputVali.readInt(
                    "1.Reservar vuelo \n2.Consultar reservas \n3.Cancelar reserva \n4. Modificar reserva \n5.Salir");
            System.out.println(" ");

            switch (choice) {
                case 1:
                    System.out.println("*** Reserva de vuelos ***");
                    int[] tripsIdSelected = tripBookingConsoleUtils.showAndSelectFlights(inputVali); // Aquí se listan y
                                                                                                     // se seleccionan
                                                                                                     // los
                    // vuelos
                    if (tripsIdSelected[0] == 0 && tripsIdSelected[1] == 0) {
                        System.out.println("*** Error al reservar ***");
                        break;
                    }
                    List<Passenger> passengers = tripBookingConsoleUtils.savePassengers(tripsIdSelected); // Aquí se
                                                                                                          // registra la
                                                                                                          // info de los
                    // pasajeros
                    if (passengers.isEmpty()) {
                        System.out.println("*** Error al reservar ***");
                        break;
                    }
                    tripBookingConsoleUtils.infoValidateAndSave(tripsIdSelected, passengers);
                    break;
                case 2:
                    System.out.println("*** Consulta de reserva ***");
                    TripBooking bookingToShow = tripBookingConsoleUtils.returnTripBooking(inputVali); // verifica que
                                                                                                      // exista el id de
                                                                                                      // reserva
                    tripBookingConsoleUtils.showBookingDetails(bookingToShow.getId()); // MOstrar los detalles
                    tripBookingConsoleUtils.showPassengers(bookingToShow.getId()); // Mostrar los pasajeros asociados a
                                                                                   // ese booking
                    break;
                case 3:
                    System.out.println("*** Cancelar reserva ***");
                    System.out.println("Si tiene un viaje de ida y vuelta deberá cancelar ambas reservas por separado");
                    TripBooking bookingToDelete = tripBookingConsoleUtils.returnTripBooking(inputVali); // Verificar que
                                                                                                        // exista el
                                                                                                        // booking
                    List<Passenger> passengersToDelete = tripBookingService
                            .getPassengersByBookingId(bookingToDelete.getId()); // Listar los pasajeros asociados
                    for (Passenger passenger : passengersToDelete) {
                        tripBookingService.deleletePassengers(passenger.getNif()); // Eliminar uno por uno de la base
                    }
                    Optional<TripBookingDetails> details = tripBookingService
                            .findByTripBookingId(bookingToDelete.getId());
                    details.get().setTripConditionId(2);

                    tripBookingService.updateTripBookingDetail(details.get());
                    System.out.println("Reserva cancelada");
                    break;
                case 4:
                    System.out.println("*** Modificar reserva ***");
                    System.out.println("*** Consulta de reserva ***");
                    TripBooking bookingToUpdate = tripBookingConsoleUtils.returnTripBooking(inputVali); // verifica que
                                                                                                        // exista el id
                                                                                                        // de
                                                                                                        // reserva
                                                                                                        // //Tomar la
                                                                                                        // fecha
                    tripBookingConsoleUtils.showBookingDetails(bookingToUpdate.getId()); // MOstrar los detalles
                    tripBookingConsoleUtils.showPassengers(bookingToUpdate.getId()); // Mostrar los pasajeros asociados
                                                                                     // a
                                                                                     // ese booking
                    Trip trip = tripBookingConsoleUtils.returnTripById(bookingToUpdate.getId_trip()); // Trae el trip
                    Optional<ConnectionDTO> tripAsConenction = tripBookingService.findTripAsDTO(trip.getId());

                    System.out.println("Qué acción desea realizar, digite una opcion numérica");
                    choice = inputVali.readInt(
                            "1.Reagendar fecha \n2.Información de pasajeros \n3.Asientos \n0.Salir");
                    switch (choice) {
                        case 1:
                            updateTripDate(bookingToUpdate, tripAsConenction);
                            break;

                        default:
                            break;
                    }
                    break;
                default:
                    executing = false;
                    System.out.println("Saliendo del modulo");
                    break;

            }
        }
    }

    public void updateTripDate(TripBooking bookingToUpdate, Optional<ConnectionDTO> tripAsConenction) {

        InputVali inputVali = new InputVali();
        String newDate = inputVali.stringNotNull("Ingrese la nueva fecha que desea volar (YYYY-MM-DD): -> ");
        List<ConnectionDTO> tripsAvailable = tripBookingService.listConnectionsAvailable(
                    tripAsConenction.get().getStartAirport(), tripAsConenction.get().getArriveAirport(), newDate);
        if (!tripsAvailable.isEmpty()) {
            tripBookingConsoleUtils.showFlightsByAirports(tripsAvailable); // Este dice by airports, pero como tal
                                                                               // solo imprime la lista que se le mande
                                                                               // con un formato
                                                                                  
            Trip newTrip = tripBookingConsoleUtils.returnTripById(inputVali.readInt("Ingrese el id del nuevo vuelo"));
            //Poner este nuevo trip id en booking toupdate, setear los seats de las personas como 0 y ver cómo les asigno 
            //otros según el nuevo vuelo

            //verificar el número de asientos disponibles y los pasajeros que voy a ingresar

            //verificar el de asginar a un vuelo con conexión

        } else {
                System.out.println("No hay más viajes para su trayecto en la fecha seleccionada");
        }
    }
}
