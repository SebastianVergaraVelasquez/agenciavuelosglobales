package com.fabiansebastianj1.documenttype.adapters.out;

import com.fabiansebastianj1.documenttype.domain.models.DocumentType;
import com.fabiansebastianj1.documenttype.infraestructure.DocumentTypeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DocumentTypeMYSQLRepository implements DocumentTypeRepository {
    private final String url;
    private final String user;
    private final String password;

    public DocumentTypeMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(DocumentType documentType) {
        try (Connection connection = DriverManager.getConnection(url,user,password)){
            String query = "INSERT INTO document_type (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1,documentType.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(DocumentType documentType) {
        try (Connection connection = DriverManager.getConnection(url,user,password)){
            String query = "UPDATE document_type SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1,documentType.getName());
                statement.setInt(2,documentType.getId());
                statement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<DocumentType> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url,user,password)){
            String query = "SELECT * FROM document_type WHERE id = ?";
            try (PreparedStatement statement =connection.prepareStatement(query)){
                statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                DocumentType documentType = new DocumentType(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
                );
                return Optional.of(documentType);
                }
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user,password)) {
        String query = "DELETE FROM document_type WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1,id);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<DocumentType> findAll() {
        List<DocumentType> documentTypes = new ArrayList<>();
        try (Connection connection =DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM document_type;";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()){
                    DocumentType documentType = new DocumentType(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    );
                    documentTypes.add(documentType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documentTypes;
    }
}
