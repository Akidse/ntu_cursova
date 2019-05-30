package com.ntu.dao;

import com.ntu.domain.Dealership;

import java.util.List;

public interface DealershipDAO {

    Dealership getDealershipById(int id);
    Dealership getDealershipByName(String name);
    List<Dealership> getAllDealerships();

    boolean insertDealership(Dealership dealership);
    boolean updateDealership(Dealership dealership);
    boolean deleteDealership(int id);
}
