package com.fabiansebastianj1.menus;

import com.fabiansebastianj1.airlines.adapters.out.AirlineMYSQLRepository;
import com.fabiansebastianj1.airport.adapters.in.AirportConsoleAdapter;
import com.fabiansebastianj1.airport.adapters.out.AirportMYSQLRepository;
import com.fabiansebastianj1.airport.application.AirportService;
import com.fabiansebastianj1.city.adapters.out.CityMYSQLRepository;
import com.fabiansebastianj1.connection.adapters.in.ConnectionConsoleAdapter;
import com.fabiansebastianj1.connection.adapters.out.ConnectionMYSQLRepository;
import com.fabiansebastianj1.connection.application.ConnectionService;
import com.fabiansebastianj1.country.adapters.out.CountryMYSQLRepository;
import com.fabiansebastianj1.customer.adapters.in.CustomerConsoleAdapter;
import com.fabiansebastianj1.customer.adapters.out.CustomerMYSQLRepository;
import com.fabiansebastianj1.customer.application.CustomerService;
import com.fabiansebastianj1.documenttype.adapters.in.DocumentTypeConsoleAdapter;
import com.fabiansebastianj1.documenttype.adapters.out.DocumentTypeMYSQLRepository;
import com.fabiansebastianj1.documenttype.application.DocumentService;
import com.fabiansebastianj1.employee.adapters.out.EmployeeMYSQLRepository;
import com.fabiansebastianj1.fare.adapters.in.FareConsoleAdapter;
import com.fabiansebastianj1.fare.adapters.out.FareMYSQLRepository;
import com.fabiansebastianj1.fare.application.FareService;
import com.fabiansebastianj1.model.adapters.out.ModelMYSQLRepository;
import com.fabiansebastianj1.passenger.adapters.out.PassengerMYSQLRepository;
import com.fabiansebastianj1.pay_type.adapters.out.PayTypeMYSQLRepository;
import com.fabiansebastianj1.payment.adapters.out.PaymentMYSQLRepository;
import com.fabiansebastianj1.planes.adapters.in.PlaneConsoleAdapter;
import com.fabiansebastianj1.planes.adapters.out.PlaneMySQLRepository;
import com.fabiansebastianj1.planes.application.PlaneService;
import com.fabiansebastianj1.revemployee.adapters.out.RevEmployeeMYSQLRepository;
import com.fabiansebastianj1.revision.adapters.in.RevisionConsoleAdapter;
import com.fabiansebastianj1.revision.adapters.out.RevisionMYSQLRepository;
import com.fabiansebastianj1.revision.application.RevisionService;
import com.fabiansebastianj1.status.adapters.out.StatusMYSQLRepository;
import com.fabiansebastianj1.trip.adapters.in.TripConsoleAdapter;
import com.fabiansebastianj1.trip.adapters.out.TripMYSQLRepository;
import com.fabiansebastianj1.trip.application.TripService;
import com.fabiansebastianj1.tripbooking.adapters.in.TripBookingConsoleAdapter;
import com.fabiansebastianj1.tripbooking.adapters.in.TripBookingConsoleUtils;
import com.fabiansebastianj1.tripbooking.adapters.out.TripBookingMYSQLRepository;
import com.fabiansebastianj1.tripbooking.application.TripBookingService;
import com.fabiansebastianj1.tripbookingdetails.adapters.out.TripBookingDetailsMYSQLRepository;
import com.fabiansebastianj1.tripcrew.adapters.in.TripCrewConsoleAdapter;
import com.fabiansebastianj1.tripcrew.adapters.out.TripCrewMYSQLRepository;
import com.fabiansebastianj1.tripcrew.application.TripCrewService;
import com.fabiansebastianj1.validations.InputVali;

public class MenuController {

