package com.fabiansebastianj1.airport.adapters.in;

import com.fabiansebastianj1.airport.application.AirportService;
import com.fabiansebastianj1.airport.domain.models.Airport;
import com.fabiansebastianj1.airport.domain.models.AirportDTO;
import com.fabiansebastianj1.city.domain.models.City;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.ValidationExist;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PlaneConsoleAdapter {

    private final AirportService airportService;

    public PlaneConsoleAdapter(AirportService airportService) {
        this.airportService = airportService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("*** Modulo de Aeropuerto ***");
            System.out.println(" ");
            System.out.println("Que accion desea realizar, digite una opcion numerica");
            System.out.println("1. Registrar Aeropuerto \n2.Consultar Aeropuerto \n3. Salir");
            int choice = scanner.nextInt();
            System.out.println(" ");

            switch (choice) {
                case 1:
                    System.out.println("*** Registro de aeropuerto ***");
                    System.out.println(" ");
                    scanner.nextLine();

                    String airports = verificarAeropuerto(inputVali);

                    String name = inputVali.stringNotNull("Ingrese el nombre del Aeropuerto");

                    mostrarCities();
                    City showCity = ValidationExist.transformAndValidateObj(() -> airportService.findCityById(inputVali.stringNotNull("Ingrese el id de la ciudad a la que pertenece")));
                    String cityId = showCity.getId();

                    Airport newAirport = new Airport(airports, name, cityId);
                    airportService.createAirport(newAirport);
                    break;
                case 2:
                    System.out.println("*** Consulta de aeropuerto ");
                    Airport showAirport = ValidationExist.transformAndValidateObj(
                        () -> airportService.findAirportById(inputVali.stringNotNull("Ingrese el id del aeropuerto"))
                    );
                    showAirportInfo(showAirport.getId());
                    break;
                case 3:
                    executing = false;
                    System.out.println("Saliendo del modulo de Aeropuerto");
                    break;
                default:
                    break;

            }


        }


    }

    public void showAirportInfo(String id){
        Optional<AirportDTO> airport = airportService.findAirportCityById(id);
        AirportDTO airportFinded = airport.get();
        System.out.println(String.format("id: %s"+
        "name: %s"+
        "city: %s", airportFinded.getId(), airportFinded.getName(), airportFinded.getCityName() ));
    }


    public String verificarAeropuerto(InputVali inputVali) {
        String airports;
        while (true) {
            airports = inputVali.stringNotNull("Ingrese identificador del aeropuerto del avión");
            Optional<Airport> airportFinded = airportService.findAirportById(airports);
            if (airportFinded.isPresent()) {
                Airport airport = airportFinded.get();
                System.out.println("Este Aeropuerto ya ha sido registrado");
                System.out.println(String.format("id: %s , airports: %s , cityID: %s", airport.getId(), airport.getName(), airport.getCityId()));
                System.out.println("Ingrese un identificador de Aeropuerto que no se haya registrado");
            } else {
                System.out.println("Este Aeropuerto aún no se ha registado");
                break;
            }
        }
        return airports;
    }

    public void mostrarCities() {
        System.out.println("Lista de ciudades");
        List<City> cities = airportService.findAllCities();
        for (City city : cities) {
            System.out.println(String.format("id: %s , nombre: %s , cityID: %s", city.getId(), city.getName()));
        }
    }


}
