package com.ntu.dao;

import com.ntu.domain.Sale;

import java.util.List;

public interface SaleDAO {

    List<Sale> getSalesByClientId(int id);
    List<String> getNameAndPriceCarByClientId(int id);

    boolean insertSale(Sale sale);
    boolean deleteSale(int id);
}
