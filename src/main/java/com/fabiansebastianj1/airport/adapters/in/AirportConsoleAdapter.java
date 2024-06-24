package com.fabiansebastianj1.airport.adapters.in;

import com.fabiansebastianj1.airport.application.AirportService;
import com.fabiansebastianj1.airport.domain.models.Airport;
import com.fabiansebastianj1.airport.domain.models.AirportDTO;
import com.fabiansebastianj1.city.domain.models.City;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.Register;
import com.fabiansebastianj1.validations.ValidationExist;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AirportConsoleAdapter {

    private final AirportService airportService;

    public AirportConsoleAdapter(AirportService airportService) {
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
            System.out.println(
                    "1. Registrar Aeropuerto \n2.Consultar Aeropuerto \n3.Actualizar Aeropuerto \n4.Eliminar Aeropuerto \n5. Salir");
            int choice = scanner.nextInt();
            System.out.println(" ");

            switch (choice) {
                case 1:
                    System.out.println("*** Registro de aeropuerto ***");
                    System.out.println(" ");
                    scanner.nextLine();

                    String airports = verificarAeropuerto(inputVali);

                    String name = inputVali.stringNotNull("Ingrese el nombre del Aeropuerto");

                    showCities();
                    City showCity = ValidationExist.transformAndValidateObj(() -> airportService
                            .findCityById(inputVali.stringNotNull("Ingrese el id de la ciudad a la que pertenece")));
                    String cityId = showCity.getId();

                    Airport newAirport = new Airport(airports, name, cityId);
                    airportService.createAirport(newAirport);
                    break;
                case 2:
                    System.out.println("*** Consulta de aeropuerto ***");
                    Airport showAirport = returnAirport(inputVali);
                    showAirportInfo(showAirport.getId());
                    break;
                case 3:
                    System.out.println("*** Actualizar aeropuerto ***");
                    updateAirport(inputVali);
                    System.out.println("*** Aeropuerto actualizado ***");
                    break;
                case 4:
                    System.out.println("*** Eliminar aeropuerto ***");
                    Airport airport = returnAirport(inputVali);
                    airportService.deleteAirport(airport.getId());
                    System.out.println("*** Aeropuerto eliminado ***");
                    break;
                case 5:
                    executing = false;
                    System.out.println("Saliendo del modulo de Aeropuerto");
                    break;
                default:
                    break;
            }
        }
    }

    public void updateAirport(InputVali inputVali) {
        boolean newInput;
        String newName;
        Airport airport = returnAirport(inputVali);
        showAirportInfo(airport.getId());
        newInput = Register.yesOrNo("Desea cambiar el nombre del aeropuerto? Ingrese el valor numérico: 1(Sí) o 2(No)");
        if (newInput) {
            newName = inputVali.stringNotNull("Ingrese el nuevo nombre del aeropuerto");
        } else {
            newName = airport.getName();
        }
        Airport updatedAirport = new Airport(airport.getId(), newName, airport.getCityId());
        airportService.updateAirport(updatedAirport);
    }

    public Airport returnAirport(InputVali inputVali) {
        Airport showAirport = ValidationExist.transformAndValidateObj(
                () -> airportService.findAirportById(inputVali.stringNotNull("Ingrese el id del aeropuerto")));
        return showAirport;
    }

    public void showAirportInfo(String id) {
        Optional<AirportDTO> airport = airportService.findAirportCityById(id);
        AirportDTO airportFinded = airport.get();
        System.out.println(String.format("id: %s" +
                "name: %s" +
                "city: %s", airportFinded.getId(), airportFinded.getName(), airportFinded.getCityName()));
    }

    public String verificarAeropuerto(InputVali inputVali) {
        String airports;
        while (true) {
            airports = inputVali.stringNotNull("Ingrese identificador del aeropuerto del avión");
            Optional<Airport> airportFinded = airportService.findAirportById(airports);
            if (airportFinded.isPresent()) {
                Airport airport = airportFinded.get();
                System.out.println("Este Aeropuerto ya ha sido registrado");
                System.out.println(String.format("id: %s , airports: %s , cityID: %s", airport.getId(),
                        airport.getName(), airport.getCityId()));
                System.out.println("Ingrese un identificador de Aeropuerto que no se haya registrado");
            } else {
                System.out.println("Este Aeropuerto aún no se ha registado");
                break;
            }
        }
        return airports;
    }

    public void showCities() {
        System.out.println("Lista de ciudades");
        List<City> cities = airportService.findAllCities();
        for (City city : cities) {
            System.out.println(String.format("id: %s , nombre: %s , cityID: %s", city.getId(), city.getName()));
        }
    }
}
