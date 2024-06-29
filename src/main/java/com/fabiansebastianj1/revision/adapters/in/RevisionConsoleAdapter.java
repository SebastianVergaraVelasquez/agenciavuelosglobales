package com.fabiansebastianj1.revision.adapters.in;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.employee.domain.models.Employee;
import com.fabiansebastianj1.employee.domain.models.EmployeeDTO;
import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.revemployee.domain.models.RevEmployee;
import com.fabiansebastianj1.revision.application.RevisionService;
import com.fabiansebastianj1.revision.domain.models.Revision;
import com.fabiansebastianj1.revision.domain.models.RevisionDTO;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.Register;
import com.fabiansebastianj1.validations.ValidationExist;

public class RevisionConsoleAdapter {

    private final RevisionService revisionService;

    public RevisionConsoleAdapter(RevisionService revisionService) {
        this.revisionService = revisionService;
    }

    public void start() {
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {

            System.out.println("Qué acción desea realizar, digite una opcion numérica");
            int choice = inputVali.readInt(
                    "1. Registrar revisión \n2. Consultar historial de revision\n3. Actualizar Informacion de Revision\n4. Eliminar Revision\n0. Salir");
            System.out.println(" ");

            switch (choice) {
                case 1:
                    System.out.println("*** Registrar revisión ***\n");
                    mostrarAviones();
                    Plane showPlane = ValidationExist.transformAndValidateObj(
                            () -> revisionService.findPlaneById(
                                    inputVali.readInt(("Ingrese el id del avión: -> "))));
                    int planeId = showPlane.getId();
                    String revisionDate = inputVali
                            .stringNotNull("Ingrese la fecha de la revisión en formato yyyy-MM-dd: -> ");
                    String description = inputVali.stringNotNull("Ingrese una descripción del procedimiento: -> ");
                    List<EmployeeDTO> employees = revisionService.findTechniciansByAirline(showPlane.getAirlineId());
                    showEmployes(employees);
                    Employee showEmployee = ValidationExist.transformAndValidateObj(
                            () -> revisionService.findEmployeeById(inputVali
                                    .stringNotNull("Ingrese el id del empleado que realizo la revision: -> ")));
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

                    System.out.println("* Registro de revisión guardado exitosamente *");
                    break;
                case 2:
                    System.out.println("*** Consultar historial de revision ***");

                    // consultar avion por plate, luego extraer de ahí el id y pasarlo a la busqueda
                    // de revisión
                    Plane planeFinded = ValidationExist.transformAndValidateObj(
                            () -> revisionService
                                    .findPlaneByPlates(inputVali.stringNotNull("Ingrese la placa del avion: -> ")));
                    // Consultar si el avión buscado tiene revisiones
                    showRevisionsByPlaneId(planeFinded.getId());
                    break;
                case 3:
                    System.out.println("*** Actualizar Informacion de Revision ***");
                    System.out.println(" ");
                    System.out.println("* Informacion actual de la revision *");
                    Revision showRevision = ValidationExist.transformAndValidateObj(
                            () -> revisionService.findRevisionById(
                                    inputVali.readInt(("Ingrese el Id de la revision que desea editar: -> "))));
                    showRevisionsByPlaneId(showRevision.getPlaneId());
                    updateRevision(showRevision);

                    break;
                case 4:
                    System.out.println("*** Eliminar Revision ***");
                    System.out.println(" ");
                    List<Revision> revisions = revisionService.findAllRevisions();
                    String leftAlignFormat = "| %-4d | %-10s | %-8d |%n";
                    System.out.format("+------+------------+----------+%n");
                    System.out.format("| ID   | Date       | Id_Plane |%n");
                    System.out.format("+------+------------+----------+%n");

                    for (Revision revision : revisions) {
                        System.out.format(leftAlignFormat, revision.getId(), revision.getRevisionDate(),
                                revision.getPlaneId());
                    }

                    System.out.format("+------+------------+----------+%n");

                    Revision showRevisions = ValidationExist.transformAndValidateObj(
                            () -> revisionService.findRevisionById(
                                    inputVali.readInt(("Ingrese el ID de la revision que desea eliminar: -> "))));

                    revisionService.deleteRevEmployee(showRevisions.getId());
                    revisionService.deleteRevision(showRevisions.getId());
                    System.out.println("* Registro de revisión eliminado exitosamente *");

                    break;
                case 0:
                    System.out.println("Saliendo del modulo de revisión");
                    executing = false;
                    break;
                default:
                    System.out.println("Ingrese una opción válida");
                    break;
            }
        }
    }

