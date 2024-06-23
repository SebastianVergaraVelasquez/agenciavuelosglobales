package com.fabiansebastianj1.customer.adapters.in;

import java.util.Scanner;

import com.fabiansebastianj1.customer.application.CustomerService;
import com.fabiansebastianj1.customer.domain.models.CustomerDTO;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.ValidationExist;

public class CustomerConsoleAdapter {
    private final CustomerService customerService;

    public CustomerConsoleAdapter(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("*** Modulo de cliente ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            System.out.println("1.Visualizar información del cliente \n2.Salir");
            int choice = scanner.nextInt();
            System.out.println(" ");

            switch (choice) {
                case 1:
                    CustomerDTO showCustomer = ValidationExist.transformAndValidateObj(
                        () -> customerService.findCustomerDTO(inputVali.stringNotNull("Ingrese el id del cliente para conocer su información"))
                    );
                    System.out.println(String.format("id: %s\n name: %s \n age: %s \ndocument_type: %s", showCustomer.getId(),showCustomer.getName(),showCustomer.getAge(),showCustomer.getDocumentType()));
                    break;
                    
                default:
                    break;
            }

//             1. El agente de ventas selecciona la opción "Consultar Cliente" en el menú principal.
// 2. El sistema solicita al agente ingresar el identificador del cliente.
// 3. El agente ingresa el identificador del cliente.
// 4. El sistema busca la información del cliente en la base de datos.
// 5. El sistema muestra la información del cliente.
// Postcondiciones: La información del cliente es mostrada al agente.

        }
    }
}
