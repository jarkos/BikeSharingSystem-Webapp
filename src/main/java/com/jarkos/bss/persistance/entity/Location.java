package com.jarkos.bss.persistance.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Jarek on 2015-12-25.
 */
@Entity
public class Location implements Serializable{

    private static final long serialVersionUID = -3742577140770355954L;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="station_id")
    private Station station;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id")
    private Integer id;

    @Column(nullable = true, unique = true)
    @NotEmpty
    private String locationAddress;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String latitude;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String longitude;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

}
