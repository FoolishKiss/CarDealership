package com.pluralsight.models;

import java.time.LocalDate;

public abstract class Contract {

    //Fields every contract will have
    private LocalDate date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicle;

    // Constructor
    public Contract(LocalDate date, String customerName, String customerEmail, Vehicle vehicle) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicle = vehicle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    //Method to get price that child class will have to have
    public abstract double getTotalPrice();

    //Method to get monthly payment that child class will have to have
    public abstract double getMonthlyPayment();


}
