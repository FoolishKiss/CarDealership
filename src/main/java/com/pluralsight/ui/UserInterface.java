package com.pluralsight.ui;


import com.pluralsight.dao.*;
import com.pluralsight.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    // Create Scanner that can be used inside the class
    private final Scanner userInterface = new Scanner(System.in);

    // Create vehicleDAO to manage vehicle
    private final VehicleDAO vehicleDAO = new JdbcVehicleDAO();

    // Create dealershipDAO to manage dealership data
    private final DealershipDAO dealershipDAO = new JdbcDealershipDAO();

    // Create SalesContractDAO to manage sales contract data
    private final SalesContractDAO salesContractDAO = new JdbcSalesContractDAO();

    // Create LeaseContractDAO to manage sales contract data
    private final LeaseContractDAO leaseContractDAO = new JdbcLeaseContractDAO();


    //Method to call the user interface
    public void run() {

        // Flag to control when to exit app
        boolean exit = false;

        // Loop until user sleeps
        while (!exit) {
            // Show menu
            displayMenu();

            // Read user input and convert to int
            int choice = Integer.parseInt(userInterface.nextLine());

            //
            switch (choice) {
                case 1 -> listAllVehicles();
                case 2 -> searchVehicleByVin();
                case 3 -> addVehicle();
                case 4 -> removeVehicle();
                case 5 -> sellVehicle();
                case 6 -> leaseVehicle();
                case 99 -> exit = true;
                default -> System.out.println("Invalid choice");

            }

        }

    }

    //Method to display the menu options
    private void displayMenu() {
        System.out.println("\n === Dealership Menu ===");
        System.out.println("1 - list all vehicles");
        System.out.println("2 - Search vehicle by VIN");
        System.out.println("3 - Add vehicle");
        System.out.println("4 - Remove Vehicle");
        System.out.println("5 - Sell vehicle");
        System.out.println("6 - Lease vehicle");
        System.out.println("99 - Exit");
        System.out.println("Enter choice: \n");
    }

    //Method to show all cars in the database
    private void listAllVehicles() {

        // Get list of vehicles from database
        List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
        //Loop through each vehicle and prints out the details
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.getVin() + " - " + vehicle.getMake() + " - " + vehicle.getModel() + " $" + vehicle.getPrice());
        }
    }

    // Method to search for vehicle by vin
    private void searchVehicleByVin() {
        // Ask user for VIN
        System.out.println("Enter VIN: ");
        // Read and convert to int
        int vin = Integer.parseInt(userInterface.nextLine());
        // Ask database for vehicle matching VIN
        Vehicle vehicle = vehicleDAO.getVehicleByVin(vin);
        // Checks if vehicle found
        if (vehicle != null) {
            // If found print out info
            System.out.println(vehicle.getVin() + " - " + vehicle.getMake() + " - " + vehicle.getModel() + " $" + vehicle.getPrice());
            // Otherwise print message
        } else {
            System.out.println("Vehicle not found.");
        }

    }

    //Method for handling price range input and results
    private void addVehicle() {

        // Ask user for VIN
        System.out.println("VIN: ");

        // Read and convert to int
        int vin = Integer.parseInt(userInterface.nextLine());

        // Ask user for Year
        System.out.println("Year: ");

        // Read and convert to int
        int year = Integer.parseInt(userInterface.nextLine());

        // Ask user for Make
        System.out.println("Make: ");

        // Read as string
        String make = userInterface.nextLine();

        // Ask user for Model
        System.out.println("Model: ");

        // Read as string
        String model = userInterface.nextLine();

        // Ask user for Type
        System.out.println("Type: ");

        // Read as string
        String type = userInterface.nextLine();

        // Ask user for Color
        System.out.println("Color: ");

        // Read as string
        String color = userInterface.nextLine();

        // Ask user for Odometer
        System.out.println("Odometer: ");

        // Read and convert to int
        int odometer = Integer.parseInt(userInterface.nextLine());

        // Ask user for Price
        System.out.println("Price: ");

        // Read and convert to Double
        double price = Double.parseDouble(userInterface.nextLine());

        // Create vehicle with info
        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price, false);

        // Add vehicle to database
        vehicleDAO.addVehicle(vehicle);

        // Let user know it worked
        System.out.println("Vehicle added");
    }

    //Method to remove vehicle
    private void removeVehicle() {

        //Asks user for VIN
        System.out.println("Enter VIN to remove: ");

        //
        int vin = Integer.parseInt(userInterface.nextLine());

        //
        vehicleDAO.deleteVehicle(vin);

        //
        System.out.println("Vehicle removed");


    }

    //Method to sell vehicle
    private void sellVehicle() {

        //Asks user for VIN
        System.out.println("Enter VIN: ");

        //
        int vin = Integer.parseInt(userInterface.nextLine());

        //
        Vehicle vehicle = vehicleDAO.getVehicleByVin(vin);

        //
        if (vehicle == null) {
            System.out.println("Vehicle not found");
            return;
        }

        // Ask user for customer name
        System.out.println("Customer name:");

        //
        String name = userInterface.nextLine();

        //
        System.out.println("Customer email: ");

        //
        String email = userInterface.nextLine();

        //
        System.out.println("Is financed (True/False): ");

        //
        boolean financed = Boolean.parseBoolean(userInterface.nextLine());

        //
        System.out.println("Total price: ");
        //
        double totalPrice = Double.parseDouble(userInterface.nextLine());
        //
        System.out.println("Monthly payment: ");
        //
        double monthlyPayment = Double.parseDouble(userInterface.nextLine());

        //
        SalesContract contract = new SalesContract(0, LocalDate.now(), name, email, vehicle, totalPrice, monthlyPayment, financed);
        //
        System.out.println("Sales contract saved");


    }

    //Method to sell vehicle
    private void leaseVehicle() {

        //Asks user for VIN
        System.out.println("Enter VIN: ");

        //
        int vin = Integer.parseInt(userInterface.nextLine());

        //
        Vehicle vehicle = vehicleDAO.getVehicleByVin(vin);

        //
        if (vehicle == null) {
            System.out.println("Vehicle not found");
            return;
        }

        // Ask user for customer name
        System.out.println("Customer name:");

        //
        String name = userInterface.nextLine();

        //
        System.out.println("Customer email: ");

        //
        String email = userInterface.nextLine();

        //
        System.out.println("Is financed (True/False): ");

        //
        boolean financed = Boolean.parseBoolean(userInterface.nextLine());

        //
        System.out.println("Total price: ");
        //
        double totalPrice = Double.parseDouble(userInterface.nextLine());
        //
        System.out.println("Monthly payment: ");
        //
        double monthlyPayment = Double.parseDouble(userInterface.nextLine());

        //
        LeaseContract contract = new LeaseContract(0, LocalDate.now(), name, email, vehicle, totalPrice, monthlyPayment);
        //
        System.out.println("Sales contract saved");

    }


}
