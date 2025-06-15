package com.pluralsight.dao;

import com.pluralsight.models.SalesContract;

import java.util.List;

public interface SalesContractDAO {

    // Must have method to save sales contract
    void addSalesContract(SalesContract contract);

    // Must have method to find sales contract by ID
    SalesContract getSalesContractById(int id);

    // Must have method to get sales contract
    List<SalesContract> getAllSalesContracts();

}
