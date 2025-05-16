package com.pluralsight;

import java.io.*;

public class DealershipFileManager {

    //Method to make dealership objects. it reads from file
    public Dealership getDealership() {

        //Variable to hold dealership objects
        Dealership dealership = null;

        //Need to read files
        try {
            //Read inventory file line by line
            BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"));

            //Read first line of the file and split it by |, and store the parts in dealerInfo a string[]
            String[] dealerInfo = reader.readLine().split("\\|");

            //Use the values from dealer info to instantiate dealership object
            dealership = new Dealership(dealerInfo[0], dealerInfo[1], dealerInfo[2]);

            //Variable to hold vehicle data
            String line;
            //Loops through the rest of the file line by line until no more lines
            while ((line = reader.readLine()) != null) {

                //Splits each line into vehilce data by the |
                String[] parts = line.split("\\|");

                //Parse each string into appropriate type
                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String type = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                //Creates a new vehicle using data from the current line
                Vehicle vehicle = new Vehicle(vin, year, make, model,type, color, odometer,price);
                //Add the vehicle to the dealership inventory
                dealership.addVehicle(vehicle);
            }
            //Close buffer reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dealership;
    }

    //Method to save dealership
    public void saveDealership(Dealership dealership) {

        // Uses print writer to write text to inventory file. Using try with resources auto closes resources
        try (PrintWriter writer = new PrintWriter(new FileWriter("inventory.csv"))) {

            // Writes the first line dealership info
            writer.println(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());

            // Loops through vehicles in the inventory and prints the details
            for (Vehicle v : dealership.getAllVehicles()) {
                writer.printf("%d|%d|%s|%s|%s|%s|%d|%.2f\n",
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                        v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());

            }




        } catch (IOException e) {
            System.out.println("Error saving Dealership info.");
            e.printStackTrace();
        }
    }
}