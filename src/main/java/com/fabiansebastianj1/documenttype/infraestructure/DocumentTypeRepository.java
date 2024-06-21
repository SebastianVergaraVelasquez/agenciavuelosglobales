package com.fabiansebastianj1.documenttype.infraestructure;

import com.fabiansebastianj1.documenttype.domain.models.DocumentType;

import java.util.List;
import java.util.Optional;

public interface DocumentTypeRepository {
    void save(DocumentType documentType);
    void update(DocumentType documentType);
    Optional<DocumentType> findById(int id);
    void delete(int id);
    List<DocumentType> findAll();
}
