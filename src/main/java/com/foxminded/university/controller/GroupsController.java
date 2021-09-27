package com.foxminded.university.controller;

import com.foxminded.university.entities.Group;
import com.foxminded.university.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupsController {
    @Autowired
    GroupService groupService;

    @GetMapping("/list")
    public String home(Model model) {
        List<Group> list = groupService.getAllGroups();
        model.addAttribute("groups", list);
        return "groups/get-groups";
    }

    @GetMapping("/{id}")
    public String groupInfo(@PathVariable("id") long id, Model model) {
        Group group = groupService.getGroupById(id);
        model.addAttribute("group", group);
        return "groups/group-info";
    }
}
