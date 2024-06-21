package com.fabiansebastianj1.revision.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.revision.domain.models.Revision;
import com.fabiansebastianj1.revision.infrastructure.RevisionRepository;

public class RevisionService {
     private final RevisionRepository revisionRepository;

    public RevisionService(RevisionRepository revisionRepository) {
        this.revisionRepository = revisionRepository;
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
}
