package com.jarkos.bss.controller;

import com.jarkos.bss.persistance.entity.Bike;
import com.jarkos.bss.persistance.entity.Location;
import com.jarkos.bss.persistance.entity.Station;
import com.jarkos.bss.persistance.enums.BikeStatus;
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
import java.util.List;

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
        model.addAttribute("station", station);
        return "stations-create";
    }

    @RequestMapping(value = "/admin/stations/create", method = RequestMethod.POST)
    public String createNewStation(@Valid Station station, BindingResult bindingResult) throws Exception {

        if (stationService.findStationById(station.getId()) != null) {
            bindingResult.rejectValue("station", "error.stationId.exist");
        }
        if (bindingResult.hasErrors()) {
            return "stations?create=false";
        }
        station.setTakenSpaces(0);
        Station newStation = stationService.saveStation(station);
        Location location = station.getLocation();
        location.setStation(newStation);
        locationService.saveLocaiton(location);
        return "redirect:/admin/stations?create=true";
    }

    @RequestMapping(value = "/admin/stations/{id}/delete", method = RequestMethod.GET)
    public String deleteStationForm(@PathVariable int id) {
        if (id != 0) {
            Station stationToDelete = stationService.findStationById(id);
            if (!stationToDelete.getBikes().isEmpty()) {
                log.debug("deleteStationBikesFull, id={}", id);
                System.out.printf("ERROR. Na stacji " + id + " s¹ dostêpne rowery!");
                return "redirect:/admin/stations?deleted=false";
            } else if (stationToDelete != null) {
                stationService.deleteStation(stationToDelete);
            } else {
                log.debug("deleteStationNoId, id={}", id);
                System.out.printf("ERROR. Nie ma stacji o takim ID: " + id);
            }
            return "redirect:/admin/stations?deleted=true";
        }
        return "redirect:/admin/stations?deleted=false";
    }

    @RequestMapping(value = "/admin/stations/{id}/edit", method = RequestMethod.GET)
    public String editStationForm(@PathVariable int id, Model model) {
        Station station = stationService.findStationById(id);
        model.addAttribute("station", station);
        model.addAttribute("bikes", station.getBikes());
        model.addAttribute("availableBikes", getAvailableBikesFromOtherPlaces
                (station, bikeService.findAllAvailableBikes()));
        return "stations-edit";
    }

    private List<Bike> getAvailableBikesFromOtherPlaces(Station station, List<Bike> allAvailableBikes) {
        station.getBikes().stream().filter(bike -> allAvailableBikes.contains(bike)).forEach(allAvailableBikes::remove);
        return allAvailableBikes;
    }

    @RequestMapping(value = "/admin/stations/{id}/edit", method = RequestMethod.POST)
    public String editStation(@Valid Station station, @PathVariable int id) {
        log.debug("editStation, id={}", id);
        stationService.updateStation(station);
        return "redirect:/admin/stations?edited=true";
    }

    @RequestMapping(value = "/admin/stations/{id}/profile", method = RequestMethod.GET)
    public String getStationProfile(@PathVariable int id, Model model) {
        Station station = stationService.findStationById(id);
        model.addAttribute("station", station);
        model.addAttribute("bikes", station.getBikes());
        return "stations-profile";
    }

    @RequestMapping(value = "/admin/stations/{stationId}/edit/bikes/{bikeId}/addBike", method = RequestMethod.GET)
    public String addBikeToStation(@PathVariable int stationId, @PathVariable int bikeId, Model model) {
        log.debug("addBikeToStation, stationId={}", stationId);
        Station station = stationService.findStationById(stationId);
        Bike bikeToAdd = bikeService.findBikeById(bikeId);
        bikeToAdd.setBikeStatus(BikeStatus.CHANGING_STATION);
        bikeToAdd.setStation(station);
        station.getBikes().add(bikeToAdd);
        //bikeService.updateBike(bikeToAdd);
        stationService.updateStation(station);
        model.addAttribute("id", stationId);
        return "redirect:/admin/stations/{id}/edit?created=true";
    }

}
