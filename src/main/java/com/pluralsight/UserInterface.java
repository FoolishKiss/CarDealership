package com.pluralsight;

import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    //Scanner that can be used inside the class
    private final Scanner userInterface = new Scanner(System.in);

    //Holds the dealership loaded from the file
    private Dealership dealership;

    //Method to call the user interface
    public void display() {
        //Calls method to load inventory file
        init();


        //Variable that stores the user input
        int command;

        //Loop that continues until user selects option 99
        do {
            //Prints out the menu
            displayMenu();
            //Ask user for input
            System.out.println("Enter a menu option: ");
            //Stores user input into command variable
            command = userInterface.nextInt();
            //Eats the newline
            userInterface.nextLine();

            //Runs different method based off users input
            switch (command) {
                case 1:
                    processAllVehiclesRequest();
                    break;
                case 2:
                    processGetByPriceRequest();
                    break;
                case 3:
                    processGetByMakeModelRequest();
                    break;
                case 4:
                    processGetByYearRequest();
                    break;
                case 5:
                    processGetByColorRequest();
                    break;
                case 6:
                    processGetByMileageRequest();
                    break;
                case 7:
                    processGetByTypeRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 10:
                    processSellorLease();
                    break;
                case 99:
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
          //Ends the loop when user enters 99
        } while (command != 99);
        //Closes scanner
        userInterface.close();
    }
    //Method to create a DealershipFileManager that load dealership from the file and stores it in dealership
    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();
    }

    //Method to display the menu options
    private void displayMenu() {
        System.out.println("\n === Dealership Menu ===");
        System.out.println("1 - list all vehicles");
        System.out.println("2 - Find vehicles by price range");
        System.out.println("3 - Find vehicles by make and model");
        System.out.println("4 - Find vehicles by year range");
        System.out.println("5 - Find vehicles by color");
        System.out.println("6 - Find vehicles by mileage range");
        System.out.println("7 - Find vehicles by type (SUV, Truck, Sedan, Van)");
        System.out.println("8 - Add vehicle to inventory");
        System.out.println("9 - Remove vehicle from inventory");
        System.out.println("10 - Sell or lease a vehicle");
        System.out.println("99 - Exit");
        System.out.println("\n");
    }

    //Method that takes a list of vehicles and prints them out in the formated way
    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        //Loop through each vehicle and prints out the details
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    //Method to call getAllVehicles and passes it to displayVehicles
    private void processAllVehiclesRequest() {
        ArrayList<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    //Method for handling price range input and results
    private void processGetByPriceRequest() {

        //Asks user for price range and reads it as a double
        System.out.println("Enter min price: ");
        double min = userInterface.nextDouble();
        System.out.println("Enter max price: ");
        double max = userInterface.nextDouble();

        //Calls dealership method to find all vehicles in price range
        ArrayList<Vehicle> results = dealership.getVehiclesByPrice(min, max);
        //Uses display method to vehicles
        displayVehicles(results);

    }

    //Method to search users make model
    private void processGetByMakeModelRequest() {

        //Asks user for make and model
        System.out.println("Enter make: ");
        String make = userInterface.nextLine();
        System.out.println("Enter model: ");
        String model = userInterface.nextLine();

        //Calls dealership method to find all vehicles with make model
        ArrayList<Vehicle> results = dealership.getVehiclesByMakeModel(make, model);
        //Uses display method to vehicles
        displayVehicles(results);
    }

    //Method to search users make model
    private void processGetByYearRequest() {

        //Asks user for min and max year
        System.out.println("Enter min year: ");
        int minYear = userInterface.nextInt();
        System.out.println("Enter max year: ");
        int maxYear = userInterface.nextInt();

        //Calls dealership method to find all vehicles within year range
        ArrayList<Vehicle> results = dealership.getVehiclesByYear(minYear, maxYear);
        //Uses display method to show vehicles
        displayVehicles(results);
    }

    //Method to search for user color
    private void processGetByColorRequest() {

        // Ask user what color
        System.out.println("Enter color: ");
        String color = userInterface.nextLine();

        //Calls dealership method to find all vehicles with user color choice
        ArrayList<Vehicle> results = dealership.getVehiclesByColor(color);
        //Uses display method to show vehicles
        displayVehicles(results);

    }

    //Method to add a vehicle
    private void processAddVehicleRequest() {

        // Ask user for Vehicle info
        System.out.println("Enter VIN: ");
        int vin = userInterface.nextInt();
        userInterface.nextLine();

        System.out.println("Enter year: ");
        int year = userInterface.nextInt();
        userInterface.nextLine();

        System.out.println("Enter make: ");
        String make = userInterface.nextLine();

        System.out.println("Enter model: ");
        String model = userInterface.nextLine();

        System.out.println("Enter vehicle type: ");
        String type = userInterface.nextLine();

        System.out.println("Enter color: ");
        String color = userInterface.nextLine();

        System.out.println("Enter odometer: ");
        int odometer = userInterface.nextInt();

        System.out.println("Enter price: ");
        double price = userInterface.nextDouble();

        Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer,price);
        dealership.addVehicle(newVehicle);

        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);

        System.out.println("\n Vehicle added successfully.");
    }

    // Method to remove a vehicle
    private void processRemoveVehicleRequest() {

        System.out.println("Enter the VIN to remove vehicle: ");
        int vin = userInterface.nextInt();

        dealership.removeVehicle(vin);

        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);

        System.out.println("Vehicle removed.");
    }

    //Method to search by mileage range
    private void processGetByMileageRequest() {

        //Ask user for min and max mileage
        System.out.println("Enter min mileage: ");
        int min = userInterface.nextInt();
        System.out.println("Enter max mileage: ");
        int max = userInterface.nextInt();

        //Calls dealership method to find all vehicles within year range
        ArrayList<Vehicle> results = dealership.getVehiclesByMileage(min, max);
        //Uses display method to show vehicles
        displayVehicles(results);

    }

    //Method to search by type
    private void processGetByTypeRequest() {

        //Ask user what type of vehicle to look for
        System.out.println("Enter vehicle type (Sedan, truck, SUV, van): ");
        String type = userInterface.nextLine();

        //Calls dealership method to find all vehicles matches
        ArrayList<Vehicle> results = dealership.getVehiclesByType(type);
        //Uses display method to show vehicles
        displayVehicles(results);
    }

    //Method to sell or lease a vehicle
    private void processSellorLease() {

        // Ask user for the VIN to sell or lease
        System.out.println("Enter VIN of Vehicle you wish to Sell/Lease: ");
        int vin = userInterface.nextInt();
        userInterface.nextLine();

        // Matching vehicle is saved to variable vehicle to sell
        Vehicle vehicleToSell = findVehicleByVin(vin);

        // if no match the return message
            if (vehicleToSell == null) {
                System.out.println("Vehicle not found.");;
                return;
            }

        // Ask for contract info
        System.out.println("Enter customer name: ");
        String name = userInterface.nextLine();

        System.out.println("Enter customer email: ");
        String email = userInterface.nextLine();

        //Ask if it's a sale or lease
        System.out.println("Is this a sale or lease? (enter sale or lease):  ");
        String type = userInterface.nextLine();

        // Get today's date
        String date = LocalDate.now().toString();

        Contract contract = createContract(type, date, name,email,vehicleToSell);

        if (contract == null) {
            System.out.println("Invalid contract type.");
            return;
        }

        new ContractFileManager().saveContract(contract);
        dealership.removeVehicle(vin);

        new DealershipFileManager().saveDealership(dealership);



    }

    // Method to find vehicle by vin
    private Vehicle findVehicleByVin(int vin) {
        //Loops through inventory to find match
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                return v;
            }
        }
        return null;
    }

    //Method to create a contract
    private Contract createContract(String type, String date, String name, String email, Vehicle vehicle) {

        if (type.equals("sales")) {

            //Ask if the customer is financing
            System.out.println("Is the customer financing? (true/false)");

            boolean isFianced = userInterface.nextBoolean();

            // Eats new line
            userInterface.nextLine();

            // Creates the sales contract
            return new SalesContract(date, name, email, vehicle, isFianced);

        } else if (type.equals("lease")) {

            // Create the lease contract
            return new LeaseContract(date, name, email, vehicle);


        }
        return null;
    }



}
