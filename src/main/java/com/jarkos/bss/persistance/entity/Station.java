package com.jarkos.bss.persistance.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jarek on 2015-12-15.
 */
@Entity
@AttributeOverride(name = "id", column = @Column(name = "station_id"))
public class Station extends Persistent {

    private static final long serialVersionUID = 3402431765055829231L;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "station", cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location = new Location();

    @Column(nullable = false)
    @Min(value = 1)
    private int spaceNumber;

    @Column
    private int takenSpaces;

    //    @Column(name = "listOfBikes", nullable = true)
//    @ElementCollection(targetClass = Bike.class, fetch = FetchType.EAGER)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "station", fetch = FetchType.EAGER)
    private Set<Bike> bikes = new HashSet<>();

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public void setSpaceNumber(int spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    public int getTakenSpaces() {
        if (bikes.size() > 0) {
            return bikes.size();
        }
        else {
            return 0;
        }
    }

    public void setTakenSpaces(int takenSpaces) {
        this.takenSpaces = takenSpaces;
    }

    public Set<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(Set<Bike> bikes) throws Exception {
        if (bikes.size() > (getSpaceNumber() - getTakenSpaces())) {
            throw new Exception("To many bikes for station!");
        }
        this.bikes = bikes;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
