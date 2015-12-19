package com.jarkos.bss.controller;

import com.jarkos.bss.service.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
