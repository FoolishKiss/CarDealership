package com.pluralsight.dao;

import com.pluralsight.config.DatabaseConfig;
import com.pluralsight.models.Dealership;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcDealershipDAO implements DealershipDAO{

    private final DataSource dataSource = DatabaseConfig.getDataSource();

    @Override
    public List<Dealership> getAllDealerships() {
        List<Dealership> dealerships = new ArrayList<>();
        String query = """
                SELECT *
                FROM Dealership
                """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet results = statement.executeQuery()) {

            while (results.next()) {
                dealerships.add(mapRowToDealership(results));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealerships;
    }

    @Override
    public Dealership getDealershipById(int dealershipId) {
        String query = """
                SELECT *
                FROM Dealership
                WHERE DealershipID = ?
                """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, dealershipId);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                return mapRowToDealership(results);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Dealership mapRowToDealership(ResultSet results) throws SQLException {
        return new Dealership(
                results.getInt("DealershipID"),
                results.getString("DealershipName"),
                results.getString("Address"),
                results.getString("Phone")
        );
    }

}
