package com.ntu.dao;

import com.ntu.domain.Car;

import java.util.List;

public interface CarDAO {

    List<Car> getAllCars();
    List<Car> getCarsByDealerShipId(int id);
    List<Car> getFilteredCarsByDealershipId(int id);

    boolean insertCar(Car car);
    boolean updateCar(Car car);
    boolean deleteCar(int id);
}
