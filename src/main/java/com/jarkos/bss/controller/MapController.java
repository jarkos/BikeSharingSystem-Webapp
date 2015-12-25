package com.jarkos.bss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jarek on 2015-12-25.
 */
@Controller
public class MapController {

    @RequestMapping("/admin/map")
    public String getMap() {
        return "map";
    }

}
