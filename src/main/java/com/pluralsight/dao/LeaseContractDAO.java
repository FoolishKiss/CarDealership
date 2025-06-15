package com.pluralsight.dao;

import com.pluralsight.models.LeaseContract;

import java.util.List;

public interface LeaseContractDAO {

    void addLeaseContract(LeaseContract contract);

    LeaseContract getLeaseContractById(int id);

    List<LeaseContract> getAllLeaseContracts();

}
