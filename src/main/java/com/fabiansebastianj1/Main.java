package com.fabiansebastianj1;

import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.menus.*;

public class Main {
    public static void main(String[] args) {

        boolean executing = true;
        InputVali inputVali = new InputVali();

        MenuController menuController = new MenuController();

        while (executing) {
            System.out.println(
                    "\n\ndb    db db    db d88888b db       .d88b.  .d8888.      d888b  db       .d88b.  d8888b.  .d8b.  db      d88888b .d8888. \r\n"
                            + //
                            "88    88 88    88 88'     88      .8P  Y8. 88'  YP     88' Y8b 88      .8P  Y8. 88  `8D d8' `8b 88      88'     88'  YP \r\n"
                            + //
                            "Y8    8P 88    88 88ooooo 88      88    88 `8bo.       88      88      88    88 88oooY' 88ooo88 88      88ooooo `8bo.   \r\n"
                            + //
                            "`8b  d8' 88    88 88~~~~~ 88      88    88   `Y8b.     88  ooo 88      88    88 88~~~b. 88~~~88 88      88~~~~~   `Y8b. \r\n"
                            + //
                            " `8bd8'  88b  d88 88.     88booo. `8b  d8' db   8D     88. ~8~ 88booo. `8b  d8' 88   8D 88   88 88booo. 88.     db   8D \r\n"
                            + //
                            "   YP    Y88888P' Y88888P Y88888P  `Y88P'  `8888Y'      Y888P  Y88888P  `Y88P'  Y8888P' YP   YP Y88888P Y88888P `8888Y' \n\n");
            System.out.println("Seleccione El Rol con el cual esta registrado");
            int choice = inputVali.readInt(
                    "1. Administrador del sistema \n2. Agente de ventas \n3. Tecnico de Mantenimiento \n4. Cliente\n0. Salir");
            System.out.println(" ");

            switch (choice) {
                case 1:
                    menuController.menuAdmin();
                    break;
                case 2:
                    menuController.menuAgenteVentas();
                    break;
                case 3:
                    menuController.menuTecnicoMantenimiento();
                    break;
                case 4:
                    menuController.menuCliente();
                    break;
                case 0:
                    executing = false;
                    System.out.println("Gracias por usar nuestros servicios\n");
                    break;
                default:
                    System.out.println("Ingrese una opción válida");
                    break;
            }
        }

    }
}