package com.jarkos.bss.service;

import com.jarkos.bss.persistance.entity.Bike;
import com.jarkos.bss.persistance.entity.Station;
import com.jarkos.bss.persistance.entity.User;
import com.jarkos.bss.persistance.enums.BikeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jarek on 2016-01-16.
 */
@Service
@Transactional
public class BookService {

    @Autowired
    private BikeService bikeService;

    @Autowired
    private UserService userService;

    @Autowired
    private StationService stationService;

    public void bookBikeOnStation(Station station, User currentUser) {
        Bike b = station.getBikes().iterator().next();
        Bike properlyBike = bikeService.findBikeById(b.getId());
        properlyBike.setEnabled(false);
        properlyBike.setBikeStatus(BikeStatus.RESERVED);
        currentUser.setBookedBike(properlyBike);
        properlyBike.setBookUser(currentUser);
        userService.update(currentUser);
        bikeService.updateBike(properlyBike);
    }
}
