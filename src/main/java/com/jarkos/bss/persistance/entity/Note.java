package com.jarkos.bss.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by Jarek on 2015-12-27.
 */
@Entity
public class Note extends Persistent{

    private static final long serialVersionUID = -7388516679639096802L;
    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name="bike_id", nullable=false)
    private Bike bike;


    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
