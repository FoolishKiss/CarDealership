//package com.pluralsight;
//
//import com.pluralsight.models.Contract;
//import com.pluralsight.models.LeaseContract;
//import com.pluralsight.models.SalesContract;
//import com.pluralsight.models.Vehicle;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class ContractFileManager {
//
//    //Method to save a contract (Sales or Lease) to a file
//    public void saveContract(Contract contract) {
//
//        // Open the contract.csv file with append true
//        try (PrintWriter contractWriter = new PrintWriter(new FileWriter("contracts.csv", true))) {
//
//            // Print out contract info
//            contractWriter.println(contract.getDate()+ " | ");
//            contractWriter.println(contract.getCustomerName()+ " | ");
//            contractWriter.println(contract.getCustomerEmail()+ " | ");
//
//            // Get the vehicle from the contract
//            Vehicle v = contract.getVehicle();
//
//            // Print out vehicle details
//            contractWriter.print(v.getVin() + " | ");
//            contractWriter.print(v.getYear() + " | ");
//            contractWriter.print(v.getMake() + " | ");
//            contractWriter.print(v.getModel() + " | ");
//            contractWriter.print(v.getVehicleType() + " | ");
//            contractWriter.print(v.getColor() + " | ");
//            contractWriter.print(v.getOdometer() + " | ");
//            contractWriter.printf("$%-9.2f | ", v.getPrice());
//
//            // Get the specific info for the contract
//            if (contract instanceof SalesContract sc) {
//                contractWriter.printf("SALE     | Total: $%-10.2f | Monthly: $%-8.2f",
//                        sc.getTotalPrice(), sc.getMonthlyPayment());
//
//            } else if (contract instanceof LeaseContract lc) {
//                contractWriter.printf("LEASE     | Total: $%-10.2f | Monthly: $%-8.2f",
//                        lc.getTotalPrice(), lc.getMonthlyPayment());
//
//            }
//            // Spacing
//            contractWriter.println();
//
//        }catch (IOException e) {
//            System.out.println("Error saving contract");
//            e.printStackTrace();
//        }
//    }
//}
