package com.ntu.domain;

import java.io.Serializable;
import java.util.Date;

public class Manager implements Serializable {
    private int managerId;
    private String firstName;
    private String lastName;
    private int roleId;
    private Date dateHired;
    private String status;

    public Manager(int managerId, String firstName, String lastName, int roleId, Date dateHired, String status) {
        this.managerId = managerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleId = roleId;
        this.dateHired = dateHired;
        this.status = status;
    }

    public Manager(String firstName, String lastName, int roleId, Date dateHired, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleId = roleId;
        this.dateHired = dateHired;
        this.status = status;
    }

    public Manager() {
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getManagerId() {
        return managerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getRoleId() {
        return roleId;
    }

    public Date getDateHired() {
        return dateHired;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roleId=" + roleId +
                ", dateHired=" + dateHired +
                ", status='" + status + '\'' +
                '}';
    }
}
