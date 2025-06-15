package com.pluralsight.dao;

import com.pluralsight.models.Vehicle;

import java.util.List;

public interface VehicleDAO {

    // Must have method to get all vehicles from database
    List<Vehicle> getAllVehicles();

    // Must have method to get vehicle by vin
    Vehicle getVehicleByVin(int vin);

    // Must have method to add vehicles to database
    void addVehicle(Vehicle vehicle);

    // Must have method to update vehicles in database
    void updateVehicle(Vehicle vehicle);

    // Must have method to delete vehicles from database
    void deleteVehicle(int vin);


}
