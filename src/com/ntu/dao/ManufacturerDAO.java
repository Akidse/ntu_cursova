package com.ntu.dao;

import com.ntu.domain.Manufacturer;

import java.util.List;

public interface ManufacturerDAO {

    Manufacturer getManufacturerById(int id);
    Manufacturer getManufacturerByName(String name);
    List<Manufacturer> getAllManufacturers();

    boolean insertManufacturer(Manufacturer manufacturer);
    boolean updateManufacturer(Manufacturer manufacturer);
    boolean deleteManufacturer(int id);
}
