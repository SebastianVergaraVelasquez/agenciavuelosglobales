package com.fabiansebastianj1.tripbooking.adapters.in;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.fabiansebastianj1.airport.domain.models.Airport;
import com.fabiansebastianj1.city.domain.models.City;
import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.customer.domain.models.Customer;
import com.fabiansebastianj1.documenttype.domain.models.DocumentType;
import com.fabiansebastianj1.fare.domain.models.Fare;
import com.fabiansebastianj1.passenger.domain.models.Passenger;
import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.print.PrintSeats;
import com.fabiansebastianj1.trip.domain.models.Trip;
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
                    // Cliente, trayecto, fecha, tarifa
                    System.out.println("*** Creación de reserva ***");
                    Customer showCustomer = ValidationExist.transformAndValidateObj(
                            () -> tripBookingService
                                    .findCostumergById(inputVali.stringNotNull("Ingrese el id del cliente")));
                    String customerId = showCustomer.getId();
                    mostrarVuelos();
                    Connections showConnection = ValidationExist.transformAndValidateObj(
                            () -> tripBookingService.findFlightById(
                                    inputVali.readInt(inputVali.stringNotNull("Ingrese el id de la tarifa"))));
                    int flightId = showConnection.getId_trip();
                    String tripDate = inputVali.stringNotNull("Ingrese la fecha de la reserva");
                    mostrarTarifas();
                    Fare showFare = ValidationExist.transformAndValidateObj(
                            () -> tripBookingService.findFareById(
                                    inputVali.readInt(inputVali.stringNotNull("Ingrese el id de la tarifa"))));
                    int fareId = showFare.getId();
                    TripBooking newTripBooking = new TripBooking(tripDate, flightId);
                    tripBookingService.createTripBooking(newTripBooking);
                    // Lo guardo y tengo que llamarlo para saber que ID se le dio y luego esa ID
                    // ponerla en el bookingDetails
                    Optional<TripBooking> tripBookingSaved = tripBookingService.findLastTripBooking();
                    TripBooking lasTripBooking = tripBookingSaved.get();
                    TripBookingDetails tripBookingDetails = new TripBookingDetails(lasTripBooking.getId(), customerId,
                            fareId);
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
                        System.out.format(leftAlignFormat, tripBooking.getId(), tripBooking.getDate(),
                                tripBooking.getId_trip());
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

    public void bookingCustomerMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();
        boolean newInput;

        while (executing) {
            System.out.println("*** Menú de reservas para clientes ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            System.out.println(
                    "1.Reservar vuelo \n2.Consultar reservas \n3.Cancelar reserva \n4. Modificar reserva \n5.Salir");
            int choice = scanner.nextInt();
            System.out.println(" ");

            switch (choice) {
                case 1:
                    System.out.println("*** Reserva de vuelos ***");
                    int[] tripsIdSelected = showAndSelectFlights(inputVali); //Aquí se listan y se seleccionan los vuelos
                    if (tripsIdSelected[0] == 0 && tripsIdSelected[1] == 0) {
                        System.out.println("*** Error al reservar ***");
                        break;
                    }
                    List<Passenger> passengers = savePassengers(tripsIdSelected); //Aquí se registra la info de los pasajeros
                    if (passengers.isEmpty()) {
                        System.out.println("*** Error al reservar ***");
                        break;
                    }

                default:
                    break;
            }
        }
    }

    public int[] showAndSelectFlights(InputVali inputVali) {
        int[] tripsSelected = new int[2]; //Aquí almaceno los trip_id
        boolean newInput;
        System.out.println("*** Buscar vuelo ***");
        System.out.println("Ingrese la información solicitada a continuación");
        System.out.println("Estas son las ciudades disponibles");
        showCities();

        // seleccionar origen
        System.out.println("Seleccón de la ciudad de origen");
        City originCity = returnCity(inputVali);
        System.out.println("Estas son los aeropuertos disponibles");
        showAirports(originCity.getId());
        System.out.println("Seleccón del aeropuerto de origen");
        Airport orginAirport = returnAirport(inputVali);

        // seleccionar destino
        System.out.println("Seleccón de la ciudad de destino");
        City destinationCity = returnCity(inputVali);
        System.out.println("Estos son los aeropuertos disponibles");
        showAirports(destinationCity.getId());
        System.out.println("Seleccón del aeropuerto de destino");
        Airport destinationAirport = returnAirport(inputVali);

        // Verificar si es un viaje de ida y vuelta
        newInput = Register.yesOrNo("Es un viaje de ida y vuelta? Ingrese la opción numérica: 1(Sí) 2(No)");

        // Mostrar vuelos y seleccionarlos
        if (!newInput) { // En caso de que solo sea vuelo de ida
            String departureDate = inputVali.stringNotNull("Ingrese la fecha de salida");
            System.out.println("Estos son los vuelos disponibles:");
            List<ConnectionDTO> flightsOrigin = returnFlightsByAirports(orginAirport.getId(),
                    destinationAirport.getId(), departureDate);
            showFlightsByAirports(flightsOrigin);
            if (!flightsOrigin.isEmpty()) {
                Trip originTrip = returnTrip(inputVali);
                tripsSelected[0] = originTrip.getId();
                // de aquí se debe guardar el id del origin trip para luego mandarlo a la
                // reserva, al detail,
                // también para sacar el idConnection y luego mirar lo del avión asignado a ese
                // vuelo, de ahí tomar la capacidad,
                // para el tema de pasajeros DEVOLVER EN UN ARRAY
                System.out.println("Vuelo seleccionado");
                return tripsSelected; // Esto debería ser un return o algo así, para separar modulos y que me devuelva
                       // los id_trip al finalizar
            } else {
                System.out.println("No hay vuelos disponibles");
                return tripsSelected;
            }
        } else { // Si es vuelo de ida y vuelta
            String departureDate = inputVali.stringNotNull("Ingrese la fecha de salida");
            String returnDate = inputVali.stringNotNull("Ingrese la fecha de regreso");
            List<ConnectionDTO> flightsOrigin = returnFlightsByAirports(orginAirport.getId(),
                    destinationAirport.getId(), departureDate);
            List<ConnectionDTO> flightsReturn = returnFlightsByAirports(destinationAirport.getId(),
                    orginAirport.getId(), returnDate);
            if (flightsOrigin.isEmpty() || flightsReturn.isEmpty()) { // Verificar si en alguno de los dos no hay vuelos
                System.out.println("No hay vuelos con estas condiciones");
                return tripsSelected;
            } else {
                System.out.println("Estos son los vuelos disponibles de ida:");
                showFlightsByAirports(flightsOrigin);
                Trip originTrip = returnTrip(inputVali); // Aquí se selecciona el vuelo de ida
                System.out.println("Estos son los vuelos disponibles de vuelta:");
                showFlightsByAirports(flightsReturn);
                Trip returnTrip = returnTrip(inputVali); // Aquí se selecciona el vuelo de vuelta
                System.out.println("Vuelos seleccionados");
                tripsSelected[0] = originTrip.getId();
                tripsSelected[1] = returnTrip.getId();
                return tripsSelected; // Se deben devolver los trip_id en un array
            }
        }
    }

    public List<Passenger> asignSeats(List<Passenger> passengers, int[] tripsIdSelected){
        //Vuelo de ida
        //traer el avión
        ConnectionDTO tripOrigin = tripBookingService.findConnectionInfoById(tripsIdSelected[0]).get();
        Plane planeOrigin = tripBookingService.findPlaneById(tripOrigin.getPlaneId()).get();
        List<String> occupiedSeats = tripBookingService.getAllOccupiedSeats(tripOrigin.getTripId()); //Me devuelve los string de todos los puestos ocupados
        //Ahora imprimir en pantalla
        // PrintSeats.printSeats(occupiedSeats, planeOrigin);
        return passengers;
    }

    public List<Passenger> savePassengers(int[] tripsIdSelected){
        InputVali inputVali = new InputVali();
        List<Passenger> passengers = new ArrayList<>();
        int passengersNumber = inputVali.readInt(inputVali.stringNotNull("Ingrese el numero de pasajeros a registrar. Valor numérico por favor"));
        //Conocer la información del vuelo seleccionado, id de avion y de ahí sacar su capacidad
        ConnectionDTO tripOrigin = tripBookingService.findConnectionInfoById(tripsIdSelected[0]).get();
        Plane planeOrigin = tripBookingService.findPlaneById(tripOrigin.getPlaneId()).get();
        int ocupados = tripBookingService.getTotalOccupiedSeats(tripOrigin.getPlaneId()); //Esto retorna cantos puestos ocupados hay
        if ((planeOrigin.getCapacity() - ocupados) > passengersNumber ) {
            for (int i = 0; i < passengersNumber; i++) {
                System.out.println("Selección de tipo de documento");
                showDocumentTypes();
                DocumentType documentType = returnDocumentType(inputVali);
                String nif = inputVali.stringNotNull(String.format("Ingrese el docuemnto del pasajero %s", i));
                String name = inputVali.stringNotNull(String.format("Ingrese el nombre del pasajero %s", i));
                int age = inputVali.readInt(inputVali.stringNotNull(String.format("Ingrese el nombre del pasajero %s", i)));
                Passenger passenger = new Passenger(nif, name, age, "", documentType.getId(), 0);
                passengers.add(passenger);
            }
            System.out.println("Pasajeros guardados");
            return passengers;
        }
        System.out.println("Cupos incompletos");
        return passengers;
    }

    public DocumentType returnDocumentType(InputVali inputVali) {
        DocumentType documentType = ValidationExist.transformAndValidateObj(
                () -> tripBookingService.getDocumentType(
                        inputVali.readInt(inputVali.stringNotNull("Ingrese la id del Tipo de Documento"))));
        return documentType;
    }

    public void showDocumentTypes() {
        List<DocumentType> documentTypes = tripBookingService.findAllDocumentTypes();
        for (DocumentType documentType : documentTypes) {
            System.out.println(String.format("id: %s \n" +
                    "name: %s \n" + documentType.getId(), documentType.getName()));
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
            System.out.println(String.format("id: %s, descripción: %, valor: %s", fare.getId(), fare.getDescription(),
                    fare.getValue()));
        }
    }

    public void showCities() {
        List<City> cities = tripBookingService.findAllCities();
        for (City city : cities) {
            System.out.println(String.format("id_city: %s, name: %s", city.getId(), city.getName()));
        }
    }

    public City returnCity(InputVali inputVali) {
        City city = ValidationExist.transformAndValidateObj(
                () -> tripBookingService.findCityById(inputVali.stringNotNull("Ingrese el id de la ciudad")));
        return city;
    }

    public void showAirports(String id) {
        List<Airport> airports = tripBookingService.findAllAirportsByCityId(id);
        for (Airport airport : airports) {
            System.out.println(String.format("id_city: %s, name: %s", airport.getId(), airport.getName()));
        }
    }

    public Airport returnAirport(InputVali inputVali) {
        Airport airport = ValidationExist.transformAndValidateObj(
                () -> tripBookingService.findAirportById(inputVali.stringNotNull("Ingrese el id del aeropuerto")));
        return airport;
    }

    public List<ConnectionDTO> returnFlightsByAirports(String airportId1, String airportId2, String fecha) {
        List<ConnectionDTO> flights = tripBookingService.findAllFlightsByAirportsId(airportId1, airportId2, fecha);
        return flights;
    }

    public void showFlightsByAirports(List<ConnectionDTO> flights) {

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

    public Trip returnTrip(InputVali inputVali) {
        Trip trip = ValidationExist.transformAndValidateObj(
                () -> tripBookingService
                        .findTripById(inputVali
                                .readInt(inputVali.stringNotNull("Ingrese el id del vuelo a seleccionar (id_trip)"))));
        return trip;
    }
}
