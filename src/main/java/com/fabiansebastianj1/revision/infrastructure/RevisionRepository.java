package com.fabiansebastianj1.revision.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.revision.domain.models.Revision;
import com.fabiansebastianj1.revision.domain.models.RevisionDTO;

public interface RevisionRepository {
    void save(Revision revision);
    void update(Revision revision);
    void delete(int id);
    Optional<Revision> findById(int id);
    List<Revision> findAll();
    Optional<Revision> findLast();
    List<RevisionDTO> findRevisionsByPlaneId(int id);
}
