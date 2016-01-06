package com.jarkos.bss.persistance.entity;

import com.jarkos.bss.persistance.enums.OperationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by Jarek on 2016-01-06.
 */
@Entity
public class History extends Persistent{

    private static final long serialVersionUID = 5468835564229029604L;

    @Column(nullable = false, name = "user_id")
    private Integer userId;

    @Column(nullable = false, name = "user_login")
    private String userLogin;

    @Column(nullable = false, name = "station_id")
    private Integer stationId;

    @Column(nullable = false, name = "bike_id")
    private Integer bikeId;

    @Column(nullable = false, name = "operation_type")
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public void setBikeId(Integer bikeId) {
        this.bikeId = bikeId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
