package com.jarkos.bss.controller;

import com.jarkos.bss.persistance.dto.BorrowOperationDto;
import com.jarkos.bss.persistance.exceptions.CannotBorrowMoreBikesException;
import com.jarkos.bss.persistance.exceptions.NotRequiredAccountBalanceException;
import com.jarkos.bss.service.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Jarek on 2015-12-28.
 */
@RestController
@EnableWebMvc
public class ExternalApiController {

    private static final Logger log = LoggerFactory.getLogger(ExternalApiController.class);

    @Autowired
    private RentService rentService;

    @RequestMapping(value = "/externalApi/rentService", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void borrowBike(@RequestBody BorrowOperationDto borrowBike) {
        try {
            rentService.borrowBike(borrowBike);
        } catch (NotRequiredAccountBalanceException e) {
            e.printStackTrace();
        } catch (CannotBorrowMoreBikesException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/externalApi/returnService", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void returnBike(@RequestBody BorrowOperationDto borrowBike) {
        rentService.returnBike(borrowBike);
    }
}
