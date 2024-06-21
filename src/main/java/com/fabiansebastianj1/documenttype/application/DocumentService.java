package com.fabiansebastianj1.documenttype.application;

import com.fabiansebastianj1.documenttype.domain.models.DocumentType;
import com.fabiansebastianj1.documenttype.infraestructure.DocumentTypeRepository;

import java.util.List;
import java.util.Optional;

public class DocumentService {
    private final DocumentTypeRepository documentTypeRepository;

    public DocumentService(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public void createDocumentType(DocumentType documentType) {
        documentTypeRepository.save(documentType);
    }

    public void updateDocumentType(DocumentType documentType) {
        documentTypeRepository.update(documentType);
    }

    public Optional<DocumentType> getDocumentTypeById(int id) {
        return documentTypeRepository.findById(id);
    }

    public void deleteDocumentTypeById(int id) {
        documentTypeRepository.delete(id);
    }

    public List<DocumentType> getAllDocumentTypes() {
        return documentTypeRepository.findAll();
    }

}
