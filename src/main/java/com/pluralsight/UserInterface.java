package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    //Holds the dealership loaded from the file
    private Dealership dealership;

    //Method to call the user interface
    public void display() {
        //Calls method to load inventory file
        init();

        //Scanner takes user input
        Scanner userInput = new Scanner(System.in);
        //Variable that stores the user input
        int command;

        //Loop that continues until user selects option 99
        do {
            //Prints out the menu
            displayMenu();
            //Ask user for input
            System.out.println("Enter a menu option: ");
            //Stores user input into command variable
            command = userInput.nextInt();
            //Eats the newline
            userInput.nextLine();

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
                case 99:
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
          //Ends the loop when user enters 99
        } while (command != 99);
        //Closes scanner
        userInput.close();
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
        System.out.println("99 - Exit");
        System.out.println("\n");
    }

    //Method that takes a list of vehicles and prints them out in the formated way
    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        //Loop through each vehicle and prints out the details
        for (Vehicle vehicle : vehicles) {
            System.out.printf("%d | %d | %s | %s | %s | %s |%,d miles | $%,.2f\n",
                    vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(),
                    vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());

        }
    }

    //Method to call getAllVehicles and passes it to displayVehicles
    private void processAllVehiclesRequest() {
        ArrayList<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    //Method for handling price range input and results
    private void processGetByPriceRequest() {
        //Scanner to take user price range input
        Scanner price = new Scanner(System.in);

        //Asks user for price range and reads it as a double
        System.out.println("Enter min price: ");
        double min = price.nextDouble();
        System.out.println("Enter max price: ");
        double max = price.nextDouble();

        //Calls dealership method to find all vehicles in price range
        ArrayList<Vehicle> results = dealership.getVehiclesByPrice(min, max);
        //Uses display method to vehicles
        displayVehicles(results);

    }

    //Method to search users make model
    private void processGetByMakeModelRequest() {
        //Scanner to get user make and model
        Scanner makeModel = new Scanner(System.in);

        //Asks user for make and model
        System.out.println("Enter make: ");
        String make = makeModel.nextLine();
        System.out.println("Enter model: ");
        String model = makeModel.nextLine();

        //Calls dealership method to find all vehicles with make model
        ArrayList<Vehicle> results = dealership.getVehiclesByMakeModel(make, model);
        //Uses display method to vehicles
        displayVehicles(results);
    }

    //Method to search users make model
    private void processGetByYearRequest() {
        //Scanner to get user make and model
        Scanner year = new Scanner(System.in);

        //Asks user for min and max year
        System.out.println("Enter min year: ");
        int minYear = year.nextInt();
        System.out.println("Enter max year: ");
        int maxYear = year.nextInt();

        //Calls dealership method to find all vehicles within year range
        ArrayList<Vehicle> results = dealership.getVehiclesByYear(minYear, maxYear);
        //Uses display method to vehicles
        displayVehicles(results);
    }



}
