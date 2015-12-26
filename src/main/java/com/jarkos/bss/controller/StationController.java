package com.jarkos.bss.controller;

import com.jarkos.bss.persistance.entity.Bike;
import com.jarkos.bss.persistance.entity.Location;
import com.jarkos.bss.persistance.entity.Station;
import com.jarkos.bss.service.BikeService;
import com.jarkos.bss.service.LocationService;
import com.jarkos.bss.service.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jarek on 2015-12-15.
 */

@Controller
public class StationController {

    private static final Logger log = LoggerFactory.getLogger(StationController.class);

    @Autowired
    private StationService stationService;

    @Autowired
    private BikeService bikeService;

    @Autowired
    private LocationService locationService;

    @RequestMapping("/admin/stations")
    public String getStationsList(Model model) {
        log.debug("getStationList");
        model.addAttribute(stationService.findAllStations());
        return "stations";
    }

    @RequestMapping(value = "/admin/stations/create", method = RequestMethod.GET)
    public String createNewStationForm(Model model) {
        Location location = new Location();
        Station station = new Station();
        station.setLocation(location);
        model.addAttribute("station",station);
        return "stations-create";
    }

    @RequestMapping(value = "/admin/stations/create", method = RequestMethod.POST)
    public String createNewStation(@Valid Station station, BindingResult bindingResult) {

        if (stationService.findStationById(station.getId()) != null) {
            bindingResult.rejectValue("station", "error.stationId.exist");
        }
        if (bindingResult.hasErrors()) {
            return "stations-create?created=false";
        }
        //mock
        Set<Bike> bi =  new HashSet<Bike>();
        Bike b1 = new Bike();
        b1.setManufacturer("DOPSK");
        b1.setModel("MODELCYS");
        b1.setSerialNumber("1242134" + Math.random());
        bikeService.saveNewBike(b1);
        bi.add(b1);
        station.setBikes(bi);
        station.setTakenSpaces(0);
        Station newStation = stationService.saveStation(station);
        Location location = station.getLocation();
        location.setStation(newStation);
        locationService.saveLocaiton(location);
        return "redirect:/admin/stations?created=true";
    }

    @RequestMapping(value = "/admin/stations/{id}/delete", method = RequestMethod.GET)
    public String deleteStationForm(@PathVariable int id) {
        if (id != 0) {
            Station stationToDelete = stationService.findStationById(id);
            if (stationToDelete != null) {
                stationService.deleteStation(stationToDelete);
            } else {
                log.debug("deleteStation, id={}", id);
                System.out.printf("ERROR. Nie ma stacji o takim ID: " + id);
            }
            return "redirect:/admin/stations?deleted=true";
        }
        return "redirect:/admin/stations?deleted=false";
    }
}
