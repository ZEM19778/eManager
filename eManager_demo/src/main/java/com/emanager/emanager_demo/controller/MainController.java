package com.emanager.emanager_demo.controller;

import com.emanager.emanager_demo.model.Dienste;
import com.emanager.emanager_demo.model.User;
import com.emanager.emanager_demo.service.DiensteServiceIn;
import com.emanager.emanager_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService service;

    @Autowired
    private DiensteServiceIn diensteService;

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


//////////////////////
    
    @GetMapping("/diensteEintragen")
    public String diensteEintragen(Model model) {
        List<Dienste> listDienste = diensteService.getAllDienste();
        model.addAttribute("listDienste",listDienste);

        return "diensteEintragen";
    }


    @GetMapping("/diensteerstellen")
    public String diensteerstellen(Model model) {
        // create model attribute to bind form data
        Dienste dienste = new Dienste();
        model.addAttribute("dienste", dienste);
        return "diensteerstellen";
    }

    @PostMapping("/saveDienste")
    public String saveDienste( Dienste dienste) {
        diensteService.saveDienste(dienste);

        return "redirect:/diensteEintragen";
    }

    @GetMapping("/homepageAdmin")
    public String homepageAdmin() {
        return "homepageAdmin";
    }

}
