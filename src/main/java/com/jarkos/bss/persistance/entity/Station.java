package com.jarkos.bss.persistance.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Jarek on 2015-12-15.
 */
@Entity
public class Station extends Persistent{

    private static final long serialVersionUID = 3402431765055829231L;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private int spaceNumber;

    @Column(nullable = false)
    private int takenSpaces;

    @NotEmpty
    @Column(name = "bikes", nullable = false)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private Collection<Bike> bikes;

    public String getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Collection<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(Collection<Bike> bikes) {
        this.bikes = bikes;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public void setSpaceNumber(int spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    public int getTakenSpaces() {
        return takenSpaces;
    }

    public void setTakenSpaces(int takenSpaces) {
        this.takenSpaces = takenSpaces;
    }
}
