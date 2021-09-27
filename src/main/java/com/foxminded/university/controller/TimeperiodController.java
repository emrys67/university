package com.foxminded.university.controller;

import com.foxminded.university.entities.TimePeriod;
import com.foxminded.university.services.TimePeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/timeperiod")
public class TimeperiodController {
    @Autowired
    private TimePeriodService timePeriodService;

    @GetMapping("/{id}")
    public String timeperiodInfo(@PathVariable("id") long id, Model model) {
        TimePeriod timeperiod = timePeriodService.getTimePeriodById(id);
        model.addAttribute("timeperiod", timeperiod);
        return "timeperiods/timeperiod-info";
    }
}
