package com.jarkos.bss.controller;

import com.jarkos.bss.persistance.entity.Location;
import com.jarkos.bss.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jarek on 2015-12-25.
 */
@Controller
public class MapController {

    @Autowired
    private LocationService locationService;

    @RequestMapping("/admin/map")
    public String getMap(Model model) {
        List<String> latList = getLat(locationService.getAllLocations());
        List<String> lonList = getLon(locationService.getAllLocations());
        model.addAttribute("lat", latList);
        model.addAttribute("lon", lonList);
        return "map";
    }

    private List<String> getLat(List<Location> allLocations) {
        List<String> lat = new ArrayList<>();

        for (Location l : allLocations) {
            lat.add(l.getLatitude());
        }
        return lat;
    }

    private List<String> getLon(List<Location> allLocations) {
        List<String> lon = new ArrayList<>();

        for (Location l : allLocations) {
            lon.add(l.getLongitude());
        }
        return lon;
    }

}
