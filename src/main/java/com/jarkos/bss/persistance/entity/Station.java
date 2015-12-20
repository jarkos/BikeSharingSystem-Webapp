package com.jarkos.bss.persistance.entity;

import javax.persistence.*;

/**
 * Created by Jarek on 2015-12-15.
 */
@Entity
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

//    @ElementCollection(targetClass = Bike.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "bike", joinColumns = @JoinColumn(name = "bike_id"))
//    @Column(name = "bikes", nullable = true)
//    private Collection<Bike> bikes;

    public String getLatitude() {
        return latitude;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setAdress(String address) {
        this.locationAddress = address;
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

//    public Collection<Bike> getBikes() {
//        return bikes;
//    }
//
//    public void setBikes(Collection<Bike> bikes) {
//        this.bikes = bikes;
//    }

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
