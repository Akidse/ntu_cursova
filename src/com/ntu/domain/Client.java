package com.ntu.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Client implements Serializable {
    private int clientId;
    private int dealershipId;
    private String firstName;
    private String lastName;
    private Timestamp lastVisit;
    private Timestamp firstVisit;
    private int carsCount;

    public Client(int clientId, int dealershipId, String firstName, String lastName, Timestamp lastVisit, Timestamp firstVisit) {
        this.clientId = clientId;
        this.dealershipId = dealershipId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastVisit = lastVisit;
        this.firstVisit = firstVisit;
    }

    public Client(int dealershipId, String firstName, String lastName, Timestamp lastVisit, Timestamp firstVisit) {
        this.dealershipId = dealershipId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastVisit = lastVisit;
        this.firstVisit = firstVisit;
    }

    public Client() {
    }

    public int getCarsCount() {
        return carsCount;
    }

    public void setCarsCount(int carsCount) {
        this.carsCount = carsCount;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setDealershipId(int dealershipId) {
        this.dealershipId = dealershipId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLastVisit(Timestamp lastVisit) {
        this.lastVisit = lastVisit;
    }

    public void setFirstVisit(Timestamp firstVisit) {
        this.firstVisit = firstVisit;
    }

    public int getClientId() {
        return clientId;
    }

    public int getDealershipId() {
        return dealershipId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Timestamp getLastVisit() {
        return lastVisit;
    }

    public Timestamp getFirstVisit() {
        return firstVisit;
    }

    public String getFullName() {
        return lastName+" "+firstName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", dealershipId=" + dealershipId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lastVisit=" + lastVisit +
                ", firstVisit=" + firstVisit +
                ", carsCount=" + carsCount +
                '}';
    }
}
