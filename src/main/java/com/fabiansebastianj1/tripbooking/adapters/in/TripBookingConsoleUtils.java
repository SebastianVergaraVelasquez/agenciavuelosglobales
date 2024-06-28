package com.fabiansebastianj1.tripbooking.adapters.in;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.airport.domain.models.Airport;
import com.fabiansebastianj1.city.domain.models.City;
import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.customer.domain.models.Customer;
import com.fabiansebastianj1.documenttype.domain.models.DocumentType;
import com.fabiansebastianj1.fare.domain.models.Fare;
import com.fabiansebastianj1.passenger.domain.models.Passenger;
import com.fabiansebastianj1.pay_type.domain.models.PayType;
import com.fabiansebastianj1.payment.domain.models.Payment;
import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.print.PrintSeats;
import com.fabiansebastianj1.trip.domain.models.Trip;
import com.fabiansebastianj1.tripbooking.application.TripBookingService;
import com.fabiansebastianj1.tripbooking.domain.models.TripBooking;
import com.fabiansebastianj1.tripbookingdetails.domain.models.TripBookingDetails;
import com.fabiansebastianj1.tripbookingdetails.domain.models.TripBookingDetailsDTO;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.Register;
import com.fabiansebastianj1.validations.ValidationExist;

public class TripBookingConsoleUtils {
    private final TripBookingService tripBookingService;

    public TripBookingConsoleUtils(TripBookingService tripBookingService) {
        this.tripBookingService = tripBookingService;
    }

    public DocumentType returnDocumentType(InputVali inputVali) {
        DocumentType documentType = ValidationExist.transformAndValidateObj(
                () -> tripBookingService.getDocumentType(
                        inputVali.readInt(("Ingrese la id del Tipo de Documento"))));
        return documentType;
    }

    public void showDocumentTypes() {
        List<DocumentType> documentTypes = tripBookingService.findAllDocumentTypes();
        for (DocumentType documentType : documentTypes) {
            System.out.println(String.format("id: %d \n" +
                    "name: %s \n", documentType.getId(), documentType.getName()));
        }
    }

    public void showTrips() {
        List<ConnectionDTO> flights = tripBookingService.listFlights();
        for (ConnectionDTO flight : flights) {
            System.out.println(String.format("id_vuelo: %s, id_escala: %s, aeropuerto_salida %s, " +
                    "aeropuerto llegada: %s, " + "airline: %s"+
                    "fecha: %s", flight.getTripId(), flight.getConnectionId(), flight.getStartAirport(),
                    flight.getArriveAirport(), flight.getAirlineName(), flight.getTripDate()));
        }
    }

    public void showFares() {
        List<Fare> fares = tripBookingService.listFares();
        for (Fare fare : fares) {
            System.out.println(String.format("id: %d, descripción: %s, valor: %f", fare.getId(), fare.getDescription(),
                    fare.getValue()));
        }
    }

