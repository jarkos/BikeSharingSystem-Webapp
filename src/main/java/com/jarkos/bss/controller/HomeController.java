package com.jarkos.bss.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String firstPage() {
        log.debug("aHome page failed");
        return "home";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String getLoginPage() {
        log.debug("Login page failed");
        return "login";
    }

}
