package com.jarkos.bss.persistance.dto;

import java.io.Serializable;

/**
 * Created by Jarek on 2016-01-02.
 */
public class BorrowOperationDto extends BaseBikeOperationDto implements Serializable {

    protected Integer bikeId;
    protected Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public void setBikeId(Integer bikeId) {
        this.bikeId = bikeId;
    }

    public BorrowOperationDto() {

    }
}
