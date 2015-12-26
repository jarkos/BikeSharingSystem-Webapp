package com.jarkos.bss.persistance.entity;

import com.jarkos.bss.persistance.enums.BikeStatus;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by Jarek on 2015-12-01.
 */
@Entity
@AttributeOverride(name = "id", column = @Column(name = "bike_id"))
public class Bike extends Persistent {

    private static final long serialVersionUID = 4964532837112242431L;

    @NotEmpty
    @Column(nullable = false)
    private String manufacturer;

    @NotEmpty
    @Column(nullable = false)
    private String model;

    @NotEmpty
    @Column(nullable = false,  unique = true)
    private String serialNumber;

    @Column(nullable = false, name = "status")
    @Enumerated(EnumType.STRING)
    private BikeStatus bikeStatus;

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

    public BikeStatus getBikeStatus() {
        return bikeStatus;
    }

    public void setBikeStatus(BikeStatus bikeStatus) {
        this.bikeStatus = bikeStatus;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
