package com.pluralsight;

public class LeaseContract extends Contract{

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
    }

    // Overridden method to get total price with lease
    @Override
    public double getTotalPrice() {
        double price = getVehicle().getPrice();

        return price + (price * 0.07);
    }

    // Overridden method to get monthly payment
    @Override
    public double getMonthlyPayment() {
        double price = getVehicle().getPrice();
        double endValue = price * 0.5;
        double leaseFee = price * 0.07;

        return ((price - endValue) / 36) + leaseFee;
    }

}
