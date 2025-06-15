package com.pluralsight.models;

import java.time.LocalDate;

public class SalesContract extends Contract {

    // Properties of sales contract
    private int id;
    private double totalPrice;
    private double monthlyPayment;
    private boolean isFinanced;

    // Constructor from parent class
    public SalesContract(int id, LocalDate date, String customerName, String customerEmail, Vehicle vehicle, double totalPrice, double monthlyPayment, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicle);
        this.id = id;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
        this.isFinanced = isFinanced;
    }

    // Method to get contract ID
    public int getId() {
        return id;
    }

    // Method to check if the contract is financed
    public boolean isFinanced() {
        return isFinanced;
    }

    //Method to get the price overridden from parent class
    @Override
    public double getTotalPrice() {
        return totalPrice;
    }

    //Method to get monthly price overridden from parent class
    @Override
    public double getMonthlyPayment() {
        return monthlyPayment;
        }

}
