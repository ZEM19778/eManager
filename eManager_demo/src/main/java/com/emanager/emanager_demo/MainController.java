package com.emanager.emanager_demo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired private UserService service;

    @GetMapping("/")
    public String start(){
        return "homepageUser";
    }

    @GetMapping("/user")
    public String user() {
        return "homepageUser";
    }

    @GetMapping("/homepageUser")
    public String homepageUser() {
        return "homepageUser";
    }

    @GetMapping("/diensteEintragen")
    public String dienste() {
        return "diensteEintragen";
    }
    @GetMapping("/kalender")
    public String kalender() {
        return "kalender";
    }



    @GetMapping("/admin")
    public String admin() {

        return "homepageAdmin";
    }

    @GetMapping("/userverwaltung")
    public String userverwaltung(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers",listUsers);

        return "userverwaltung";
    }


    @GetMapping("/showNewUserForm")
    public String showNewUserForm(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);

        return "newuser";
    }

    @PostMapping("/saveUser")
    public String saveUser(User user) {
        // save User to database
        service.saveUser(user);

        return "redirect:/userverwaltung";
    }

    //@GetMapping("/useredit/{id}")
    //public String useredit(@PathVariable("id") Long id, Model model){
    //    try {
    //        User user = service.get(id);
    //         model.addAttribute("user",user);
    //       model.addAttribute("pageTitle","Edit User (ID: "+id +")");
    //       return "newuser";

    //   } catch (UserNotFoundException e) {
    //       return "redirect:/userverwaltung";
    //    }
    //}

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

        // get employee from the service
        User user = service.getUserById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "updateuser";
    }


    @GetMapping("/deleteuser/{id}")
    public String deleteuser(@PathVariable (value = "id") long id) {


        this.service.deleteUsereById(id);
        return "redirect:/userverwaltung";
    }



}
