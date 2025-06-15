package com.pluralsight.models;

import java.time.LocalDate;

public class LeaseContract extends Contract {

    private int id;
    private double totalPrice;
    private double monthlyPayment;

    public LeaseContract(int id, LocalDate date, String customerName, String customerEmail, Vehicle vehicle, double totalPrice, double monthlyPayment) {
        super(date, customerName, customerEmail, vehicle);
        this.id = id;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    // Method to get Id
    public int getId() {
        return id;
    }

    // Overridden method to get total price with lease
    @Override
    public double getTotalPrice() {
      return totalPrice;
    }

    // Overridden method to get monthly payment
    @Override
    public double getMonthlyPayment() {
        return monthlyPayment;
    }

}
