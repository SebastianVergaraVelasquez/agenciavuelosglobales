package com.fabiansebastianj1.customer.adapters.in;

import java.util.List;
import java.util.Scanner;

import com.fabiansebastianj1.customer.application.CustomerService;
import com.fabiansebastianj1.customer.domain.models.Customer;
import com.fabiansebastianj1.customer.domain.models.CustomerDTO;
import com.fabiansebastianj1.documenttype.domain.models.DocumentType;
import com.fabiansebastianj1.validations.*;
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
                    System.out.println("Consultar cliente");
                    CustomerDTO showCustomer = returnCustomerDTO(inputVali);
                    showCustomerInfo(showCustomer);
                    break;
                case 2:
                    System.out.println("Registrar cliente");
                    insertCustomerInfo(inputVali);
                    break;
                case 3:
                    updateCustomerInfo(inputVali);
                    break;
                default:
                    break;
            }
        }
    }

    public void updateCustomerInfo(InputVali inputVali){
        boolean newInput;
        String newName;
        int newAge, newIdDocumentType;
        Customer showCustomer = returnCustomer(inputVali);
        //verificar si desea cambiar el nombre
        newInput = Register.yesOrNo( "Desea cambiar el nombre del cliente? Ingrese el valor numerico 1 (si) o 2(no)");
        if (newInput == true){newName = inputVali.stringNotNull("Ingrese el nuevo nombre");}
        else{newName = showCustomer.getName();}
        //verificar si desea cambiar la edad
        newInput = Register.yesOrNo( "Desea cambiar la edad del cliente? Ingrese el valor numerico 1 (si) o 2(no)");
        if(newInput == true){newAge = inputVali.readInt(inputVali.stringNotNull("Ingrese la edad"));}
        else{newAge = showCustomer.getAge();}
        //verificar si desea cambiar el tipo de documento
        newInput = Register.yesOrNo( "Desea el tipo de documento? Ingrese el valor numerico 1 (si) o 2(no)");
        if(newInput == true){
            showDocumentTypes();
            DocumentType showDocumentType = ValidationExist.transformAndValidateObj(
                () -> customerService.findDocumentTypeById(
                        inputVali.readInt(inputVali.stringNotNull("Ingrese el id del tipo de documento"))));
            newIdDocumentType = showDocumentType.getId();
        }
        else{newIdDocumentType = showCustomer.getDocumentTypeId();}
        Customer updaCustomerDTO = new Customer(showCustomer.getId(),newName,newAge,newIdDocumentType);
        customerService.updateCustomer(updaCustomerDTO);
    }

    //Este me devuelve el cliente con el nombre de su tipo de documento
    public CustomerDTO returnCustomerDTO(InputVali inputVali) {
        CustomerDTO showCustomer = ValidationExist.transformAndValidateObj(
                () -> customerService.findCustomerDTO(
                        inputVali.stringNotNull("Ingrese el id del cliente para conocer su información")));
        return showCustomer;
    }

    //Este me devuelve el cliente con el id de su tipo de documento
    public Customer returnCustomer(InputVali inputVali) {
        Customer showCustomer = ValidationExist.transformAndValidateObj(
                () -> customerService.findCustomerById(
                        inputVali.stringNotNull("Ingrese el id del cliente para conocer su información")));
        return showCustomer;
    }

    public void showCustomerInfo(CustomerDTO customer) {
        System.out.println(String.format("id: %s\n name: %s \n age: %s \ndocument_type: %s", customer.getId(),
                customer.getName(), customer.getAge(), customer.getDocumentType()));
    }

    public void showDocumentTypes() {
        System.out.println("Lista de tipos de documento");
        List<DocumentType> documentTypes = customerService.findAllDocumentTypes();
        for (DocumentType documentType : documentTypes) {
            System.out.println(String.format("id: %s, name: %s", documentType.getId(), documentType.getName()));
        }
    }

    public void insertCustomerInfo(InputVali inputVali) {
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
    }
}
