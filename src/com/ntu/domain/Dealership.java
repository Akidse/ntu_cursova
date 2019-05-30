package com.ntu.domain;

import com.ntu.DateUtil;

import java.io.Serializable;
import java.sql.Timestamp;

public class Dealership implements Serializable {
    private int dealershipId;
    private String title;
    private String description;
    private Timestamp dateCreated;
    private String status;

    public Dealership(int dealershipId, String title, String description, Timestamp dateCreated, String status) {
        this.dealershipId = dealershipId;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.status = status;
    }

    public Dealership(String title, String description, Timestamp dateCreated, String status) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.status = status;
    }

    public Dealership(String title) {
        this.title = title;
        this.description = "";
        this.dateCreated = DateUtil.getCurrentTimestamp();
        this.status = "new";
    }

    public Dealership(int dealershipId, String title) {
        this.dealershipId = dealershipId;
        this.title = title;
        this.description = "";
        this.dateCreated = DateUtil.getCurrentTimestamp();
        this.status = "new";
    }

    public Dealership() {
    }

    public void setDealershipId(int dealershipId) {
        this.dealershipId = dealershipId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDealershipId() {
        return dealershipId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Dealership{" +
                "dealershipId=" + dealershipId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated=" + dateCreated +
                ", status='" + status + '\'' +
                '}';
    }
}
