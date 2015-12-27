package com.jarkos.bss.controller;

import com.jarkos.bss.persistance.entity.Bike;
import com.jarkos.bss.service.BikeService;
import com.jarkos.bss.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jarek on 2015-12-27.
 */
@Controller
public class NoteController {
    private static final Logger log = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    @Autowired
    private BikeService bikeService;

    @RequestMapping("/admin/bikes/{id}/notes")
    public String getNotesListForBike(@PathVariable int id, Model model) {
        log.debug("getNotesListForBike");
        Bike b = bikeService.findBikeById(id);
        model.addAttribute("notes", b.getNotes());
        model.addAttribute("bike", b);
        return "bike-notes";
    }

}
