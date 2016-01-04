package com.jarkos.bss.persistance.dto;

import java.io.Serializable;

/**
 * Created by Jarek on 2016-01-02.
 */
public class BorrowOperationDto implements Serializable{

    private Long bike;

    public Long getBike() {
        return bike;
    }

    public void setBike(Long bike) {
        this.bike = bike;
    }

    public BorrowOperationDto(Long bike) {
        this.bike = bike;
    }

    public BorrowOperationDto() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BorrowOperationDto)) return false;

        BorrowOperationDto that = (BorrowOperationDto) o;

        if (bike != null ? !bike.equals(that.bike) : that.bike != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return bike != null ? bike.hashCode() : 0;
    }
}
