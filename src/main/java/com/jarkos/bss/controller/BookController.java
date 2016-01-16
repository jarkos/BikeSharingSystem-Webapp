package com.jarkos.bss.controller;

import com.jarkos.bss.persistance.entity.Station;
import com.jarkos.bss.persistance.entity.User;
import com.jarkos.bss.service.BookService;
import com.jarkos.bss.service.StationService;
import com.jarkos.bss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jarek on 2016-01-16.
 */


@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private StationService stationService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/stations/{id}/bookBike", method = RequestMethod.GET)
    public String bookBikeOnStation(@PathVariable int id) {
        Station station = stationService.findStationById(id);
        if(station.getBikes().size()>0){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            User currentUser = userService.findUserByUsername(name);
            if(currentUser.getAccountBalance()>5){
                bookService.bookBikeOnStation(station, currentUser);
                return "redirect:/user/map?booked=true";
            }
        }
        return "redirect:/user/map?booked=false";
    }

}
