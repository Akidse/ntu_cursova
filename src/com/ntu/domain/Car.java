package com.ntu.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Car implements Serializable {
    private int carId;
    private int dealershipId;
    private String model;
    private int manufacturerId;
    private Timestamp lastAdded;
    private int count;
    private boolean available;
    private int price;
    private Manufacturer manufacturer;

    public Car(int carId, int dealershipId, String model, int manufacturerId, Timestamp lastAdded, int count, boolean available, int price) {
        this.carId = carId;
        this.dealershipId = dealershipId;
        this.model = model;
        this.manufacturerId = manufacturerId;
        this.lastAdded = lastAdded;
        this.count = count;
        this.available = available;
        this.price = price;
    }

    public Car(int dealershipId, String model, int manufacturerId, Timestamp lastAdded, int count, boolean available, int price) {
        this.dealershipId = dealershipId;
        this.model = model;
        this.manufacturerId = manufacturerId;
        this.lastAdded = lastAdded;
        this.count = count;
        this.available = available;
        this.price = price;
    }

    public Car() {
    }

    public void setDealershipId(int dealershipId) {
        this.dealershipId = dealershipId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public void setLastAdded(Timestamp lastAdded) {
        this.lastAdded = lastAdded;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDealershipId() {
        return dealershipId;
    }

    public int getCarId() {
        return carId;
    }

    public String getModel() {
        return model;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public Timestamp getLastAdded() {
        return lastAdded;
    }

    public int getCount() {
        return count;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getPrice() {
        return price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", model='" + model + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", lastAdded=" + lastAdded +
                ", count=" + count +
                ", available=" + available +
                ", price=" + price +
                '}';
    }
}
