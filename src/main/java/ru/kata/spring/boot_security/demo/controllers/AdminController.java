package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entenies.Role;
import ru.kata.spring.boot_security.demo.entenies.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public String listUser(Model model, Principal principal) {
        Optional<User> user = userService.findByUserName(principal.getName());
        model.addAttribute("admin", user.get());
        List<User> allUser = userService.allUser();
        model.addAttribute("allUser", allUser);
        model.addAttribute("newUser", new User());

        model.addAttribute("allRoles", roleService.findAllRoles());
        return "admin";
    }
    @PostMapping
    public String create(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/admin/list";
    }

    @RequestMapping("/getOne")
    @ResponseBody
    private User getOne(Long id) {
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(User user) {
        if (user.getRoles().isEmpty()) {
            List<Role> roles = userService.findUserById(user.getId()).getRoles();
            user.setRoles(roles);
        }
        userService.update(user);
        return "redirect:/admin/list";
    }


    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(User user) {
        userService.deleteUser(user.getId());
        return "redirect:/admin/list";
    }

}

