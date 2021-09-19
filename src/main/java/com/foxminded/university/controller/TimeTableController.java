package com.foxminded.university.controller;

import com.foxminded.university.entities.Lecture;
import com.foxminded.university.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/timetable")
public class TimeTableController {
    @Autowired
    private LectureService lectureService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Lecture> lectures = lectureService.getAllLectures();
        model.addAttribute("lectures", lectures);
        return "timetable/get-timetable";
    }
}
