package com.fabiansebastianj1.fare.adapters.in;

import java.util.List;

import com.fabiansebastianj1.fare.application.FareService;
import com.fabiansebastianj1.fare.domain.models.Fare;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.Register;
import com.fabiansebastianj1.validations.ValidationExist;

public class FareConsoleAdapter {
    private final FareService fareService;

    public FareConsoleAdapter(FareService fareService) {
        this.fareService = fareService;
    }

    public void start() {
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("*** Modulo de tarifas ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            int choice = inputVali.readInt(
                    "1. Registrar Tarifa \n2. Actualizar Tarifa \n3. Consultar Tarifa \n4. Eliminar tarifa \n0. Salir");
            System.out.println(" ");

            switch (choice) {
                case 1:
                    System.out.println("*** Registrar tarifa ***");
                    newFareInfo(inputVali);
                    System.out.println("\n* Tarifa registrada exitosamente *\n");
                    break;
                case 2:
                    System.out.println("*** Actualizar tarifa ***");
                    System.out.println(" ");
                    System.out.println("Lista de tarifas");
                    showFares();
                    Fare fareToUpdate = returnFare(inputVali);
                    showFareInfo(fareToUpdate);
                    updateFare(fareToUpdate, inputVali);
                    System.out.println("* Tarifa actualizada exitosamente *\n");
                    break;
                case 3:
                    System.out.println("*** Consultar tarifa ***");
                    Fare showFare = returnFare(inputVali);
                    showFareInfo(showFare);
                    break;
                case 4:
                    System.out.println("*** Eliminar tarifa ***");
                    System.out.println(" ");
                    System.out.println("Lista de tarifas");
                    showFares();
                    Fare fareToDelete = returnFare(inputVali);
                    fareService.deleteFare(fareToDelete.getId());
                    System.out.println("\n* Tarifa eliminada exitosamente *\n");
                    break;
                case 0:
                    executing = false;
                    System.out.println("Saliendo del modulo de tarifas");
                    break;
                default:
                    System.out.println("Ingrese una opción válida");
                    break;
            }
        }
    }

    public void startMenuCliente() {
        InputVali inputVali = new InputVali();

        System.out.println("*** Consultar tarifa ***");
        Fare showFare = returnFare(inputVali);
        showFareInfo(showFare);

    }

    public void updateFare(Fare fare, InputVali inputVali) {
        boolean newInput;
        newInput = Register.yesOrNo("Desea cambiar el nombre de la tarifa? Ingrese el valor numérico: " +
                "1 (si) 2 (no)");
        if (newInput) {
            String newName = inputVali.stringNotNull("Ingrese el nombre de la tarifa: -> ");
            fare.setDescription(newName);
        }
        newInput = Register.yesOrNo("Desea cambiar el detalle de la tarifa? Ingrese el valor numérico: " +
                "1 (si) 2 (no)");
        if (newInput) {
            String newDetail = inputVali.stringNotNull("Ingrese el detalle de la tarifa: -> ");
            fare.setDetail(newDetail);
        }
        newInput = Register.yesOrNo("Desea cambiar el valor de la tarifa? Ingrese el valor numérico: " +
                "1 (si) 2 (no)");
        if (newInput) {
            Double newValue = inputVali.readDouble(inputVali.stringNotNull("Ingrese el valor de la tarifa: -> "));
            fare.setValue(newValue);
        }
        fareService.updateFare(fare);
    }

    public void newFareInfo(InputVali inputVali) {
        String description = inputVali.stringNotNull("Ingrese el nombre de la tarifa: -> ");
        String detail = inputVali.stringNotNull("Ingrese el detalle de la tarifa: -> ");
        double value = inputVali.readDouble(inputVali.stringNotNull("Ingrese el valor de la tarifa: -> "));
        Fare newFare = new Fare(description, detail, value);
        fareService.createFare(newFare);
    }

    public Fare returnFare(InputVali inputVali) {
        Fare searchedFare = ValidationExist.transformAndValidateObj(
                () -> fareService
                        .findFareById(inputVali.readInt("Ingrese la id de la tarifa: -> ")));
        return searchedFare;
    }

    public void showFareInfo(Fare fare) {
        System.out.println(String.format("id: %s \n" +
                "name: %s \n" +
                "detail: %s \n" +
                "value: %s \n", fare.getId(), fare.getDescription(), fare.getDetail(), fare.getValue()));
    }

    public void showFares() {
        List<Fare> fares = fareService.findAllFares();
        for (Fare fare : fares) {
            System.out.println(String.format("id: %s \n" +
                    "name: %s \n" +
                    "detail: %s \n" +
                    "value: %s \n", fare.getId(), fare.getDescription(), fare.getDetail(), fare.getValue()));
        }
    }
}