    public void menuAdmin() {

        String url = "jdbc:mysql://localhost:3306/agencia";
        String user = "root";
        String password = "Fauroro.95";

        AirlineMYSQLRepository airlineMYSQLRepository = new AirlineMYSQLRepository(url, user, password);
        AirportMYSQLRepository airportMYSQLRepository = new AirportMYSQLRepository(url, user, password);
        CityMYSQLRepository cityMYSQLRepository = new CityMYSQLRepository(url, user, password);
        ConnectionMYSQLRepository connectionMYSQLRepository = new ConnectionMYSQLRepository(url, user, password);
        CountryMYSQLRepository countryMYSQLRepository = new CountryMYSQLRepository(url, user, password);
        DocumentTypeMYSQLRepository documentTypeMYSQLRepository = new DocumentTypeMYSQLRepository(url, user, password);
        EmployeeMYSQLRepository employeeMYSQLRepository = new EmployeeMYSQLRepository(url, user, password);
        FareMYSQLRepository fareMYSQLRepository = new FareMYSQLRepository(url, user, password);
        ModelMYSQLRepository modelMYSQLRepository = new ModelMYSQLRepository(url, user, password);
        PlaneMySQLRepository planeMySQLRepository = new PlaneMySQLRepository(url, user, password);
        StatusMYSQLRepository statusMYSQLRepository = new StatusMYSQLRepository(url, user, password);
        TripMYSQLRepository tripMYSQLRepository = new TripMYSQLRepository(url, user, password);
        TripCrewMYSQLRepository tripCrewMYSQLRepository = new TripCrewMYSQLRepository(url, user, password);

        PlaneService planeService = new PlaneService(planeMySQLRepository, statusMYSQLRepository,
                airlineMYSQLRepository, modelMYSQLRepository);
        PlaneConsoleAdapter planeConsoleAdapter = new PlaneConsoleAdapter(planeService);

        FareService fareService = new FareService(fareMYSQLRepository);
        FareConsoleAdapter fareConsoleAdapter = new FareConsoleAdapter(fareService);

        DocumentService documentService = new DocumentService(documentTypeMYSQLRepository);
        DocumentTypeConsoleAdapter documentTypeConsoleAdapter = new DocumentTypeConsoleAdapter(documentService);

        ConnectionService connectionService = new ConnectionService(connectionMYSQLRepository, airportMYSQLRepository,
                planeMySQLRepository);
        ConnectionConsoleAdapter connectionConsoleAdapter = new ConnectionConsoleAdapter(connectionService);

        AirportService airportService = new AirportService(airportMYSQLRepository, cityMYSQLRepository);
        AirportConsoleAdapter airportConsoleAdapter = new AirportConsoleAdapter(airportService);

        TripService tripService = new TripService(tripMYSQLRepository, connectionMYSQLRepository, planeMySQLRepository,
                cityMYSQLRepository, countryMYSQLRepository, airportMYSQLRepository);
        TripConsoleAdapter tripConsoleAdapter = new TripConsoleAdapter(tripService);

        TripCrewService tripCrewService = new TripCrewService(tripCrewMYSQLRepository, connectionMYSQLRepository,
                employeeMYSQLRepository, tripMYSQLRepository,planeMySQLRepository);
        TripCrewConsoleAdapter tripCrewConsoleAdapter = new TripCrewConsoleAdapter(tripCrewService);

        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("\n _  _    ___   ___ _____ _  _  _  _  _     _  __       _____ _____ __    _ \r\n" + //
                    "|_|| \\|V| | |\\| | (_  | |_)|_|| \\/ \\|_)   | \\|_ |     (_  | (_  | |_ |V||_|\r\n" + //
                    "| ||_/| |_|_| |_|___) | | \\| ||_/\\_/| \\   |_/|__|__   __)_|___) | |__| || |\r\n" + //
                    "\n");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            int choice = inputVali.readInt(
                    ("1. Modulo Aeropuerto \n2. Modulo Conexiones\n3. Modulo Tipo Documento \n4. Modulo Tarifa\n5. Modulo Avion\n6. Modulo Viaje\n7. Modulo de tripulacion\n0. Salir"));
            System.out.println(" ");

            switch (choice) {
                case 1:
                    airportConsoleAdapter.start();
                    break;
                case 2:
                    connectionConsoleAdapter.start();
                    break;
                case 3:
                    documentTypeConsoleAdapter.start();
                    break;
                case 4:
                    fareConsoleAdapter.start();
                    break;
                case 5:
                    planeConsoleAdapter.start();
                    break;
                case 6:
                    tripConsoleAdapter.start();
                    break;
                case 7:
                    tripCrewConsoleAdapter.start();
                    break;
                case 0:
                    executing = false;
                    System.out.println("Gracias por usar nuestros servicios");
                    break;
                default:
                    System.out.println("Ingrese una opción válida");
                    break;
            }
        }

    }

    public void menuTecnicoMantenimiento() {
        String url = "jdbc:mysql://localhost:3306/agencia";
        String user = "root";
        String password = "Fauroro.95";
        RevEmployeeMYSQLRepository revEmployeeMYSQLRepository = new RevEmployeeMYSQLRepository(url, user, password);
        RevisionMYSQLRepository revisionMYSQLRepository = new RevisionMYSQLRepository(url, user, password);
        EmployeeMYSQLRepository employeeMYSQLRepository = new EmployeeMYSQLRepository(url, user, password);
        PlaneMySQLRepository planeMySQLRepository = new PlaneMySQLRepository(url, user, password);
        RevisionService revisionService = new RevisionService(revisionMYSQLRepository, planeMySQLRepository,
                employeeMYSQLRepository, revEmployeeMYSQLRepository);
        RevisionConsoleAdapter revisionConsoleAdapter = new RevisionConsoleAdapter(revisionService);
        System.out.println("\n___ __ __   ___ __ _     _  __       _    ___ __   ___   ___ __   ___ _ \r\n"
                + //
                " | |_ /  |\\| | /  / \\   | \\|_    |V||_||\\| | |_ |\\| | |V| | |_ |\\| | / \\\r\n" + //
                " | |__\\__| |_|_\\__\\_/   |_/|__   | || || | | |__| |_|_| |_|_|__| | | \\_/\r\n" + //
                "\n");
        revisionConsoleAdapter.start();

    }

    public void menuCliente() {
        String url = "jdbc:mysql://localhost:3306/agencia";
        String user = "root";
        String password = "Fauroro.95";
        CustomerMYSQLRepository customerMYSQLRepository = new CustomerMYSQLRepository(url, user, password);
        TripBookingMYSQLRepository tripBookingMYSQLRepository = new TripBookingMYSQLRepository(url, user, password);
        ConnectionMYSQLRepository connectionMYSQLRepository = new ConnectionMYSQLRepository(url, user, password);
        FareMYSQLRepository fareMYSQLRepository = new FareMYSQLRepository(url, user, password);
        TripBookingDetailsMYSQLRepository tripBookingDetailsMYSQLRepository = new TripBookingDetailsMYSQLRepository(url,
                user, password);
        CityMYSQLRepository cityMYSQLRepository = new CityMYSQLRepository(url, user, password);
        AirportMYSQLRepository airportMYSQLRepository = new AirportMYSQLRepository(url, user, password);
        TripMYSQLRepository tripMYSQLRepository = new TripMYSQLRepository(url, user, password);
        DocumentTypeMYSQLRepository documentTypeMYSQLRepository = new DocumentTypeMYSQLRepository(url, user, password);
        PlaneMySQLRepository planeMySQLRepository = new PlaneMySQLRepository(url, user, password);
        PassengerMYSQLRepository passengerMYSQLRepository = new PassengerMYSQLRepository(url, user, password);
        PaymentMYSQLRepository paymentMYSQLRepository = new PaymentMYSQLRepository(url, user, password);
        PayTypeMYSQLRepository payTypeMYSQLRepository = new PayTypeMYSQLRepository(url, user, password);

        TripBookingService tripBookingService = new TripBookingService(tripBookingMYSQLRepository,
                customerMYSQLRepository, connectionMYSQLRepository, fareMYSQLRepository,
                tripBookingDetailsMYSQLRepository, cityMYSQLRepository, airportMYSQLRepository, tripMYSQLRepository,
                documentTypeMYSQLRepository, planeMySQLRepository, passengerMYSQLRepository, paymentMYSQLRepository,
                payTypeMYSQLRepository);
        TripBookingConsoleUtils tripBookingConsoleUtils = new TripBookingConsoleUtils(tripBookingService);

        TripBookingConsoleAdapter tripBookingConsoleAdapter = new TripBookingConsoleAdapter(tripBookingService,
                tripBookingConsoleUtils);
        System.out.println("\n __   ___ __   ___ __\r\n" + //
                "/  |   | |_ |\\| | |_ \r\n" + //
                "\\__|___|_|__| | | |__\r\n" + //
                "\n");
        tripBookingConsoleAdapter.bookingCustomerMenu(); // cliente - agente de

    }

    public void menuAgenteVentas() {

        String url = "jdbc:mysql://localhost:3306/agencia";
        String user = "root";
        String password = "Fauroro.95";

        DocumentTypeMYSQLRepository documentTypeMYSQLRepository = new DocumentTypeMYSQLRepository(url, user, password);
        CustomerMYSQLRepository customerMYSQLRepository = new CustomerMYSQLRepository(url, user, password);
        CustomerService customerService = new CustomerService(customerMYSQLRepository, documentTypeMYSQLRepository);
        CustomerConsoleAdapter customerConsoleAdapter = new CustomerConsoleAdapter(customerService);
        DocumentService documentService = new DocumentService(documentTypeMYSQLRepository);
        DocumentTypeConsoleAdapter documentTypeConsoleAdapter = new DocumentTypeConsoleAdapter(documentService);
        FareMYSQLRepository fareMYSQLRepository = new FareMYSQLRepository(url, user, password);
        FareService fareService = new FareService(fareMYSQLRepository);
        FareConsoleAdapter fareConsoleAdapter = new FareConsoleAdapter(fareService);
        ConnectionMYSQLRepository connectionMYSQLRepository = new ConnectionMYSQLRepository(url, user, password);
        PlaneMySQLRepository planeMySQLRepository = new PlaneMySQLRepository(url, user, password);
        AirportMYSQLRepository airportMYSQLRepository = new AirportMYSQLRepository(url, user, password);
        ConnectionService connectionService = new ConnectionService(connectionMYSQLRepository, airportMYSQLRepository,
                planeMySQLRepository);
        ConnectionConsoleAdapter connectionConsoleAdapter = new ConnectionConsoleAdapter(connectionService);
        TripCrewMYSQLRepository tripCrewMYSQLRepository = new TripCrewMYSQLRepository(url, user, password);
        EmployeeMYSQLRepository employeeMYSQLRepository = new EmployeeMYSQLRepository(url, user, password);
        TripMYSQLRepository tripMYSQLRepository = new TripMYSQLRepository(url, user, password);
        CityMYSQLRepository cityMYSQLRepository = new CityMYSQLRepository(url, user, password);
        PassengerMYSQLRepository passengerMYSQLRepository = new PassengerMYSQLRepository(url, user, password);
        PaymentMYSQLRepository paymentMYSQLRepository = new PaymentMYSQLRepository(url, user, password);
        PayTypeMYSQLRepository payTypeMYSQLRepository = new PayTypeMYSQLRepository(url, user, password);

        TripCrewService tripCrewService = new TripCrewService(tripCrewMYSQLRepository, connectionMYSQLRepository,
                employeeMYSQLRepository, tripMYSQLRepository, planeMySQLRepository);
        TripCrewConsoleAdapter tripCrewConsoleAdapter = new TripCrewConsoleAdapter(tripCrewService);
        TripBookingMYSQLRepository tripBookingMYSQLRepository = new TripBookingMYSQLRepository(url, user, password);
        TripBookingDetailsMYSQLRepository tripBookingDetailsMYSQLRepository = new TripBookingDetailsMYSQLRepository(url,
                user, password);
        TripBookingService tripBookingService = new TripBookingService(tripBookingMYSQLRepository,
                customerMYSQLRepository, connectionMYSQLRepository, fareMYSQLRepository,
                tripBookingDetailsMYSQLRepository, cityMYSQLRepository, airportMYSQLRepository, tripMYSQLRepository,
                documentTypeMYSQLRepository, planeMySQLRepository, passengerMYSQLRepository, paymentMYSQLRepository,
                payTypeMYSQLRepository);
        TripBookingConsoleUtils tripBookingConsoleUtils = new TripBookingConsoleUtils(tripBookingService);

        TripBookingConsoleAdapter tripBookingConsoleAdapter = new TripBookingConsoleAdapter(tripBookingService,
                tripBookingConsoleUtils);
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("\n _  __ __   ___ __    _  __       __   ___ _  __\r\n" + //
                    "|_|/__|_ |\\| | |_    | \\|_    \\ /|_ |\\| | |_|(_ \r\n" + //
                    "| |\\_||__| | | |__   |_/|__    V |__| | | | |__)\n");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            int choice = inputVali.readInt(
                    ("1. Modulo de cliente\n2. Consultas Tipo Documento \n3. Consultar Tarifa de vuelo\n4. Consultar escalas del trayecto\n5. 'Los de trip_bookinmg machacos XD' \n6. Consultar asignacion de tripulacion\n0. Salir"));
            System.out.println(" ");

            switch (choice) {
                case 1:
                    customerConsoleAdapter.start();
                    break;
                case 2:
                    documentTypeConsoleAdapter.startMenuCliente();
                    break;
                case 3:
                    fareConsoleAdapter.startMenuCliente();
                    break;
                case 4:
                    connectionConsoleAdapter.startMenuCliente();
                    break;
                case 5:
                    tripBookingConsoleAdapter.bookingCustomerMenu(); // cliente - agente de
                    break;
                case 6:
                    tripCrewConsoleAdapter.startMenuCliente();
                    break;
                case 0:
                    executing = false;
                    System.out.println("Gracias por usar nuestros servicios");
                    break;
                default:
                    System.out.println("Ingrese una opción válida");
                    break;
            }
        }

    }
}