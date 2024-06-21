package com.fabiansebastianj1.revisiondetail.application;

import com.fabiansebastianj1.revisiondetail.domain.models.RevisionDetail;
import com.fabiansebastianj1.revisiondetail.infraestructure.RevisionDetailRepository;

import java.util.List;
import java.util.Optional;

public class RevisionDetailService {
    private final RevisionDetailRepository revisionDetailRepository;

    public RevisionDetailService(RevisionDetailRepository revisionDetailRepository) {
        this.revisionDetailRepository = revisionDetailRepository;
    }

    public void createRevisionDetail(RevisionDetail revisionDetail) {
        revisionDetailRepository.save(revisionDetail);
    }

    public void updateRevisionDetail(RevisionDetail revisionDetail) {
        revisionDetailRepository.update(revisionDetail);
    }

    public void deleteRevisionDetail(String id) {
        revisionDetailRepository.delete(id);
    }

    public Optional<RevisionDetail> getRevisionDetailById(String id) {
        return revisionDetailRepository.findById(id);
    }
    public List<RevisionDetail> getAllRevisionDetail() {
        return revisionDetailRepository.findAll();
    }
}
