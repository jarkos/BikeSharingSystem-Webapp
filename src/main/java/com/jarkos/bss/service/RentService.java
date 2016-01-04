package com.jarkos.bss.service;

import com.jarkos.bss.persistance.dto.BorrowOperationDto;
import com.jarkos.bss.persistance.entity.Bike;
import com.jarkos.bss.persistance.entity.Station;
import com.jarkos.bss.persistance.entity.User;
import com.jarkos.bss.persistance.enums.BikeStatus;
import com.jarkos.bss.persistance.exceptions.CannotBorrowMoreBikesException;
import com.jarkos.bss.persistance.exceptions.NotRequiredAccountBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jarek on 2015-12-28.
 */
@Service
@Transactional
public class RentService {

    private static final int MONEY_FOR_BIKE_BORROWING = 5;

    @Autowired
    private BikeService bikeService;

    @Autowired
    private UserService userService;

    @Autowired
    private StationService stationService;

    @Transactional(rollbackFor = Exception.class)
    public void borrowBike(BorrowOperationDto borrowOperationDto) throws NotRequiredAccountBalanceException, CannotBorrowMoreBikesException {
        User user = userService.findUserById(borrowOperationDto.getUserId());
        Bike bike = bikeService.findBikeById(borrowOperationDto.getBikeId());
        Station station = stationService.findStationById(borrowOperationDto.getStationId());
        if (canBorrow(user)) {
            user.setAccountBalance(user.getAccountBalance() - MONEY_FOR_BIKE_BORROWING);
            bike.setStation(null);
            station.setTakenSpaces(station.getTakenSpaces() - 1);
            removeBikeFromStation(station, bike);
            bike.setBikeStatus(BikeStatus.BORROWED);
            user.setAccountBalance(user.getAccountBalance() - MONEY_FOR_BIKE_BORROWING);
            user.setBorrowedBike(bike);
            userService.update(user);
            bikeService.updateBike(bike);
            stationService.updateStation(station);
        }
    }

    private void removeBikeFromStation(Station station, Bike bike) {
        station.getBikes().remove(bike);
    }

    private boolean canBorrow(User user) throws NotRequiredAccountBalanceException, CannotBorrowMoreBikesException {

        if (user.isAccountNonExpired() && !user.isLocked() && user.isEnabled()) {
            if (user.getAccountBalance() >= 5) {
                return true;
            } else if (user.getBorrowedBike() != null) {
                throw new CannotBorrowMoreBikesException();
            } else {
                throw new NotRequiredAccountBalanceException();
            }
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    public void returnBike(BorrowOperationDto borrowOperationDto) {
        User user = userService.findUserById(borrowOperationDto.getUserId());
        Bike bike = bikeService.findBikeById(borrowOperationDto.getBikeId());
        Station station = stationService.findStationById(borrowOperationDto.getStationId());

        bike.setBikeStatus(BikeStatus.TO_BORROW);
        bike.setStation(station);
        station.setTakenSpaces(station.getTakenSpaces() + 1);
        station.getBikes().add(bike);
        user.setBorrowedBike(null);
        userService.update(user);
        bikeService.updateBike(bike);
        stationService.updateStation(station);
    }
}
