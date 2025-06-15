package com.pluralsight.dao;

import com.pluralsight.config.DatabaseConfig;
import com.pluralsight.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcVehicleDAO implements VehicleDAO{

    private final DataSource dataSource = DatabaseConfig.getDataSource();

    @Override
    public List<Vehicle> getAllVehicles(){
        List<Vehicle> vehicles = new ArrayList<>();
        String query = """
                SELECT *
                FROM Vehicles
                """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet results = statement.executeQuery()) {

            while (results.next()) {
                vehicles.add(mapRowToVehicle(results));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public Vehicle  getVehicleByVin(int vin){

        //
        String query ="""
                SELECT *
                FROM Vehicles
                WHERE Vin = ?
                """;

        //
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            //
            statement.setInt(1, vin);
            //
            ResultSet results = statement.executeQuery();

            //
            if (results.next()) {
                return mapRowToVehicle(results);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addVehicle(Vehicle vehicle) {

        //
        String query = """
                INSERT INTO Vehicles (Vin, Year, Make, Model, VehicleType, color, Odometer, Price, Sold)
                VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        //
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            //
            statement.setInt(1, vehicle.getVin());
            statement.setInt(2, vehicle.getYear());
            statement.setString(3, vehicle.getMake());
            statement.setString(4, vehicle.getModel());
            statement.setString(5, vehicle.getVehicleType());
            statement.setString(6, vehicle.getColor());
            statement.setInt(7, vehicle.getOdometer());
            statement.setDouble(8, vehicle.getPrice());
            statement.setBoolean(9, vehicle.isSold());

            //
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateVehicle(Vehicle vehicle){

        //
        String query = """
                UPDATE Vehicles
                SET Year = ?, Make = ?, Model = ?, VehicleType = ?, Color = ?, Odometer = ?, Price = ?, Sold = ?
                WHERE Vin = ?
                """;
        //
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            //
            statement.setInt(1, vehicle.getVin());
            statement.setInt(2, vehicle.getYear());
            statement.setString(3, vehicle.getMake());
            statement.setString(4, vehicle.getModel());
            statement.setString(5, vehicle.getVehicleType());
            statement.setString(6, vehicle.getColor());
            statement.setInt(7, vehicle.getOdometer());
            statement.setDouble(8, vehicle.getPrice());
            statement.setBoolean(9, vehicle.isSold());

            //
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteVehicle(int vin){

        //
        String query = """
                DELETE FROM Vehicles
                WHERE Vin = ?
                """;

        //
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            //
            statement.setInt(1, vin);
            //
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Vehicle mapRowToVehicle(ResultSet results) throws SQLException {
        return new Vehicle(
                results.getInt("Vin"),
                results.getInt("Year"),
                results.getString("Make"),
                results.getString("Model"),
                results.getString("VehicleType"),
                results.getString("Color"),
                results.getInt("Odometer"),
                results.getDouble("Price"),
                results.getBoolean("Sold")
        );
    }





}
