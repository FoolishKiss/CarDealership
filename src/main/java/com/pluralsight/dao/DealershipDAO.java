package com.pluralsight.dao;

import com.pluralsight.models.Dealership;

import java.util.List;

public interface DealershipDAO {

    // Must have method to get dealership
    List<Dealership> getAllDealerships();

    // Must have method to find dealership
    Dealership getDealershipById(int dealershipId);

}
