package com.fabiansebastianj1.revision.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.revision.domain.models.Revision;
import com.fabiansebastianj1.revision.infrastructure.RevisionRepository;

public class RevisionMYSQLRepository implements RevisionRepository {
    private final String url;
    private final String user;
    private final String password;

    public RevisionMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Revision revision) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO revision (revision_date,id_plane) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, revision.getRevisionDate());
                statement.setInt(2, revision.getPlaneId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Revision revision) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE revision SET revision_date = ?, id_plane = ? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, revision.getRevisionDate());
                statement.setInt(2, revision.getPlaneId());
                statement.setInt(3, revision.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM revision WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Revision> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM revision WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Revision revision = new Revision(
                                resultSet.getInt("id"),
                                resultSet.getString("revision_date"),
                                resultSet.getInt("id_plane"));
                        return Optional.of(revision);
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Revision> findAll() {
        List<Revision> revisions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM revision";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Revision revision = new Revision(
                            resultSet.getInt("id"),
                            resultSet.getString("revision_date"),
                            resultSet.getInt("id_plane"));
                    revisions.add(revision);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revisions;
    }

    @Override
    public Optional<Revision> findLast() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM revision ORDER BY id DESC LIMIT 1";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Revision revision = new Revision(
                            resultSet.getInt("id"),
                            resultSet.getString("revision_date"),
                            resultSet.getInt("id_plane"));
                    return Optional.of(revision);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
