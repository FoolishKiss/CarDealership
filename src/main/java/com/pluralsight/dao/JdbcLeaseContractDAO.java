package com.pluralsight.dao;

import com.pluralsight.config.DatabaseConfig;
import com.pluralsight.models.LeaseContract;
import com.pluralsight.models.SalesContract;
import com.pluralsight.models.Vehicle;

import javax.sql.DataSource;
import java.rmi.dgc.Lease;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcLeaseContractDAO implements LeaseContractDAO {

    // Get database connection from config
    private final DataSource dataSource = DatabaseConfig.getDataSource();

    // Method saves a lease contract to database
    @Override
    public void addLeaseContract (LeaseContract contract) {

        // Prepare sql query
        String query = """
                INSERT INTO LeaseContract (Vin, CustomerName, CustomerEmail, Date, TotalPrice, MonthlyPayment)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        // Try block to connect to database and prepare the query
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            // Replace 1st ? with vehicle vin
            statement.setInt(1, contract.getVehicle().getVin());

            // Replace the 2nd ? with customers name
            statement.setString(2, contract.getCustomerName());

            // Replace the 3rd ? with customers email
            statement.setString(3, contract.getCustomerEmail());

            // Replace the 4th ? with contract date
            statement.setDate(4, Date.valueOf(contract.getDate()));

            // Replace the 5th ? with total price
            statement.setDouble(5, contract.getTotalPrice());

            // Replace the 6th ? with monthly payment
            statement.setDouble(6, contract.getMonthlyPayment());

            // Execute sql query
            statement.executeUpdate();


            // Catch error with database
        } catch (SQLException e) {
            // print error
            e.printStackTrace();
        }

    }

    // Method to find and return sales contract
    @Override
    public LeaseContract getLeaseContractById(int id) {

        // Sql query
        String query = """
                SELECT *
                FROM LeaseContract
                WHERE ID = ?
                """;

        // Try block to connect to database
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Replace ? with ID
            statement.setInt(1, id);

            // Execute query
            ResultSet results = statement.executeQuery();

            // If matching contract found
            if (results.next()) {
                // Convert the row to LeaseContract
                return mapRowToLeaseContract(results);
            }

            // Catch error with database
        } catch (SQLException e) {
            // print error
            e.printStackTrace();
        }

        // If nothing found return null
        return null;
    }

    // Method to get all sales contract from database
    @Override
    public List<LeaseContract> getAllLeaseContracts() {

        // Create an empty list to store contracts
        List<LeaseContract> contracts = new ArrayList<>();

        // Prepare query
        String query = """
                SELECT *
                FROM LeaseContract
                """;

        // Try block to connect to database, prepare, and execute query
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet results = statement.executeQuery()) {

            // Loop through contracts found
            while (results.next()) {

                // Convert to SalesContract and add to list
                contracts.add(mapRowToLeaseContract(results));
            }


            // Catch error with database
        } catch (SQLException e) {
            // print error
            e.printStackTrace();
        }

        // Return list of contracts
        return contracts;

    }

    // Method to convert row to SalesContract
    private LeaseContract mapRowToLeaseContract(ResultSet results) throws SQLException {

        // Get the ID from the database row
        int id = results.getInt("ID");

        // Get the VIN from the database row
        int vin = results.getInt("Vin");

        // Get the customer name from the database row
        String customerName = results.getString("CustomerName");

        // Get the customer email from the database row
        String customerEmail = results.getString("CustomerEmail");

        // Get the date from the database row and convert it to LocalDate
        LocalDate date = results.getDate("Date").toLocalDate();

        // Get the total price from the database row
        double totalPrice = results.getDouble("TotalPrice");

        // Get the monthly payment from the database row
        double monthlyPayment = results.getDouble("MonthlyPayment");

        // Create a Vehicle object
        Vehicle vehicle = new Vehicle(vin, 0, "", "", "", "", 0, 0.0, false);

        // Create and return a new LeaseContract object with all the data
        return new LeaseContract(id, date, customerName, customerEmail, vehicle, totalPrice, monthlyPayment);
    }

}
