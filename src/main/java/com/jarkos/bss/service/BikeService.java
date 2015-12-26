package com.jarkos.bss.service;

import com.jarkos.bss.persistance.dao.BikeDao;
import com.jarkos.bss.persistance.entity.Bike;
import com.jarkos.bss.persistance.enums.BikeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jarek on 2015-12-01.
 */

@Service
@Transactional
public class BikeService {

    @Autowired
    private BikeDao bikeDao;

    public Bike findBikeById(Integer id) {
        return bikeDao.findBikeById(id);
    }

    public List<Bike> findAllBikes(){
        return bikeDao.findAllBikes();
    }

    public List<Bike> findAllNewBikes(){
        return bikeDao.findAllNewBikes();
    }

    public void saveNewBike(Bike bike) {
        bike.setEnabled(true);
        bike.setBikeStatus(BikeStatus.NEW);
        bikeDao.save(bike);
    }

    public void deleteBike(Bike bike) {
        bikeDao.delete(bike);
    }

    public void updateBike(Bike bike){
        bikeDao.update(bike);
    }

    public List<Bike> findAllAvailableBikes() {
        return bikeDao.findAllAvailableBikes();
    }
}
