package com.fabiansebastianj1.trip.adapters.in;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.airport.domain.models.Airport;
import com.fabiansebastianj1.city.domain.models.City;
import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.connection.domain.models.Connections;
import com.fabiansebastianj1.country.domain.models.Country;
import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.planes.domain.models.PlaneDTO;
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

        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("*** Modulo de trip ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            int choice = inputVali.readInt(
                    "1.Registrar trayecto? \n2.Reasignar avión a trayecto \n3.Actualizar información de trayecto \n0. Salir");
            System.out.println(" ");

            switch (choice) {
                case 1:
                    System.out.println("Información del trayecto");
                    infoTrip(inputVali);
                    break;
                case 2:
                    System.out.println("*** Reasignar avión a trayecto ***");
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
                    break;
                // case 4:
                //     System.out.println("*** Eliminar trayecto ***");
                //     Trip tripToDelete = returnTrip(inputVali);
                //     tripService.deleteTrip(tripToDelete.getId());
                //     Optional<ConnectionDTO> tripAsConnection = tripService
                //             .findTripAsConnectionByTripId(tripToDelete.getId());
                //     List<Connections> connectionsToDelete = tripService
                //             .findAllConnectionsByTripId(tripAsConnection.get().getTripId());
                //     for (Connections connections : connectionsToDelete) {
                //         tripService.deleteConnection(connections.getId());
                //     }
                //     System.out.println("* Trayecto eliminado exitosamente *");
                //     break;
                case 0:
                    executing = false;
                    System.out.println("** Saliendo del modulo de trip **");
                    break;

                default:
                    executing = false;
                    System.out.println("** Saliendo del modulo de trip **");
                    break;
            }
        }
    }

    public void infoTrip(InputVali inputVali) {

        boolean newInput = true;
        System.out.println("Información pais/ciudad/aeropuerto de origen"); // Pedir la información de origen
        showCountries();
        Country originCountry = returnCountry(inputVali);
        showCitiesByCountry(originCountry.getId());
        City originCity = returnCity(inputVali);
        showAirports(originCity.getId());
        Airport originAirport = returnAirport(inputVali);

        System.out.println("Información pais/ciudad/aeropuerto de destino"); // pedir la información de destino
        showCountries();
        Country destinationCountry = returnCountry(inputVali);
        showCitiesByCountry(destinationCountry.getId());
        City destinationCity = returnCity(inputVali);
        showAirports(destinationCity.getId());
        Airport destinationAirport = returnAirport(inputVali);

        String date = inputVali.stringNotNull("Ingrese la fecha del viaje (YYYY-MM-DD): -> "); // Fecha del vuelo
        Double price = inputVali.readDouble("Ingrese el valor del vuelo: -> "); // valor del vuelo
        Trip newTrip = new Trip(date, price); // Con la info creo el nuevo idTrip
        tripService.createTrip(newTrip);

        showAvailablePlanes();
        Plane plane = returnPlane(inputVali);

        Optional<Trip> tripRegistered = tripService.findLastTrip();

        createTripAsConnection(tripRegistered.get().getId(), originAirport.getId(), destinationAirport.getId(),
                inputVali, plane.getId()); // Registro
        // eL origen y destino como connection
        newInput = Register.yesOrNo("Este vuelo tiene escalas? Ingrese el valor numerico 1 (si) o 2(no)");
        if (!newInput) {
            System.out.println("\n* Vuelo registrado *\n");
        } else {
            List<ConnectionDTO> connections = returnAvailableConnections(originAirport.getId(),
                    destinationAirport.getId(),date);
            if (connections.isEmpty()) {
                System.out.println("No hay escalas disponibles");
                System.out.println("\n* Vuelo registrado *\n");
            } else {
                showAvailableConnections(connections);
                System.out.println("Ahora va a añadir vuelo que desea como escala");
                Trip trip = returnTrip(inputVali);
                Optional<ConnectionDTO> tripAsDTO = tripService.findTripAsConnectionByTripId(trip.getId());
                Connections newConnection = new Connections(tripAsDTO.get().getConnectionNumber(),
                        tripRegistered.get().getId(),
                        tripAsDTO.get().getPlaneId(), tripAsDTO.get().getStartAirport(), 2);
                tripService.createConnection(newConnection);
                System.out.println("\n* Vuelo registrado *\n");
            }
        }

    }

    public List<ConnectionDTO> returnAvailableConnections(String idAirOri, String idAirDest, String date) {
        List<ConnectionDTO> connections = tripService.listConnectionsAvailable(idAirOri, idAirDest,date);
        return connections;
    }

    public void showAvailableConnections(List<ConnectionDTO> connections) {
        String format = "| %-10s | %-13s | %-18s | %-13s | %-13s | %-12s | %-12f | %-18s |%n";
        System.out.format(
                "+------------+---------------+--------------------+---------------+---------------+--------------+--------------+--------------------+%n");
        System.out.format(
                "| Trip ID    | Connection ID | Connection Number  | Start Airport | Arrive Airport| Trip Date    | Price        | Airline            |%n");
        System.out.format(
                "+------------+---------------+--------------------+---------------+---------------+--------------+--------------+--------------------+%n");
        for (ConnectionDTO connectionDTO : connections) {
            System.out.format(format,
                    connectionDTO.getTripId(),
                    connectionDTO.getConnectionId(),
                    connectionDTO.getConnectionNumber(),
                    connectionDTO.getStartAirport(),
                    connectionDTO.getArriveAirport(),
                    connectionDTO.getTripDate(),
                    connectionDTO.getPrice(),
                    connectionDTO.getAirlineName());
            System.out.format(
                "+------------+---------------+--------------------+---------------+---------------+--------------+--------------+--------------------+%n");
            // System.out.println(String.format("Airline: %s", connectionDTO.getAirlineName()));
        }
    }

    public void createTripAsConnection(int idTrip, String idOriginAirport, String idDestinationAirport,
            InputVali inputVali, int id_plane) {
        String con_number1 = inputVali
                .stringNotNull("Asigne un connection_number para el origen en este formato CX###; ejm: CX001: -> ");
        String con_number2 = inputVali
                .stringNotNull("Asigne un connection_number para el destino en este formato CX###; ejm: CX001: -> ");
        Connections connectionOrigen = new Connections(con_number1, idTrip, id_plane, idOriginAirport, 1);
        tripService.createConnection(connectionOrigen);
        Connections connectionDestination = new Connections(con_number2, idTrip, null, idDestinationAirport, 3);
        tripService.createConnection(connectionDestination);
    }

    public void updateTrip(Trip trip) {
        boolean newInput;
        InputVali inputVali = new InputVali();
        newInput = Register.yesOrNo("Desea cambiar la fecha del trayecto? Ingrese el valor numérico 1(si) 2(no)");
        if (newInput) {
            String newDate = inputVali.stringNotNull("Ingrese la nueva fecha: -> ");
            trip.setDate(newDate);
        }
        newInput = Register.yesOrNo("Desea cambiar el precio? Ingrese el valor numérico 1(si) 2(no)");
        if (newInput) {
            Double newPrice = inputVali.readDouble(inputVali.stringNotNull("Ingrese el precio: -> "));
            trip.setPrice(newPrice);
        }
        tripService.updateTrip(trip);
    }

    public void asignPlaneToTrip(Trip trip) {
        InputVali inputVali = new InputVali();
        Optional<ConnectionDTO> tripAsConnection = tripService.findTripAsConnectionByTripId(trip.getId());

        System.out.println("Este trayecto ya tiene un avión asignado.\n Seleccione el nuevo avion/aerolinea");
        showAvailablePlanes();
        Plane plane = returnPlane(inputVali);
        Connections connection = tripService.findConnectionById(tripAsConnection.get().getConnectionId()).get();
        connection.setId_plane(plane.getId());
        tripService.updateConnection(connection);
        System.out.println("* Reasignación completa *");

    }

    public void showAvailablePlanes() {
        System.out.println("*** Listado de aviones disponibles ***");
        List<PlaneDTO> planes = tripService.findListPlaneInfo();
        for (PlaneDTO plane : planes) {
            System.out.println(
                    String.format("id: %s,  plates: %s,  capacity: %s,  airline: %s", plane.getId(), plane.getPlates(),
                            plane.getCapacity(), plane.getAirlineName()));
        }
    }

    public void showAirports(String airportId) {
        System.out.println("Aeropuertos disponibles");
        List<Airport> airports = tripService.findAirportsByCity(airportId);
        for (Airport airport : airports) {
            System.out.println(String.format("id: %s, name: %s \n", airport.getId(), airport.getName()));
        }
    }

    public Airport returnAirport(InputVali inputVali) {
        Airport airport = ValidationExist.transformAndValidateObj(
                () -> tripService.findAirport(inputVali.stringNotNull("Ingrese el id del aeropuerto: -> ")));
        return airport;
    }

    public void showCountries() {
        System.out.println("Paises disponibles");
        List<Country> countries = tripService.findAllCountries();
        for (Country country : countries) {
            System.out.println(String.format("id: %s, name: %s \n", country.getId(), country.getName()));
        }
    }

    public Country returnCountry(InputVali inputVali) {
        Country country = ValidationExist.transformAndValidateObj(
                () -> tripService.findCountry(inputVali.stringNotNull("Ingrese el id del país: -> ")));
        return country;
    }

    public void showCitiesByCountry(String id) {
        System.out.println("ciudades disponibles");
        List<City> cities = tripService.findCitiesByCountryId(id);
        for (City city : cities) {
            System.out.println(String.format("id: %s, name: %s \n", city.getId(), city.getName()));
        }
    }

    public City returnCity(InputVali inputVali) {
        City city = ValidationExist.transformAndValidateObj(
                () -> tripService.findCity(inputVali.stringNotNull("Ingrese el id de la ciudad: -> ")));
        return city;
    }

    public Plane returnPlane(InputVali inputVali) {
        Plane plane = ValidationExist.transformAndValidateObj(
                () -> tripService.findPlaneById(
                        inputVali.readInt(("Ingrese el id del avión para asignarlo: -> "))));
        return plane;
    }

    public Trip returnTrip(InputVali inputVali) {
        Trip trip = ValidationExist.transformAndValidateObj(
                () -> tripService
                        .findTripById(inputVali.readInt(("Ingrese el id del trayecto: -> n"))));
        return trip;
    }

    public void showTrips() {
        List<ConnectionDTO> trips = tripService.findAllTripsAsConnectionDTO();
        String format = "| %-10s | %-10s | %-13s | %-18s | %-13s | %-13s | %-12s |%n";
        System.out.format(
                "+------------+------------+---------------+--------------------+---------------+---------------+--------------+%n");
        System.out.format(
                "| Trip ID    | Plane ID   | Connection ID | Connection Number  | Start Airport | Arrive Airport| Trip Date    |%n");
        System.out.format(
                "+------------+------------+---------------+--------------------+---------------+---------------+--------------+%n");

        for (ConnectionDTO trip : trips) {
            System.out.format(format,
                    trip.getTripId(),
                    trip.getPlaneId(),
                    trip.getConnectionId(),
                    trip.getConnectionNumber(),
                    trip.getStartAirport(),
                    trip.getArriveAirport(),
                    trip.getTripDate());
        }

        System.out.format(
                "+------------+------------+---------------+--------------------+---------------+---------------+--------------+%n");
    }

    public void showTrip(Trip trip) {
        Optional<ConnectionDTO> tripAsConnection = tripService.findTripAsConnectionByTripId(trip.getId());
        String format = "| %-10s | %-10s | %-13s | %-18s | %-13s | %-13s | %-12s | %-12f |%n";
        System.out.format(
                "+------------+------------+---------------+--------------------+---------------+---------------+--------------+--------------+%n");
        System.out.format(
                "| Trip ID    | Plane ID   | Connection ID | Connection Number  | Start Airport | Arrive Airport| Trip Date    | Price        |%n");
        System.out.format(
                "+------------+------------+---------------+--------------------+---------------+---------------+--------------+--------------+%n");

        System.out.format(format,
                tripAsConnection.get().getTripId(),
                tripAsConnection.get().getPlaneId(),
                tripAsConnection.get().getConnectionId(),
                tripAsConnection.get().getConnectionNumber(),
                tripAsConnection.get().getStartAirport(),
                tripAsConnection.get().getArriveAirport(),
                tripAsConnection.get().getTripDate(),
                trip.getPrice());
        System.out.format(
                "+------------+------------+---------------+--------------------+---------------+---------------+--------------+--------------+%n");
    }
}
