package com.jarkos.bss.controller;

import com.jarkos.bss.persistance.entity.Location;
import com.jarkos.bss.persistance.entity.Station;
import com.jarkos.bss.service.LocationService;
import com.jarkos.bss.service.StationService;
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

    @Autowired
    private StationService stationService;

    @RequestMapping("/admin/map")
    public String getMap(Model model) {
        List<Station> stations = stationService.findAllStations();
        List<String> latList = getLat(stations);
        List<String> lonList = getLon(stations);
        List<String> addressesList = getAddresses(stations);
        List<String> spaceList = getSpaceNumbers(stations);
        List<String> takenList = getTakenNumbers(stations);
        List<String> freeBikesList = getFreeBikesNumber(stations);
        List<String> idsList = getIds(stations);
        model.addAttribute("lat", latList);
        model.addAttribute("lon", lonList);
        model.addAttribute("addresses", addressesList);
        model.addAttribute("spaces", spaceList);
        model.addAttribute("taken", takenList);
        model.addAttribute("freeBikes", freeBikesList);
        model.addAttribute("ids", idsList);
        return "map";
    }

    private List<String> getFreeBikesNumber(List<Station> stations) {
        List<String> freeBikesNmb = new ArrayList<>();
        for (Station s : stations) {
            freeBikesNmb.add(Integer.toString(s.getBikes().size()));
        }
        return freeBikesNmb;
    }

    private List<String> getIds(List<Station> stations) {
        List<String> ids = new ArrayList<>();
        for (Station s : stations) {
            ids.add(Integer.toString(s.getId()));
        }
        return ids;
    }

    private List<String> getTakenNumbers(List<Station> stations) {
        List<String> takenNumbers = new ArrayList<>();
        for (Station s : stations) {
            takenNumbers.add(Integer.toString(s.getTakenSpaces()));
        }
        return takenNumbers;
    }

    private List<String> getSpaceNumbers(List<Station> stations) {
        List<String> spaceNumbers = new ArrayList<>();
        for (Station s : stations) {
            spaceNumbers.add(Integer.toString(s.getSpaceNumber()));
        }
        return spaceNumbers;
    }

    private List<String> getAddresses(List<Station> stations) {
        List<String> addresses = new ArrayList<>();
        for (Station s : stations) {
            Location location = s.getLocation();
            String[] parts = location.getLocationAddress().split(",");
            addresses.add(parts[0]+parts[1]);
        }
        return addresses;
    }

    private List<String> getLat(List<Station> stations) {
        List<String> lat = new ArrayList<>();
        for (Station s : stations) {
            Location location = s.getLocation();
            lat.add(location.getLatitude());
        }
        return lat;
    }

    private List<String> getLon(List<Station> stations) {
        List<String> lon = new ArrayList<>();
        for (Station s : stations) {
            Location location = s.getLocation();
            lon.add(location.getLongitude());
        }
        return lon;
    }

}
