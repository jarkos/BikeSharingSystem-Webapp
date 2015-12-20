package com.jarkos.bss.persistance.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jarek on 2015-12-15.
 */
@Entity
@AttributeOverride(name = "id", column = @Column(name = "station_id"))
public class Station extends Persistent{

    private static final long serialVersionUID = 3402431765055829231L;

    @Column(nullable = true)
    private String locationAddress;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private int spaceNumber;

    @Column
    private int takenSpaces;

    @Column(name = "listOfBikes", nullable = true)
    @ElementCollection(targetClass = Bike.class, fetch = FetchType.EAGER)
    private Set<Bike> bikes = new HashSet<>();

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLatitude() {
        return latitude;
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

    public Set<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(Set<Bike> bikes) {
        this.bikes = bikes;
    }
}
