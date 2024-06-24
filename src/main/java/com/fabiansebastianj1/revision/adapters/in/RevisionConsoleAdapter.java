package com.fabiansebastianj1.revision.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.fabiansebastianj1.employee.domain.models.Employee;
import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.revemployee.domain.models.RevEmployee;
import com.fabiansebastianj1.revision.application.RevisionService;
import com.fabiansebastianj1.revision.domain.models.Revision;
import com.fabiansebastianj1.revision.domain.models.RevisionDTO;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.ValidationExist;

public class RevisionConsoleAdapter {

    private final RevisionService revisionService;

    public RevisionConsoleAdapter(RevisionService revisionService) {
        this.revisionService = revisionService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("*** Modulo de Revisión de aviones ***");
            System.out.println(" ");
            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            System.out.println("1.Registrar revisión \n2.Salir");
            int choice = scanner.nextInt();
            System.out.println(" ");

            switch (choice) {
                case 1:
                    mostrarAviones();
                    Plane showPlane = ValidationExist.transformAndValidateObj(
                            () -> revisionService.findPlaneById(
                                    inputVali.readInt(inputVali.stringNotNull("Ingrese el id del avión"))));
                    int planeId = showPlane.getId();
                    String revisionDate = inputVali.stringNotNull("Ingrese la fecha de la revisión");
                    String description = inputVali.stringNotNull("Ingrese una descripción del procedimiento");

                    Employee showEmployee = ValidationExist.transformAndValidateObj(
                            () -> revisionService.findEmployeeById(inputVali.stringNotNull("Ingrese el id del avión")));
                    String employeeId = showEmployee.getId();

                    // Registrar en revision
                    Revision newRevision = new Revision(revisionDate, planeId);
                    revisionService.createRevision(newRevision);

                    // Encontrar el registro hecho
                    Optional<Revision> revisionSaved = revisionService.findLastRevision();
                    Revision lastRevision = revisionSaved.get();
                    int revisionId = lastRevision.getId();

                    // registrar en revEmployee
                    RevEmployee newRevEmployee = new RevEmployee(employeeId, revisionId, description);
                    revisionService.createRevEmployee(newRevEmployee);

                    System.out.println("Registro de revisión guardado exitosamente");
                    break;
                case 2:
                    System.out.println("*** Consultar historial de revision ***");

                    //consultar avion por plate, luego extraer de ahí el id y pasarlo a la busqueda de revisión
                    Plane planeFinded = ValidationExist.transformAndValidateObj(
                        ()-> revisionService.findPlaneByPlates(inputVali.stringNotNull("Ingrese la id del avion"))
                    );
                    //Consultar si el avión buscado tiene revisiones
                    showRevisionsByPlaneId(planeFinded.getId());
                    break;
                case 3:
                    System.out.println("Saliendo del modulo de revisión");
                    executing = false;
                    break;

                default:
                    System.out.println("Ingrese una opción válida");
                    break;
            }
        }
    }

    public void showRevisionsByPlaneId(int id){
        System.out.println("Historial de revisiones");
        List<RevisionDTO> revisions = revisionService.revisionsByPlaneId(id);
        if (!revisions.isEmpty()) {
            for (RevisionDTO revisionDTO : revisions) {
                System.out.println(String.format("id: %s \n"+
                "date: %s \n"+
                "planeId: %s \n"+
                "description: %s \n"+
                "tecnico: %s \n\n", revisionDTO.getId(),revisionDTO.getRevisionDate(),revisionDTO.getPlaneId(),revisionDTO.getDescription(),revisionDTO.getEmployeeName()));
            }
        }
        else{System.out.println("Este avión no tiene revisiones \n");}
    }

    public void mostrarAviones() {
        System.out.println("Listado de aviones");
        List<Plane> planes = revisionService.listPlanes();
        for (Plane plane : planes) {
            System.out.println(String.format("id: %s, plates: %s", plane.getId(), plane.getPlates()));
        }
    }
}
