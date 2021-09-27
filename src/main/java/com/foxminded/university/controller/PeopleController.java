package com.foxminded.university.controller;

import com.foxminded.university.entities.Student;
import com.foxminded.university.entities.Teacher;
import com.foxminded.university.services.StudentService;
import com.foxminded.university.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    public String list() {
        return "people/home";
    }

    @GetMapping("/students")
    public String students(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "people/get-students";
    }

    @GetMapping("/teachers")
    public String teachers(Model model) {
        List<Teacher> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "people/get-teachers";
    }

    @GetMapping("/teacher/{id}")
    public String teacherInfo(@PathVariable("id") long id, Model model) {
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "people/teacher-info";
    }

    @GetMapping("/student/{id}")
    public String studentInfo(@PathVariable("id") long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "people/student-info";
    }
}
