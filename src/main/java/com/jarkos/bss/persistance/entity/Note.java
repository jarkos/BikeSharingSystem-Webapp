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
    @JoinColumn(name="bike_id", nullable=true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        if (!super.equals(o)) return false;

        Note note = (Note) o;

        if (description != null ? !description.equals(note.description) : note.description != null) return false;
        return !(bike != null ? !bike.equals(note.bike) : note.bike != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (bike != null ? bike.hashCode() : 0);
        return result;
    }

}
