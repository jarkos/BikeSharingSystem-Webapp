package com.jarkos.bss.service;

import com.jarkos.bss.persistance.dao.StationDao;
import com.jarkos.bss.persistance.entity.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jarek on 2015-12-15.
 */
@Service
@Transactional
public class StationService {

    @Autowired
    private StationDao stationDao;

    public Station findStationById(Integer id) {
        return stationDao.findStationById(id);
    }

    public List<Station> findAllStations(){
        return stationDao.findAllStaions();
    }

    public void saveStation(Station station) {
        stationDao.save(station);
    }

    public void deleteStation(Station station) {
        stationDao.delete(station);
    }

    public void updateStation(Station station){
        stationDao.update(station);
    }

}
