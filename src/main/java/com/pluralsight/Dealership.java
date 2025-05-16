package com.pluralsight;

import java.util.ArrayList;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //Method to show inventory list
    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }

    //Method to show all vehicles in the inventory list
    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    //Method to add vehicle to inventory list
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    //Method to that takes the user min and max range to search within
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {
        //Creates a new list to store all the vehicles that match the price range
        ArrayList<Vehicle> matches = new ArrayList<>();

        //Loop through each vehicle in the inventory
        for (Vehicle v : inventory) {
            //Checks if the vehicles within user price range
            if (v.getPrice() >= min && v.getPrice() <= max) {
                //Adds vehicle to matches list
                matches.add(v);
            }
        }
        //Returns the list of matches
        return matches;
    }

    //Method to that takes the user make and model to search for
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        //Creates a new list to store all the vehicles that match the make and model
        ArrayList<Vehicle> matches = new ArrayList<>();

        //Loop through each vehicle in the inventory
        for (Vehicle v : inventory) {
            //Checks if the vehicles matches make and model
            if (v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)) {
                //Adds vehicle to matches list
                matches.add(v);
            }
        }
        //Returns the list of matches
        return matches;
    }

    //Method to that takes the user min and max year to search for
    public ArrayList<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        //Creates a new list to store all the vehicles that match the min and max year
        ArrayList<Vehicle> matches = new ArrayList<>();

        //Loop through each vehicle in the inventory
        for (Vehicle v : inventory) {
            //Checks if the vehicles matches make and model
            if (v.getYear() >= minYear && v.getYear() <= maxYear) {
                //Adds vehicle to matches list
                matches.add(v);
            }
        }
        //Returns the list of matches
        return matches;
    }

    //Method to take users color choice and searches for it
    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        // Creates a list to store user matches
        ArrayList<Vehicle> matches = new ArrayList<>();
        // Loops through the inventory and stores the matches in variable v
        for (Vehicle v : inventory) {
            // Checks if the color matches user input
            if (v.getColor().equalsIgnoreCase(color)) {
                // Adds the vehicle to list of matches
                matches.add(v);
            }
        }
        // Return matches
        return matches;
    }

    //Method to search for vehicle by vehicle mileage
    public ArrayList<Vehicle> getVehiclesByMileage(int minMileage, int maxMileage) {
        //Creates a new list to store all the vehicles that match the min and max year
        ArrayList<Vehicle> matches = new ArrayList<>();

        //Loop through each vehicle in the inventory
        for (Vehicle v : inventory) {
            //Checks if the vehicles matches make and model
            if (v.getOdometer() >= minMileage && v.getOdometer() <= maxMileage) {
                //Adds vehicle to matches list
                matches.add(v);
            }
        }
        //Returns the list of matches
        return matches;
    }

    //Method to search for vehicle by vehicle type
    public ArrayList<Vehicle> getVehiclesByType(String type) {
        //Creates a new list to store all the vehicles that match the min and max year
        ArrayList<Vehicle> matches = new ArrayList<>();

        //Loop through each vehicle in the inventory
        for (Vehicle v : inventory) {
            //Checks if the vehicles matches make and model
            if (v.getVehicleType(). equalsIgnoreCase(type)) {
                //Adds vehicle to matches list
                matches.add(v);
            }
        }
        //Returns the list of matches
        return matches;
    }

    //Method to remove vehicle by vin
    public void removeVehicle(int vin) {
        // Create a variable to hold the match
        Vehicle toRemove = null;
        //
        for (Vehicle v : inventory) {
            if (v.getVin() == vin) {
                toRemove = v;
                break;
            }
        }
        if (toRemove != null) {
            inventory.remove(toRemove);
        }
    }
}
