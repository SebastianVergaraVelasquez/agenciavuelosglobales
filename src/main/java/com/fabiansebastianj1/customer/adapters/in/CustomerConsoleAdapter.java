package com.fabiansebastianj1.customer.adapters.in;

import java.util.List;
import java.util.Scanner;

import com.fabiansebastianj1.customer.application.CustomerService;
import com.fabiansebastianj1.customer.domain.models.Customer;
import com.fabiansebastianj1.customer.domain.models.CustomerDTO;
import com.fabiansebastianj1.documenttype.domain.models.DocumentType;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.ValidationExist;

public class CustomerConsoleAdapter {
    private final CustomerService customerService;

    public CustomerConsoleAdapter(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("*** Modulo de cliente ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            System.out.println(
                    "1.Visualizar información del cliente \n2.Registrar cliente \n3. Actualizar info de cliente \n4.Salir");
            int choice = scanner.nextInt();
            System.out.println(" ");

            switch (choice) {
                case 1:
                    showCustomerInfo(inputVali);
                    break;
                case 2:
                    System.out.println("Registrar cliente");
                    String id = inputVali.stringNotNull("Ingrese la id del cliente");
                    String nombre = inputVali.stringNotNull("Ingrese el nombre del cliente");
                    int edad = inputVali.readInt(inputVali.stringNotNull("Ingrese la edad del cliente"));
                    showDocumentTypes();
                    DocumentType showDocumentType = ValidationExist.transformAndValidateObj(
                            () -> customerService.findDocumentTypeById(
                                    inputVali.readInt(inputVali.stringNotNull("Ingrese el id del tipo de documento"))));

                    Customer newCustomer = new Customer(id, nombre, edad, showDocumentType.getId());
                    customerService.createCustomer(newCustomer);
                    System.out.println("Cliente creado exitosamente");
                    break;

                case 3: 

                    break;
                default:
                    break;
            }
        }
    }   

    public void showCustomerInfo(InputVali inputVali) {
        CustomerDTO showCustomer = ValidationExist.transformAndValidateObj(
                () -> customerService.findCustomerDTO(
                        inputVali.stringNotNull("Ingrese el id del cliente para conocer su información")));
        System.out.println(String.format("id: %s\n name: %s \n age: %s \ndocument_type: %s", showCustomer.getId(),
                showCustomer.getName(), showCustomer.getAge(), showCustomer.getDocumentType()));
    }

    public void showDocumentTypes() {
        System.out.println("Lista de tipos de documento");
        List<DocumentType> documentTypes = customerService.findAllDocumentTypes();
        for (DocumentType documentType : documentTypes) {
            System.out.println(String.format("id: %s, name: %s", documentType.getId(), documentType.getName()));
        }
    }

    public void insertCustomerInfo(InputVali inputVali){

    }
}