    public Fare returnFare(InputVali inputVali) {
        Fare fare = ValidationExist.transformAndValidateObj(
                () -> tripBookingService
                        .findFareById(inputVali.readInt(("Ingrese el id de la ciudad"))));
        return fare;
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
            System.out.println(String.format("id_airport: %s, name: %s", airport.getId(), airport.getName()));
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

        String format = "| %-10s | %-13s | %-18s | %-13s | %-13s | %-12s | %-12f |%n";
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
                    connectionDTO.getTripDate(),
                    connectionDTO.getPrice());
            System.out.format(
                    "+------------+---------------+--------------------+---------------+---------------+--------------+--------------+%n");
            System.out.println(String.format("Airline: %s", connectionDTO.getAirlineName()));
        }
    }

    public Trip returnTrip(InputVali inputVali) {
        Trip trip = ValidationExist.transformAndValidateObj(
                () -> tripBookingService
                        .findTripById(inputVali
                                .readInt(("Ingrese el id del vuelo a seleccionar (id_trip)"))));
        return trip;
    }

    public Trip returnTripById(int id){
        Trip trip = ValidationExist.transformAndValidateObj(
                () -> tripBookingService
                        .findTripById(id));
        return trip;
    }

    public PayType returnPayType(InputVali inputVali) {
        PayType payType = ValidationExist.transformAndValidateObj(
                () -> tripBookingService.findPayTypeById(
                        inputVali.readInt(("Ingrese el id del medio de pago"))));
        return payType;
    }

    public void showPayTypes() {
        List<PayType> payTypes = tripBookingService.findAllPayTypes();
        for (PayType payType : payTypes) {
            System.out.println(String.format("id_city: %s, name: %s", payType.getId(), payType.getName()));
        }
    }

    public void showPassengers(int bookingId) {
        System.out.println("Lista de pasajeros\n");
        List<Passenger> passengers = tripBookingService.getPassengersByBookingId(bookingId);
        for (Passenger passenger : passengers) {
            System.out.println(String.format("Nif: %s, name: %s, seat: %s \n", passenger.getNif(), passenger.getName(),
                    passenger.getSeat()));
        }
    }

    public void showBookingDetails(int bookingId) {
        Optional<TripBookingDetailsDTO> details = tripBookingService.findByTripBookingIdAsDTO(bookingId);
        System.out.println(String.format("id_details: %s \n" +
                "id_booking: %s \nid_customer: %s \nfare: %s \nCondition: %s", details.get().getId(),
                details.get().getTripBookingId(),
                details.get().getCustomerId(), details.get().getFareName(), details.get().getTripConditionName()));
    }

    public TripBooking returnTripBooking(InputVali inputVali) {
        TripBooking tripBooking = ValidationExist.transformAndValidateObj(
                () -> tripBookingService
                        .findTripBookingById(inputVali.readInt(("Ingrese el identificador de reserva"))));
        return tripBooking;
    }

    public int[] showAndSelectFlights(InputVali inputVali) {
        int[] tripsSelected = new int[2]; // Aquí almaceno los trip_id
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

    public Customer registerPayment() {
        InputVali inputVali = new InputVali();
        String customerId = inputVali.stringNotNull("Ingrese el numero de identificación");
        System.out.println("***Selección de tipo de documento***");
        showDocumentTypes();
        DocumentType docType = returnDocumentType(inputVali);
        String name = inputVali.stringNotNull("Ingrese su nombre");
        int edad = inputVali.readInt(("Ingrese su edad"));
        Customer newCustomer = new Customer(customerId, name, edad, docType.getId());
        Optional<Customer> customer = tripBookingService.findCostumerById(customerId);
        // Verificar si ya hay un cliente con esa id para no volverlo a registrar en
        // base
        if (customer.isPresent()) {
            return customer.get();
        } else {
            tripBookingService.createCustomer(newCustomer);
            return newCustomer;
        }
    }

    public List<Passenger> asignSeats(List<Passenger> passengers, int tripId) {
        // traer el avión
        InputVali inputVali = new InputVali();
        ConnectionDTO tripOrigin = tripBookingService.findConnectionInfoById(tripId).get();
        Plane planeOrigin = tripBookingService.findPlaneById(tripOrigin.getPlaneId()).get();
        List<String> occupiedSeats = tripBookingService.getAllOccupiedSeats(tripOrigin.getTripId()); // Me devuelve los
                                                                                                     // string de todos
                                                                                                     // los puestos
                                                                                                     // ocupados
        // Ahora imprimir en pantalla
        PrintSeats.printSeats(occupiedSeats, planeOrigin);
        for (Passenger passenger : passengers) {
            passenger.setSeat((inputVali.stringWithLeght(
                    "\nIngrese el numero de asiento de la siguiente manera: Ex. Si desea el 3 escribirá 003\n", 3)));
        }
        return passengers;
    }

    public List<Passenger> savePassengers(int[] tripsIdSelected) {
        InputVali inputVali = new InputVali();
        List<Passenger> passengers = new ArrayList<>();
        int passengersNumber = inputVali
                .readInt(("Ingrese el numero de pasajeros a registrar. Valor numérico por favor"));
        // Conocer la información del vuelo seleccionado, id de avion y de ahí sacar su
        // capacidad
        ConnectionDTO tripOrigin = tripBookingService.findConnectionInfoById(tripsIdSelected[0]).get();
        Plane planeOrigin = tripBookingService.findPlaneById(tripOrigin.getPlaneId()).get();
        int ocupados = tripBookingService.getTotalOccupiedSeats(tripOrigin.getPlaneId()); // Esto retorna cantos puestos
                                                                                          // ocupados hay
        if ((planeOrigin.getCapacity() - ocupados) > passengersNumber) {
            for (int i = 0; i < passengersNumber; i++) {
                System.out.println("Selección de tipo de documento");
                showDocumentTypes();
                DocumentType documentType = returnDocumentType(inputVali);
                String nif = inputVali.stringNotNull(String.format("Ingrese el docuemnto del pasajero %s", i + 1));
                String name = inputVali.stringNotNull(String.format("Ingrese el nombre del pasajero %s", i + 1));
                int age = inputVali
                        .readInt((String.format("Ingrese la edad del pasajero %s", i + 1)));
                Passenger passenger = new Passenger(nif, name, age, "", documentType.getId(), 0);
                passengers.add(passenger);
            }
            System.out.println("Pasajeros guardados");
            return passengers;
        }
        System.out.println("Cupos incompletos");
        return passengers;
    }

    public void infoValidateAndSave(int[] tripsIdSelected, List<Passenger> passengers) {
        LocalDateTime now = LocalDateTime.now();
        // Crear un formateador con el patrón deseado solo para la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Formatear la fecha a una cadena
        String formattedDate = now.format(formatter);
        InputVali inputVali = new InputVali();
        List<Passenger> passengers1 = asignSeats(passengers, tripsIdSelected[0]);
        List<Passenger> passengers2 = new ArrayList<>();
        if (tripsIdSelected[1] != 0) {
            passengers2 = asignSeats(passengers, tripsIdSelected[1]);
        }

        System.out.println("Escoja el id de una de las tarifas mostradas");
        showFares();
        Fare fare = returnFare(inputVali);

        System.out.println("*** Información de pago ***");// Pago
        // Después de realizado el pago se genera un booking y booking detail con los
        // datos de quién paga

        Customer newCustomer = registerPayment();
        String email = inputVali.stringNotNull("Ingrese su email");
        showPayTypes();
        System.out.println("Al ingresar el método de pago se procesará la transacción");
        PayType payType = returnPayType(inputVali);

        System.out.println("Transacción realizada exitosamente");

        // info de pago y registrar en payment

        Double totalPayment = tripsIdSelected[0] * 1.19 * passengers.size();
        if (tripsIdSelected[1] != 0) {
            totalPayment += tripsIdSelected[0] * 1.19 * passengers.size();
        }
        Payment newPayment = new Payment(formattedDate, payType.getId(), totalPayment, newCustomer.getId());
        tripBookingService.createPayment(newPayment);

        // generar tripBooking, generar payment, generar detail
        int tripIdCondition = 1;
        TripBooking tripBooking1 = new TripBooking(formattedDate, tripsIdSelected[0]); // Crear booking
        tripBookingService.createTripBooking(tripBooking1);
        Optional<TripBooking> lasTripBooking1 = tripBookingService.findLastTripBooking();// retornar último
                                                                                         // booking
        TripBookingDetails tripBookingDetail1 = new TripBookingDetails(lasTripBooking1.get().getId(),
                newCustomer.getId(), fare.getId(), tripIdCondition); // Crear booking detail
        tripBookingService.createTripBookingDetail(tripBookingDetail1);

        Optional<TripBookingDetails> lasTripBookingDetail1 = tripBookingService.findLastTripBookingDetail();// retornar
                                                                                                            // último
                                                                                                            // booking
                                                                                                            // detail
        System.out.println(String.format(
                "Este es su identificador de reserva %s (Viaje de ida). Ha sido enviado a su correo electrónico (%s)",
                lasTripBooking1.get().getId(), email));

        for (Passenger passenger : passengers1) {
            passenger.setTripBookingDetailId(lasTripBookingDetail1.get().getId()); // cambiar
            tripBookingService.createPassenger(passenger); // generar passengers
        }

        if (tripsIdSelected[1] != 0) {
            TripBooking tripBooking2 = new TripBooking(formattedDate, tripsIdSelected[1]);
            tripBookingService.createTripBooking(tripBooking2);
            Optional<TripBooking> lasTripBooking2 = tripBookingService.findLastTripBooking();// retornar
            // último
            // booking
            TripBookingDetails tripBookingDetail2 = new TripBookingDetails(lasTripBooking2.get().getId(),
                    newCustomer.getId(), fare.getId(), 1);
            tripBookingService.createTripBookingDetail(tripBookingDetail2);
            Optional<TripBookingDetails> lasTripBookingDetail2 = tripBookingService.findLastTripBookingDetail();// retornar
                                                                                                                // último
                                                                                                                // booking
                                                                                                                // detail
            for (Passenger passenger : passengers2) {
                passenger.setTripBookingDetailId(lasTripBookingDetail2.get().getId()); // cambiar
                tripBookingService.createPassenger(passenger); // generar passengers
            }
            System.out.println(String.format(
                    "Este es su identificador de reserva %s (Viaje de regreso). Ha sido enviado a su correo electrónico (%s)",
                    lasTripBooking2.get().getId()));
        }
    }

}
