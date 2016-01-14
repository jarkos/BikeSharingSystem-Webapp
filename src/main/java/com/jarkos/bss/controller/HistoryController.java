package com.jarkos.bss.controller;

import com.jarkos.bss.service.HistoryService;
import com.jarkos.bss.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jarek on 2016-01-06.
 */

@Controller
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private StationService stationService;

    @RequestMapping("/admin/history/customers")
    public String getAllCustomersHistory(Model model) {
        model.addAttribute("historyList", historyService.findAllCustomersOperations());
        return "history-cl";
    }

    @RequestMapping("/admin/history/workers")
    public String getAllWorkersHistory(Model model) {
        model.addAttribute("historyList", historyService.findAllWorkersOperations());
        return "history-wr";
    }

    @RequestMapping("/admin/history/{id}/station")
    public String getStationHistory(@PathVariable int id, Model model) {
        model.addAttribute("historyList", historyService.findHistoryByStationId(id));
        model.addAttribute("station", stationService.findStationById(id));
        return "history-st";
    }

}
