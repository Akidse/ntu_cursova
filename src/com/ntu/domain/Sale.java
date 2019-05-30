package com.ntu.domain;

import java.sql.Timestamp;

public class Sale {
    private int saleId;
    private int clientId;
    private int carId;
    private Timestamp timeBought;
    private Client client;
    private Car car;

    public Sale(int saleId, int clientId, int carId, Timestamp timeBought) {
        this.saleId = saleId;
        this.clientId = clientId;
        this.carId = carId;
        this.timeBought = timeBought;
    }

    public Sale(int clientId, int carId) {
        this.clientId = clientId;
        this.carId = carId;
    }

    public Sale() {
    }

    public int getSaleId() {
        return saleId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getCarId() {
        return carId;
    }

    public Timestamp getTimeBought() {
        return timeBought;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setTimeBought(Timestamp timeBought) {
        this.timeBought = timeBought;
    }

    public Client getClient() {
        return client;
    }

    public Car getCar() {
        return car;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
