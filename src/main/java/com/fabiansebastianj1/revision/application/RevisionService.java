package com.fabiansebastianj1.revision.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.employee.domain.models.Employee;
import com.fabiansebastianj1.employee.infrastructure.EmployeeRepository;
import com.fabiansebastianj1.planes.domain.models.Plane;
import com.fabiansebastianj1.planes.infrastructure.PlaneRepository;
import com.fabiansebastianj1.revemployee.application.RevEmployeService;
import com.fabiansebastianj1.revemployee.domain.models.RevEmployee;
import com.fabiansebastianj1.revision.domain.models.Revision;
import com.fabiansebastianj1.revision.domain.models.RevisionDTO;
import com.fabiansebastianj1.revision.infrastructure.RevisionRepository;

public class RevisionService {

    private final RevisionRepository revisionRepository;
    private final PlaneRepository planeRepository;
    private final EmployeeRepository employeeRepository;
    private final RevEmployeService revEmployeService;

    public RevisionService(RevisionRepository revisionRepository, PlaneRepository planeRepository,
            EmployeeRepository employeeRepository, RevEmployeService revEmployeService) {
        this.revisionRepository = revisionRepository;
        this.planeRepository = planeRepository;
        this.employeeRepository = employeeRepository;
        this.revEmployeService = revEmployeService;
    }

    public void createRevision(Revision revision) {
        revisionRepository.save(revision);
    }

    public void deleteRevision(int id) {
        revisionRepository.delete(id);
    }

    public void updateRevision(Revision revision) {
        revisionRepository.update(revision);
    }

    public Optional<Revision> findRevisionById(int id) {
        return revisionRepository.findById(id);
    }

    public List<Revision> findAllRevisions() {
        return revisionRepository.findAll();
    }
    
    public List<Plane> listPlanes(){
        return planeRepository.findAll();
    }

    public Optional<Plane> findPlaneById(int id){
        return planeRepository.findById(id);
    }

    public Optional<Employee> findEmployeeById(String id){
        return employeeRepository.findById(id);
    }

    public Optional<Revision> findLastRevision(){
        return revisionRepository.findLast();
    }

    public void createRevEmployee(RevEmployee revEmployee){
        revEmployeService.save(revEmployee);
    }

    public List<RevisionDTO> revisionsByPlaneId(int id){
        return revisionRepository.findRevisionsByPlaneId(id);
    }

    public Optional<Plane> findPlaneByPlates(String plates){
        return planeRepository.findByPlate(plates);
    }

}
