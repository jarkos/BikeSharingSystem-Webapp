package com.jarkos.bss.persistance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by Jarek on 2016-01-06.
 */
public class BaseBikeOperationDto {

    protected Integer stationId;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
    protected Date operationDate;

    public BaseBikeOperationDto() {
    }

    public Integer getStationId() {

        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }
}
