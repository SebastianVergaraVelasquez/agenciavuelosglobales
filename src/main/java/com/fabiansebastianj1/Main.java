package com.fabiansebastianj1;

import com.fabiansebastianj1.manufacturers.adapters.out.ManufacturerMYSQLRepository;
import com.fabiansebastianj1.manufacturers.application.ManufacturerService;
import com.fabiansebastianj1.manufacturers.domain.models.Manufacturer;
import com.fabiansebastianj1.planes.adapters.in.PlaneConsoleAdapter;
import com.fabiansebastianj1.planes.adapters.out.PlaneMySQLRepository;
import com.fabiansebastianj1.planes.application.PlaneService;
import com.fabiansebastianj1.status.adapters.out.StatusMYSQLRepository;
import com.fabiansebastianj1.status.application.StatusService;
import com.fabiansebastianj1.status.domain.models.Status;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/agencia";
        String user = "root";
        String password = "123456";

        // PlaneMySQLRepository planeMySQLRepository = new PlaneMySQLRepository(url, user, password);
        // PlaneService planeService = new PlaneService(planeMySQLRepository);
        // PlaneConsoleAdapter planeConsoleAdapter = new PlaneConsoleAdapter(planeService);
        // planeConsoleAdapter.start();

        // ManufacturerMYSQLRepository manufacturerMYSQLRepository = new ManufacturerMYSQLRepository(url, user, password);
        // ManufacturerService manufacturerService = new ManufacturerService(manufacturerMYSQLRepository);
        // Manufacturer manufacturer = new Manufacturer("Owo");
        // manufacturerService.createManufacturer(manufacturer);

        StatusMYSQLRepository statusMYSQLRepository = new StatusMYSQLRepository(url, user, password);
        StatusService statusService = new StatusService(statusMYSQLRepository);
        // Status status = new Status("ok");
        statusService.deleteStatus(1);
    }
}