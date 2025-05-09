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
        System.out.println("99 - Exit");
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
}
