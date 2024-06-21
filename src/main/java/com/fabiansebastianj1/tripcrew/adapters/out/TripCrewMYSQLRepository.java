package com.fabiansebastianj1.tripcrew.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fabiansebastianj1.tripcrew.domain.models.TripCrew;
import com.fabiansebastianj1.tripcrew.infrastructure.TripCrewRepository;

public class TripCrewMYSQLRepository implements TripCrewRepository {

    private final String url;
    private final String user;
    private final String password;

    public TripCrewMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(TripCrew tripCrew){
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO trip_crew (id_employee, id_connection) VALUES (?,?)";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, tripCrew.getEmployeeId());
                statement.setInt(2, tripCrew.getConnectionId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}
