package com.foxminded.university.controller;

import com.foxminded.university.entities.Subject;
import com.foxminded.university.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/subjects")
public class SubjectsController {
    @Autowired
    SubjectService subjectService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects);
        return "subjects/get-subjects";
    }

    @GetMapping("/{id}")
    public String subjectInfo(@PathVariable("id") long id, Model model) {
        Subject subject = subjectService.getSubjectById(id);
        model.addAttribute("subject", subject);
        return "subjects/subject-info";
    }
}
