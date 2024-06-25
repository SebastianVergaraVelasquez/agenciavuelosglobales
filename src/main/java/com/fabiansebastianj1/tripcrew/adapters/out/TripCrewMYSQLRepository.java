package com.fabiansebastianj1.tripcrew.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fabiansebastianj1.connection.domain.models.ConnectionDTO;
import com.fabiansebastianj1.tripcrew.domain.models.TripCrew;
import com.fabiansebastianj1.tripcrew.domain.models.TripCrewDTO;
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

    @Override
    public List<TripCrewDTO> listTripCrewDTO(int id){
        List<TripCrewDTO> tripCrewList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT  "+
            "em.id AS employee_id, em.name, tr.name "+
            "FROM trip_crew AS tc " +
            "JOIN employee em ON em.id = tc.id_employee "+
            "JOIN tripulation_role tr ON tr.id = em.id_rol "+
            "WHERE tc.id_connection = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        TripCrewDTO connect = new TripCrewDTO(
                                resultSet.getString("id"),
                                resultSet.getString("query"),
                                resultSet.getString("query"));
                                tripCrewList.add(connect);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripCrewList;
    }

}
