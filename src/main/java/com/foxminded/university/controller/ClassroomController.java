package com.foxminded.university.controller;

import com.foxminded.university.entities.Classroom;
import com.foxminded.university.services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/classroom")
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @GetMapping("/{id}")
    public String classroomInfo(@PathVariable("id") long id, Model model) {
        Classroom classroom = classroomService.getClassroomById(id);
        model.addAttribute("classroom", classroom);
        return "classrooms/classroom-info";
    }
}
