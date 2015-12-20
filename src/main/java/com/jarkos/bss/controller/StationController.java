package com.jarkos.bss.controller;

import com.jarkos.bss.persistance.entity.Bike;
import com.jarkos.bss.persistance.entity.Station;
import com.jarkos.bss.service.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by Jarek on 2015-12-15.
 */

@Controller
public class StationController {

    private static final Logger log = LoggerFactory.getLogger(StationController.class);

    @Autowired
    private StationService stationService;

    @RequestMapping("/admin/stations")
    public String getStationsList(Model model) {
        log.debug("getStationList");
        model.addAttribute(stationService.findAllStations());
        return "stations";
    }

    @RequestMapping(value = "/admin/stations/create", method = RequestMethod.GET)
    public String createNewStationForm(Model model) {
        model.addAttribute("station", new Station());
        return "stations-create";
    }

    @RequestMapping(value = "/admin/stations/create", method = RequestMethod.POST)
    public String createNewStation(@Valid Station station, BindingResult bindingResult) {

        if (stationService.findStationById(station.getId()) != null) {
            bindingResult.rejectValue("station", "error.stationId.exist");
        }
        if (bindingResult.hasErrors()) {
            return "stations-create";
        }
        station.setAdress("adresik"); //TODO poprawic dodawanie adresu
        station.setTakenSpaces(0);
        stationService.saveStation(station);

        return "redirect:/admin/stations?created";
    }

}