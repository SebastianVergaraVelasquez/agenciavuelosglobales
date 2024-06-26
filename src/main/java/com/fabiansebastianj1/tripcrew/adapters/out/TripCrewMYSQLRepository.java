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

    // Constructor que inicializa la URL, usuario y contraseña de la base de datos
    public TripCrewMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    // Método para guardar un equipo de viaje en la base de datos
    @Override
    public void save(TripCrew tripCrew) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO trip_crew (id_employee, id_connection) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripCrew.getEmployeeId());
                statement.setInt(2, tripCrew.getConnectionId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar los equipos de viaje como objetos DTO basados en el ID de conexión
    @Override
    public List<TripCrewDTO> listTripCrewDTO(int id) {
        List<TripCrewDTO> tripCrewList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT " +
                    "em.id AS employee_id, em.name AS employee_name, tr.name AS role_name " +
                    "FROM trip_crew AS tc " +
                    "JOIN employee em ON em.id = tc.id_employee " +
                    "JOIN tripulation_role tr ON tr.id = em.id_rol " +
                    "WHERE tc.id_connection = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        TripCrewDTO tripCrewDTO = new TripCrewDTO(
                                resultSet.getString("employee_id"),
                                resultSet.getString("employee_name"),
                                resultSet.getString("role_name"));
                        tripCrewList.add(tripCrewDTO);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripCrewList;
    }

}