    public void showRevisionsByPlaneId(int id) {
        System.out.println("*** Historial de revisiones ***");
        List<RevisionDTO> revisions = revisionService.revisionsByPlaneId(id);
        if (!revisions.isEmpty()) {
            for (RevisionDTO revisionDTO : revisions) {
                System.out.println(String.format("id: %s \n" +
                        "date: %s \n" +
                        "planeId: %s \n" +
                        "tecnico: %s \n" +
                        "description: %s \n\n", revisionDTO.getId(), revisionDTO.getRevisionDate(),
                        revisionDTO.getPlaneId(), revisionDTO.getDescription(), revisionDTO.getEmployeeName()));
            }
        } else {
            System.out.println("Este avión no tiene revisiones \n");
        }
    }

    public void mostrarAviones() {
        System.out.println("*** Listado de aviones ***");
        List<Plane> planes = revisionService.listPlanes();
        for (Plane plane : planes) {
            System.out.println(String.format("id: %s,  plates: %s", plane.getId(), plane.getPlates()));
        }
    }

    public void updateRevision(Revision showRevision) {
        InputVali inputVali = new InputVali();
        boolean newInput;
        String newDate;
        String newDescription;
        String newEmployeeId;
        newInput = Register.yesOrNo("Desea cambiar la fecha de la revision? Ingrese el valor numerico 1 (si) o 2(no)");
        if (newInput == true) {
            newDate = inputVali.stringNotNull("Ingrese la nueva fecha: -> ");
            showRevision.setRevisionDate(newDate);
            revisionService.updateRevision(showRevision);
        }
        newInput = Register
                .yesOrNo("Desea cambiar la descripcion de la revision? Ingrese el valor numerico 1 (si) o 2(no)");
        Optional<RevEmployee> revEmployee = revisionService.findRevEmployeeById(showRevision.getId());
        if (newInput == true) {
            newDescription = inputVali.stringNotNull("Ingrese la nueva descripcion: -> ");
            revEmployee.get().setDescription(newDescription);
            revisionService.updateRevEmploye(revEmployee.get());
        }
        newInput = Register
                .yesOrNo("Desea cambiar el empleado de la revision? Ingrese el valor numerico 1 (si) o 2(no)");
        if (newInput == true) {
            Optional<Plane> plane = revisionService.findPlaneById(showRevision.getPlaneId()); //tomar el avión y extraer el idAirline
            List<EmployeeDTO> employees = revisionService.findTechniciansByAirline(plane.get().getAirlineId()); //Encontrar tecnicos
            if (!employees.isEmpty()) {
                showEmployes(employees);
                newEmployeeId = inputVali.stringNotNull("Ingrese el Id del empleado que quiera seleccionar: -> ");
                revEmployee.get().setId_employee(newEmployeeId);
                revisionService.updateRevEmploye(revEmployee.get());  
            }
            else{
                System.out.println("No hay técnicos para asignar");
            }
        }
    }



    public void showEmployes(List<EmployeeDTO> technicians){
        
        System.out.printf("%-10s %-20s %-10s %-20s %-15s %-10s %-20s %-10s %-10s %-20s%n",
    "ID", "Nombre", "ID Rol", "Nombre Rol", "Fecha Ingreso", "ID Aerolínea", "Nombre Aerolínea", "ID Aeropuerto", "ID Ciudad", "Nombre Ciudad");

        for (EmployeeDTO technician : technicians) {
        System.out.printf("%-10s %-20s %-10d %-20s %-15s %-10d %-20s %-10s %-10s %-20s%n",
        technician.getId(),
        technician.getName(),
        technician.getRolId(),
        technician.getRoleName(),
        technician.getIngressDate(),
        technician.getAirlineId(),
        technician.getAirlineName(),
        technician.getAirportId(),
        technician.getCityId(),
        technician.getCityName());
}
    }
}
