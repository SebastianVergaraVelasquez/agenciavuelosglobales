package com.fabiansebastianj1;

import java.util.Scanner;

import com.fabiansebastianj1.airlines.adapters.out.AirlineMYSQLRepository;
import com.fabiansebastianj1.airport.adapters.in.AirportConsoleAdapter;
import com.fabiansebastianj1.airport.adapters.out.AirportMYSQLRepository;
import com.fabiansebastianj1.airport.application.AirportService;
import com.fabiansebastianj1.airport.infrastructure.AirportRepository;
import com.fabiansebastianj1.city.adapters.out.CityMYSQLRepository;
import com.fabiansebastianj1.city.infrastructure.CityRepository;
import com.fabiansebastianj1.connection.adapters.in.ConnectionConsoleAdapter;
import com.fabiansebastianj1.connection.adapters.out.ConnectionMYSQLRepository;
import com.fabiansebastianj1.connection.application.ConnectionService;
import com.fabiansebastianj1.country.adapters.out.CountryMYSQLRepository;
import com.fabiansebastianj1.country.application.CountryService;
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
import com.fabiansebastianj1.gate.adapters.out.GateMYSQLRepository;
import com.fabiansebastianj1.manufacturers.adapters.out.ManufacturerMYSQLRepository;
import com.fabiansebastianj1.manufacturers.application.ManufacturerService;
import com.fabiansebastianj1.manufacturers.domain.models.Manufacturer;
import com.fabiansebastianj1.model.adapters.out.ModelMYSQLRepository;
import com.fabiansebastianj1.passenger.adapters.out.PassengerMYSQLRepository;
import com.fabiansebastianj1.passenger.domain.models.Passenger;
import com.fabiansebastianj1.pay_type.adapters.out.PayTypeMYSQLRepository;
import com.fabiansebastianj1.payment.adapters.out.PaymentMYSQLRepository;
import com.fabiansebastianj1.planes.adapters.in.PlaneConsoleAdapter;
import com.fabiansebastianj1.planes.adapters.out.PlaneMySQLRepository;
import com.fabiansebastianj1.planes.application.PlaneService;
import com.fabiansebastianj1.print.PrintSeats;
import com.fabiansebastianj1.revemployee.adapters.out.RevEmployeeMYSQLRepository;
import com.fabiansebastianj1.revemployee.infraestructure.RevEmployeeRepository;
import com.fabiansebastianj1.revision.adapters.in.RevisionConsoleAdapter;
import com.fabiansebastianj1.revision.adapters.out.RevisionMYSQLRepository;
import com.fabiansebastianj1.revision.application.RevisionService;
import com.fabiansebastianj1.revisiondetail.adapters.out.RevisioDetailMYSQLRepository;
import com.fabiansebastianj1.status.adapters.out.StatusMYSQLRepository;
import com.fabiansebastianj1.status.application.StatusService;
import com.fabiansebastianj1.status.domain.models.Status;
import com.fabiansebastianj1.trip.adapters.in.TripConsoleAdapter;
import com.fabiansebastianj1.trip.adapters.out.TripMYSQLRepository;
import com.fabiansebastianj1.trip.application.TripService;
import com.fabiansebastianj1.tripbooking.adapters.in.TripBookingConsoleAdapter;
import com.fabiansebastianj1.tripbooking.adapters.in.TripBookingConsoleUtils;
import com.fabiansebastianj1.tripbooking.adapters.out.TripBookingMYSQLRepository;
import com.fabiansebastianj1.tripbooking.application.TripBookingService;
import com.fabiansebastianj1.tripbookingdetails.adapters.in.TripBookingDetailsAdapter;
import com.fabiansebastianj1.tripbookingdetails.adapters.out.TripBookingDetailsMYSQLRepository;
import com.fabiansebastianj1.tripbookingdetails.application.TripBookingDetailsService;
import com.fabiansebastianj1.tripcrew.adapters.in.TripCrewConsoleAdapter;
import com.fabiansebastianj1.tripcrew.adapters.out.TripCrewMYSQLRepository;
import com.fabiansebastianj1.tripcrew.application.TripCrewService;
import com.fabiansebastianj1.tripstatus.adapters.out.TripStatusMYSQLRepository;
import com.fabiansebastianj1.tripulationrole.adapters.out.TripulationRoleMYSQLRepository;
import com.fabiansebastianj1.validations.InputVali;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/agencia";
        String user = "root";
        String password = "Fauroro.95";

        // falta tripbooking y passenger

        AirlineMYSQLRepository airlineMYSQLRepository = new AirlineMYSQLRepository(url, user, password);
        AirportMYSQLRepository airportMYSQLRepository = new AirportMYSQLRepository(url, user, password);
        CityMYSQLRepository cityMYSQLRepository = new CityMYSQLRepository(url, user, password);
        ConnectionMYSQLRepository connectionMYSQLRepository = new ConnectionMYSQLRepository(url, user, password);
        CountryMYSQLRepository countryMYSQLRepository = new CountryMYSQLRepository(url, user, password);
        CustomerMYSQLRepository customerMYSQLRepository = new CustomerMYSQLRepository(url, user, password);
        DocumentTypeMYSQLRepository documentTypeMYSQLRepository = new DocumentTypeMYSQLRepository(url, user, password);
        EmployeeMYSQLRepository employeeMYSQLRepository = new EmployeeMYSQLRepository(url, user, password);
        FareMYSQLRepository fareMYSQLRepository = new FareMYSQLRepository(url, user, password);
        GateMYSQLRepository gateMYSQLRepository = new GateMYSQLRepository(url, user, password);
        ManufacturerMYSQLRepository manufacturerMYSQLRepository = new ManufacturerMYSQLRepository(url, user, password);
        ModelMYSQLRepository modelMYSQLRepository = new ModelMYSQLRepository(url, user, password);
        PlaneMySQLRepository planeMySQLRepository = new PlaneMySQLRepository(url, user, password);
        RevEmployeeMYSQLRepository revEmployeeMYSQLRepository = new RevEmployeeMYSQLRepository(url, user, password);
        RevisionMYSQLRepository revisionMYSQLRepository = new RevisionMYSQLRepository(url, user, password);
        RevisioDetailMYSQLRepository revisioDetailMYSQLRepository = new RevisioDetailMYSQLRepository(url, user,
                password);
        StatusMYSQLRepository statusMYSQLRepository = new StatusMYSQLRepository(url, user, password);
        TripMYSQLRepository tripMYSQLRepository = new TripMYSQLRepository(url, user, password);
        TripBookingDetailsMYSQLRepository tripBookingDetailsMYSQLRepository = new TripBookingDetailsMYSQLRepository(url,
                user, password);
        TripCrewMYSQLRepository tripCrewMYSQLRepository = new TripCrewMYSQLRepository(url, user, password);
        TripStatusMYSQLRepository tripStatusMYSQLRepository = new TripStatusMYSQLRepository(url, user, password);
        TripulationRoleMYSQLRepository tripulationRoleMYSQLRepository = new TripulationRoleMYSQLRepository(url, user,
                password);
        TripBookingMYSQLRepository tripBookingMYSQLRepository = new TripBookingMYSQLRepository(url, user, password);
        PassengerMYSQLRepository passengerMYSQLRepository = new PassengerMYSQLRepository(url, user, password);
        PaymentMYSQLRepository paymentMYSQLRepository = new PaymentMYSQLRepository(url, user, password);
        PayTypeMYSQLRepository payTypeMYSQLRepository = new PayTypeMYSQLRepository(url, user, password);

        RevisionService revisionService = new RevisionService(revisionMYSQLRepository, planeMySQLRepository,
                employeeMYSQLRepository, revEmployeeMYSQLRepository);
        RevisionConsoleAdapter revisionConsoleAdapter = new RevisionConsoleAdapter(revisionService);

        PlaneService planeService = new PlaneService(planeMySQLRepository, statusMYSQLRepository,
                airlineMYSQLRepository, modelMYSQLRepository);
        PlaneConsoleAdapter planeConsoleAdapter = new PlaneConsoleAdapter(planeService);

        FareService fareService = new FareService(fareMYSQLRepository);
        FareConsoleAdapter fareConsoleAdapter = new FareConsoleAdapter(fareService);

        CustomerService customerService = new CustomerService(customerMYSQLRepository, documentTypeMYSQLRepository);
        CustomerConsoleAdapter customerConsoleAdapter = new CustomerConsoleAdapter(customerService);

        DocumentService documentService = new DocumentService(documentTypeMYSQLRepository);
        DocumentTypeConsoleAdapter documentTypeConsoleAdapter = new DocumentTypeConsoleAdapter(documentService);

        ConnectionService connectionService = new ConnectionService(connectionMYSQLRepository, airportMYSQLRepository,
                planeMySQLRepository);
        ConnectionConsoleAdapter connectionConsoleAdapter = new ConnectionConsoleAdapter(connectionService);

        AirportService airportService = new AirportService(airportMYSQLRepository, cityMYSQLRepository);
        AirportConsoleAdapter airportConsoleAdapter = new AirportConsoleAdapter(airportService);

        TripService tripService = new TripService(tripMYSQLRepository, connectionMYSQLRepository, planeMySQLRepository);
        TripConsoleAdapter tripConsoleAdapter = new TripConsoleAdapter(tripService);

        TripCrewService tripCrewService = new TripCrewService(tripCrewMYSQLRepository, connectionMYSQLRepository,
                employeeMYSQLRepository, tripMYSQLRepository);
        TripCrewConsoleAdapter tripCrewConsoleAdapter = new TripCrewConsoleAdapter(tripCrewService);

        TripBookingService tripBookingService = new TripBookingService(tripBookingMYSQLRepository,
                customerMYSQLRepository, connectionMYSQLRepository, fareMYSQLRepository,
                tripBookingDetailsMYSQLRepository, cityMYSQLRepository, airportMYSQLRepository, tripMYSQLRepository,
                documentTypeMYSQLRepository, planeMySQLRepository, passengerMYSQLRepository, paymentMYSQLRepository,
                payTypeMYSQLRepository);
        TripBookingConsoleUtils tripBookingConsoleUtils = new TripBookingConsoleUtils(tripBookingService);
        TripBookingConsoleAdapter tripBookingConsoleAdapter = new TripBookingConsoleAdapter(tripBookingService,
                tripBookingConsoleUtils);

        TripBookingDetailsService tripBookingDetailsService = new TripBookingDetailsService(
                tripBookingDetailsMYSQLRepository);
        TripBookingDetailsAdapter tripBookingDetailsAdapter = new TripBookingDetailsAdapter(tripBookingDetailsService);

        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("\n *** MENU PRINCIPAL AGENCIA VUELOS GLOBALES ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            int choice = inputVali.readInt(
                    ("1. Modulo Aeropuerto? \n2. Modulo Conexiones\n3. Modulo Customer \n4. Modulo Tipo Documento \n5. Modulo Tarifa\n6. Modulo Avion\n7. Modulo Revision\n0. Salir"));
            System.out.println(" ");

            switch (choice) {
                case 1:
                    airportConsoleAdapter.start();
                    break;
                case 2:
                    connectionConsoleAdapter.start();
                    break;
                case 3:
                    customerConsoleAdapter.start();
                    break;
                case 4:
                    documentTypeConsoleAdapter.start();
                    break;
                case 5:
                    fareConsoleAdapter.start();
                    break;
                case 6:
                    planeConsoleAdapter.start();
                    break;
                case 7:
                    revisionConsoleAdapter.start();
                    break;
                case 8:
                    tripConsoleAdapter.start();
                    break;
                case 9:
                    tripCrewConsoleAdapter.start();
                    break;
                case 10:
                    tripBookingConsoleAdapter.bookingCustomerMenu();
                    break;
                case 11:
                    tripBookingConsoleAdapter.start();
                    break;
                case 12:
                    tripBookingDetailsAdapter.start();
                    break;
                default:
                    executing = false;
                    System.out.println("Gracias por usar nuestros servicios");
                    break;
            }
        }

    }
}