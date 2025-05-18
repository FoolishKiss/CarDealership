package com.pluralsight;

public class SalesContract extends Contract{

    // Fields of the sales contract
    // True if buyer is financing
    private boolean isFinanced;
    // The processing fee is based on the vehicle price
    private double processingFee;

    // The sales tax, and recording fee are constant
    private static final double SALES_TAX = 0.05;
    private static final double RECORDING_FEE = 100;

    // Constructor from parent class
    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean finance) {
        super(date, customerName, customerEmail, vehicle);
        // Sales contract field
        this.isFinanced = finance;

        // checks price of vehicle to set processing fee
        if (vehicle.getPrice() < 10000) {
            this.processingFee = 295.00;
        } else {
            this.processingFee = 495.00;
        }
    }

    //Method to get the price overridden from parent class
    @Override
    public double getTotalPrice() {
        // price of vehicle
        double basePrice = getVehicle().getPrice();
        // Tax on vehicle
        double tax = basePrice * SALES_TAX;

        // Processing fee if the price is
        // lower than 10000 than add 295.0 if not than 495.0
        double processingFee = basePrice < 10000 ? 295.0 : 495.0;

        //
        return basePrice + tax + RECORDING_FEE + processingFee;
    }

    //Method to get monthly price overridden from parent class
    @Override
    public double getMonthlyPayment() {
        //If not financed
        if (!isFinanced){
            // return 0
            return 0;
        }

        // call get price method
        double price = getTotalPrice();
        // Gets the interest rates if the car is greater than or equal 10000 than the
        // interest rate is 0.0425 if not than 0.0525
        double interestRate = price >= 10000 ? 0.0425 : 0.0525;
        //If the car is greater than or equal to 10000 than the number
        // of months is 48 if not than its 24
        int months = price >= 10000 ? 48 : 24;
        // Returns the monthly payment
        return price * (1 + interestRate) / months;
    }

}
