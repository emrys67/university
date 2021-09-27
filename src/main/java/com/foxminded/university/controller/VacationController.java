package com.foxminded.university.controller;

import com.foxminded.university.entities.Vacation;
import com.foxminded.university.services.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vacation")
public class VacationController {
    @Autowired
    private VacationService vacationService;

    @GetMapping("/{id}")
    public String vacationInfo(@PathVariable("id") long id, Model model) {
        Vacation vacation = vacationService.getVacationById(id);
        model.addAttribute("vacation", vacation);
        return "vacations/vacation-info";
    }
}
