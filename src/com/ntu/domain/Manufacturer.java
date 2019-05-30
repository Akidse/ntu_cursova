package com.ntu.domain;

import java.io.Serializable;

public class Manufacturer implements Serializable {
    private int manufacturerId;
    private String name;

    public Manufacturer() {
        super();
    }

    public Manufacturer(int manufacturerId, String name) {
        this.manufacturerId = manufacturerId;
        this.name = name;
    }

    public Manufacturer(String name) {
        this.name = name;
        this.manufacturerId = 0;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public String getName() {
        return name;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "manufacturerId=" + manufacturerId +
                ", name='" + name + '\'' +
                '}';
    }
}
