package com.fabiansebastianj1.tripbooking.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.fabiansebastianj1.airport.domain.models.Airport;
import com.fabiansebastianj1.city.domain.models.City;
import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.customer.domain.models.Customer;
import com.fabiansebastianj1.fare.domain.models.Fare;
import com.fabiansebastianj1.tripbooking.application.TripBookingService;
import com.fabiansebastianj1.tripbooking.domain.models.TripBooking;
import com.fabiansebastianj1.tripbookingdetails.domain.models.TripBookingDetails;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.Register;
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
            System.out.println("1.Crear reserva \n2.Consultar reservas \n3. Eliminar reservas \n4.Salir");
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
                    System.out.println("*** Eliminar reserva ***");
                    System.out.println(" ");
                    List<TripBooking> tripBookings = tripBookingService.findAllTripBookings();
                    String leftAlignFormat = "| %-4d | %-10s | %-8d |%n";
                    System.out.format(
                            "+------+------------+----------+%n");
                    System.out.format(
                            "| ID   | Date     | Id_Trip |%n");
                    System.out.format(
                            "+------+------------+----------+%n");

                    for (TripBooking tripBooking : tripBookings) {
                        System.out.format(leftAlignFormat, tripBooking.getId(), tripBooking.getDate(), tripBooking.getId_trip());
                    }

                    System.out.format(
                            "+------+------------+----------+%n");

                    TripBooking showTripBooking = ValidationExist.transformAndValidateObj(
                            () -> tripBookingService.findTripBookingById(
                                    inputVali.readInt(inputVali.stringNotNull("Ingrese el id de la reserva"))));
                    int tripBookingId = showTripBooking.getId();

                    tripBookingService.deleteTripBookingDetailForId(tripBookingId);
                    tripBookingService.deleteTripBooking(tripBookingId);

                    break;
                case 4:
                    executing = false;
                    System.out.println("Saliendo del módulo de reserva");
                    break;
                default:
                    System.out.println("Seleccione una opción válida");
                    break;
            }

        }
    }


    public void bookingCustomerMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();
        boolean newInput; 

        while (executing) {
            System.out.println("*** Menú de reservas para clientes ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            System.out.println("1.Reservar vuelo \n2.Consultar reservas \n3.Cancelar reserva \n4. Modificar reserva \n5.Salir");
            int choice = scanner.nextInt();
            System.out.println(" ");

            switch (choice) {
                case 1:
                    System.out.println("*** Reserva de vuelos ***");
                    System.out.println("*** Buscar vuelo ***");
                    System.out.println("Ingrese la información solicitada a continuación");
                    System.out.println("Estas son las ciudades disponibles");
                    showCities();

                    //seleccionar origen
                    System.out.println("Seleccón de la ciudad de origen");
                    City originCity = returnCity(inputVali);
                    System.out.println("Estas son los aeropuertos disponibles");
                    showAirports(originCity.getId());
                    System.out.println("Seleccón del aeropuerto de origen");
                    Airport orginAirport = returnAirport(inputVali);

                    //seleccionar destino
                    System.out.println("Seleccón de la ciudad de destino");
                    City destinationCity = returnCity(inputVali);
                    System.out.println("Estos son los aeropuertos disponibles");
                    showAirports(destinationCity.getId());
                    System.out.println("Seleccón del aeropuerto de destino");
                    Airport destinationAirport = returnAirport(inputVali);
                    newInput = Register.yesOrNo("Es un viaje de ida y vuelta? Ingrese la opción numérica: 1(Sí) 2(No)" );
                    
                    
                    //Verificar si es un viaje de ida y vuelta
                    String departureDate = inputVali.stringNotNull("Ingrese la fecha de salida");
                    if (newInput) {
                        String returnDate = inputVali.stringNotNull("Ingrese la fecha de regreso");
                    }

                    //Mostrar los vuelos disponibles segun aeropuertos y fechas
                    System.out.println("Estos son los vuelos de ida");
                    showFlightsByAirports(orginAirport.getId(), destinationAirport.getId(), departureDate);

                    break;
            
                default:
                    break;
            }
        }
    }

     public void mostrarVuelos() {
        List<ConnectionDTO> flights = tripBookingService.listFlights();
        for (ConnectionDTO flight : flights) {
            System.out.println(String.format("id_vuelo: %s, id_escala: %s, aeropuerto_salida %s, " +
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

    public void showCities(){
        List<City> cities = tripBookingService.findAllCities();
        for (City city : cities) {
            System.out.println(String.format("id_city: %s, name: %s",city.getId(), city.getName()));
        }
    }

    public City returnCity(InputVali inputVali){
        City city = ValidationExist.transformAndValidateObj(
            () -> tripBookingService.findCityById(inputVali.stringNotNull("Ingrese el id de la ciudad"))
        );
        return city;
    }

    public void showAirports(String id){
        List<Airport> airports = tripBookingService.findAllAirportsByCityId(id);
        for (Airport airport : airports) {
            System.out.println(String.format("id_city: %s, name: %s",airport.getId(), airport.getName()));
        }
    }

    public Airport returnAirport(InputVali inputVali){
        Airport airport = ValidationExist.transformAndValidateObj(
            () -> tripBookingService.findAirportById(inputVali.stringNotNull("Ingrese el id del aeropuerto"))
        );
        return airport;
    }

    public void showFlightsByAirports(String airportId1, String airportId2, String fecha){
        List<ConnectionDTO> flights = tripBookingService.findAllFlightsByAirportsId(airportId1, airportId2, fecha);
        String format = "| %-10s | %-13s | %-18s | %-15s | %-15s | %-12s | %-12s |%n";
        System.out.format(
                "+------------+---------------+--------------------+---------------+---------------+--------------+--------------+%n");
        System.out.format(
                "| Trip ID    | Connection ID | Connection Number  | Start Airport | Arrive Airport| Trip Date    | Price        |%n");
        System.out.format(
                "+------------+---------------+--------------------+---------------+---------------+--------------+--------------+%n");

            for (ConnectionDTO connectionDTO : flights) {
                System.out.format(format,
                connectionDTO.getTripId(),
                connectionDTO.getConnectionId(),
                connectionDTO.getConnectionNumber(),
                connectionDTO.getStartAirport(),
                connectionDTO.getArriveAirport(),
                connectionDTO.getTripDate());
                connectionDTO.getPrice();
            System.out.format(
                "+------------+---------------+--------------------+---------------+---------------+--------------+--------------+%n");
            }
            
    }
}
