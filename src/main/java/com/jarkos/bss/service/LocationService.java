package com.jarkos.bss.service;

import com.jarkos.bss.persistance.dao.LocationDao;
import com.jarkos.bss.persistance.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jarek on 2015-12-25.
 */
@Service
@Transactional
public class LocationService {

    @Autowired
    private LocationDao locationDao;

    public void saveLocation(Location location) {
        locationDao.save(location);
    }

    public List<Location> getAllLocations (){
       return locationDao.getAll();
    }
}
