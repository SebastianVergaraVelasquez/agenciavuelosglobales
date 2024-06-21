package com.fabiansebastianj1.revisiondetail.infraestructure;

import com.fabiansebastianj1.revisiondetail.domain.models.RevisionDetail;

import java.util.List;
import java.util.Optional;

public interface RevisionDetailRepository {
    void save(RevisionDetail revisionDetail);
    void update(RevisionDetail revisionDetail);
    void delete(String id);
    Optional<RevisionDetail> findById(String id);
    List<RevisionDetail> findAll();
}
