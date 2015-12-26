package com.jarkos.bss.controller;

import com.jarkos.bss.persistance.entity.Bike;
import com.jarkos.bss.service.BikeService;
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

/**
 * Created by Jarek on 2015-12-01.
 */
@Controller
public class BikeController {
    private static final Logger log = LoggerFactory.getLogger(BikeController.class);

    @Autowired
    private BikeService bikeService;

    @RequestMapping("/admin/bikes")
    public String getBikesList(Model model) {
        log.debug("getBikeList");
        model.addAttribute(bikeService.findAllBikes());
        return "bikes";
    }

    @RequestMapping(value = "/admin/bikes/create", method = RequestMethod.GET)
    public String createNewBikeForm(Model model) {
        model.addAttribute("bike", new Bike());
        return "bikes-create";
    }

    @RequestMapping(value = "/admin/bikes/create", method = RequestMethod.POST)
    public String createNewBike(@Valid Bike bike, BindingResult bindingResult) {
        log.debug("createNewBike, serialNumber={}, model={}, errorCount={}", bike.getSerialNumber(), bike.getModel(), bindingResult.getErrorCount());

        if (bikeService.findBikeById(bike.getId()) != null) {
            bindingResult.rejectValue("bike", "error.bikeId.exist");
        }
        if (bindingResult.hasErrors()) {
            return "bikes-create";
        }
        bike.setEnabled(true);
        bikeService.saveBike(bike);

        return "redirect:/admin/bikes?created=true";
    }

    @RequestMapping(value = "/admin/bikes/{id}/delete", method = RequestMethod.GET)
    public String deleteBikeForm(@PathVariable int id) {
        if (id != 0) {
            Bike bikeToDelete = bikeService.findBikeById(id);
            if (bikeToDelete != null) {
                bikeService.deleteBike(bikeToDelete);
            } else {
                log.debug("deleteBike, id={}", id);
                System.out.printf("ERROR. Nie ma roweru o takim ID: " + id);
            }
            return "redirect:/admin/bikes?deleted=true";
        }
        return "redirect:/admin/bikes?deleted=false";
    }

    @RequestMapping(value = "/admin/bikes/{id}/edit", method = RequestMethod.GET)
    public String editBikeForm(@PathVariable int id, Model model) {
        Bike bike = bikeService.findBikeById(id);
        model.addAttribute("bike", bike);
        return "bikes-edit";
    }

    @RequestMapping(value = "/admin/bikes/{id}/edit", method = RequestMethod.POST)
    public String editBike(@Valid Bike bike, @PathVariable int id) {
        log.debug("editBike, id={}", id);
        bikeService.updateBike(bike);
        return "redirect:/admin/bikes?edited=true";
    }

    @RequestMapping(value = "/admin/test", method = RequestMethod.GET)
    public String test(Model model) {
        return "test";
    }
}
