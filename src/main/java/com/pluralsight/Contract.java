package com.pluralsight;

public abstract class Contract {

    //Fields every contract will have
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicle;

    // Constructor
    public Contract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicle = vehicle;
    }

    // Getters
    public String getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    //Method to get price that child class will have to have
    public abstract double getTotalPrice();

    //Method to get monthly payment that child class will have to have
    public abstract double getMonthlyPayment();


}
