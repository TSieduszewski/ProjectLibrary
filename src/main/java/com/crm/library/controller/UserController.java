package com.crm.library.controller;

import com.crm.library.entity.User;
import com.crm.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/showAllUsers")
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.findAll();

        model.addAttribute("allUsers", allUsers);

        return "users/showAll";
    }

    @GetMapping("/addUser")
    public String addUserForm(Model model) {
        User user = new User();

        model.addAttribute("user", user);

        return "users/userForm";
    }

    @GetMapping("/editUser")
    public String editBookForm(@RequestParam("id") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);

        return "users/userForm";
    }

    @PostMapping("/saveUser")
    public String save(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users/showAllUsers";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/users/showAllUsers";
    }

    @GetMapping("/changeStatus")
    public String changeStatus(@RequestParam("id") int id) {
        User user = userService.findById(id);
        user.setActive(!user.isActive());
        userService.save(user);
        return "redirect:/users/showAllUsers";
    }

}
