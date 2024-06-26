package com.fabiansebastianj1;

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
import com.fabiansebastianj1.documenttype.adapters.out.DocumentTypeMYSQLRepository;
import com.fabiansebastianj1.employee.adapters.out.EmployeeMYSQLRepository;
import com.fabiansebastianj1.fare.adapters.out.FareMYSQLRepository;
import com.fabiansebastianj1.gate.adapters.out.GateMYSQLRepository;
import com.fabiansebastianj1.manufacturers.adapters.out.ManufacturerMYSQLRepository;
import com.fabiansebastianj1.manufacturers.application.ManufacturerService;
import com.fabiansebastianj1.manufacturers.domain.models.Manufacturer;
import com.fabiansebastianj1.model.adapters.out.ModelMYSQLRepository;
import com.fabiansebastianj1.planes.adapters.in.PlaneConsoleAdapter;
import com.fabiansebastianj1.planes.adapters.out.PlaneMySQLRepository;
import com.fabiansebastianj1.planes.application.PlaneService;
import com.fabiansebastianj1.revemployee.adapters.out.RevEmployeeMYSQLRepository;
import com.fabiansebastianj1.revision.adapters.out.RevisionMYSQLRepository;
import com.fabiansebastianj1.revisiondetail.adapters.out.RevisioDetailMYSQLRepository;
import com.fabiansebastianj1.status.adapters.out.StatusMYSQLRepository;
import com.fabiansebastianj1.status.application.StatusService;
import com.fabiansebastianj1.status.domain.models.Status;
import com.fabiansebastianj1.trip.adapters.out.TripMYSQLRepository;
import com.fabiansebastianj1.tripbookingdetails.adapters.out.TripBookingDetailsMYSQLRepository;
import com.fabiansebastianj1.tripcrew.adapters.out.TripCrewMYSQLRepository;
import com.fabiansebastianj1.tripstatus.adapters.out.TripStatusMYSQLRepository;
import com.fabiansebastianj1.tripulationrole.adapters.out.TripulationRoleMYSQLRepository;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/agencia";
        String user = "root";
        String password = "Fauroro.95";

        //falta tripbooking y passenger

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
        RevisioDetailMYSQLRepository revisioDetailMYSQLRepository  = new RevisioDetailMYSQLRepository(url, user, password);
        StatusMYSQLRepository statusMYSQLRepository = new StatusMYSQLRepository(url, user, password);
        TripMYSQLRepository tripMYSQLRepository = new TripMYSQLRepository(url, user, password);
        TripBookingDetailsMYSQLRepository tripBookingDetailsMYSQLRepository  = new TripBookingDetailsMYSQLRepository(url, user, password);
        TripCrewMYSQLRepository tripCrewMYSQLRepository = new TripCrewMYSQLRepository(url, user, password);
        TripStatusMYSQLRepository tripStatusMYSQLRepository = new TripStatusMYSQLRepository(url,user,password);
        TripulationRoleMYSQLRepository tripulationRoleMYSQLRepository = new TripulationRoleMYSQLRepository(url,user,password);

        
        
        CustomerService customerService = new CustomerService(customerMYSQLRepository, documentTypeMYSQLRepository);
        CustomerConsoleAdapter customerConsoleAdapter = new CustomerConsoleAdapter(customerService);
        customerConsoleAdapter.start();

        
        
        
        
        // ConnectionService connectionService = new ConnectionService(connectionMYSQLRepository, airportMYSQLRepository, planeMySQLRepository);
        // ConnectionConsoleAdapter connectionConsoleAdapter = new ConnectionConsoleAdapter(connectionService);
        // connectionConsoleAdapter.start();
        
        // AirportService airportService = new AirportService(airportMYSQLRepository, cityMYSQLRepository);
        // AirportConsoleAdapter airportConsoleAdapter = new AirportConsoleAdapter(airportService);
        // airportConsoleAdapter.start();

        // PlaneService planeService = new PlaneService(planeMySQLRepository, statusMYSQLRepository,airlineMYSQLRepository,modelMYSQLRepository );
        // PlaneConsoleAdapter planeConsoleAdapter = new PlaneConsoleAdapter(planeService);
        // planeConsoleAdapter.start();

        // ManufacturerService manufacturerService = new ManufacturerService(manufacturerMYSQLRepository);
        // Manufacturer manufacturer = new Manufacturer("Owo");
        // manufacturerService.createManufacturer(manufacturer);

        // StatusService statusService = new StatusService(statusMYSQLRepository);
        // Status status = new Status("fail");
        // statusService.createStatus(status);
    }
}