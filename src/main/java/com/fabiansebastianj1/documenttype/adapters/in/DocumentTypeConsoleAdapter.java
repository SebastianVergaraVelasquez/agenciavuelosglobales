package com.fabiansebastianj1.documenttype.adapters.in;

import com.fabiansebastianj1.documenttype.application.DocumentService;
import com.fabiansebastianj1.documenttype.domain.models.DocumentType;
import com.fabiansebastianj1.validations.InputVali;
import com.fabiansebastianj1.validations.Register;
import com.fabiansebastianj1.validations.ValidationExist;

import java.util.List;
import java.util.Scanner;

public class DocumentTypeConsoleAdapter {

    private final DocumentService documentService;

    public DocumentTypeConsoleAdapter(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean executing = true;
        InputVali inputVali = new InputVali();

        while (executing) {
            System.out.println("*** Modulo de Tipo de Documento ***");
            System.out.println(" ");
            System.out.println("Que accion desea realizar, digite una opcion numerica");
            System.out.println(
                    "1. Registrar tipo de documento\n2. Actualizar tipo de documento\n3. Eliminar tipo de documento" +
                            "\n4. Consultar tipo de documento\n5. Salir");
            int choice = scanner.nextInt();
            System.out.println(" ");

            switch (choice) {
                case 1:
                    System.out.println("*** Registrar tipo de documento ***");
                    System.out.println(" ");
                    scanner.nextLine();

                    String nameDocumentTypes = inputVali.stringNotNull("Ingrese el nombre del tipo de documento");
                    DocumentType documentType = new DocumentType(nameDocumentTypes);
                    documentService.createDocumentType(documentType);
                    break;
                case 2:
                    System.out.println("*** Actualizar Tipo de Documento ***");
                    showDocumentTypes();
                    System.out.println(" ");
                    DocumentType documentTypeToUpdate = returnDocumentType(inputVali);
                    showDocumentTypeInfo(documentTypeToUpdate);
                    updateDocumentType(documentTypeToUpdate, inputVali);
                    System.out.println("Tipo de Documento actualizada");
                    break;
                case 3:
                    System.out.println("*** Eliminar Tipo de Documento ***");
                    showDocumentTypes();
                    System.out.println(" ");
                    DocumentType documentTypeToDelete = returnDocumentType(inputVali);
                    documentService.deleteDocumentTypeById(documentTypeToDelete.getId());
                    break;
                case 4:
                    System.out.println("*** Consultar Tipo de Documento ***");
                    DocumentType showDocumentType = returnDocumentType(inputVali);
                    showDocumentTypeInfo(showDocumentType);
                    break;
                case 5:
                    executing = false;
                    System.out.println("Saliendo del modulo de Tipo de Documento");
                    break;
                default:
                    break;
            }

        }

    }

    public void updateDocumentType(DocumentType documentType, InputVali inputVali) {
        boolean newInput;
        newInput = Register.yesOrNo("Desea cambiar el nombre del Tipo de Documento? Ingrese el valor numérico: " +
                "1 (si) 2 (no)");
        if (newInput) {
            String newName = inputVali.stringNotNull("Ingrese el nombre del Tipo de Documento");
            documentType.setName(newName);
        }
        documentService.updateDocumentType(documentType);
    }

    public DocumentType returnDocumentType(InputVali inputVali) {
        DocumentType documentType = ValidationExist.transformAndValidateObj(
                () -> documentService.getDocumentTypeById(
                        inputVali.readInt(inputVali.stringNotNull("Ingrese la id del Tipo de Documento"))));
        return documentType;
    }

    public void showDocumentTypeInfo(DocumentType documentType) {
        System.out.println(String.format("id: %d \n" +
                "name: %s \n", documentType.getId(), documentType.getName()));
    }

    public void showDocumentTypes() {
        List<DocumentType> documentTypes = documentService.getAllDocumentTypes();
        System.out.println("Tipos de documentos registrados actualmente");
        for (DocumentType documentType : documentTypes) {
            System.out.println(String.format("id: %d ; " +
                    "name: %s", documentType.getId(), documentType.getName()));
        }
    }

}
