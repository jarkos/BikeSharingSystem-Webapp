package com.jarkos.bss.service;

import com.jarkos.bss.persistance.entity.Bike;
import com.jarkos.bss.persistance.entity.Station;
import com.jarkos.bss.persistance.enums.BikeStatus;
import com.jarkos.bss.persistance.exceptions.NotEnoughtSpaceOnStationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jarek on 2016-01-06.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BikeTransferService {

    @Autowired
    private BikeService bikeService;

    @Autowired StationService stationService;

    public void transferBikesFormStation(TransferBikesOperationDto transferBikes) {
        Station station = stationService.findStationById(transferBikes.getStationId());
        for(Integer id : transferBikes.getBikeIds()){
            Bike bike = bikeService.findBikeById(id);
            bike.setStation(null);
            bike.setBikeStatus(BikeStatus.CHANGING_STATION);
            station.setTakenSpaces(station.getSpaceNumber()-1);
            removeBikeFromStation(station,bike);
            bikeService.updateBike(bike);
            stationService.updateStation(station);
        }
    }

    private void removeBikeFromStation(Station station, Bike bike) {
        station.getBikes().remove(bike);
    }

    public void transferBikesToStation(TransferBikesOperationDto transferBikes) throws NotEnoughtSpaceOnStationException {
        Station station = stationService.findStationById(transferBikes.getStationId());
        for(Integer id : transferBikes.getBikeIds()){
            if(station.getTakenSpaces() < station.getSpaceNumber()) {
                Bike bike = bikeService.findBikeById(id);
                bike.setStation(station);
                bike.setBikeStatus(BikeStatus.TO_BORROW);
                station.setTakenSpaces(station.getSpaceNumber() + 1);
                station.getBikes().add(bike);
                bikeService.updateBike(bike);
                stationService.updateStation(station);
            }
            else{
                throw new NotEnoughtSpaceOnStationException();
            }
        }

    }
}
