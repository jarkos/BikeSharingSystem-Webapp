package com.jarkos.bss.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Jarek on 2015-12-01.
 */
@Entity
public class Bike extends Persistent {

    private static final long serialVersionUID = 4964532837112242431L;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private boolean enabled;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
