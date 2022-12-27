package com.emanager.emanager_demo.controller;

import com.emanager.emanager_demo.model.Dienste;
import com.emanager.emanager_demo.model.Nachrichten;
import com.emanager.emanager_demo.model.User;
import com.emanager.emanager_demo.service.DiensteServiceIn;
import com.emanager.emanager_demo.service.NachrichtenServiceIn;
import com.emanager.emanager_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService service;

    @Autowired
    private DiensteServiceIn diensteService;

    @Autowired
    private NachrichtenServiceIn nachrichtenService;

    @GetMapping("/homepageUser")
    public String homepageUser() {
        return "homepageUser";
    }


    @GetMapping("/kalender")
    public String kalender() {
        return "kalender";
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
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        dienste.setMitarbeiter(username);
        diensteService.saveDienste(dienste);

        return "redirect:/diensteEintragen";
    }

    @GetMapping("/admin")
    public String homepageAdmin(Model model) {
        List<Nachrichten> listNachrichten = nachrichtenService.getAllNachrichten();
        model.addAttribute("listNachrichten",listNachrichten);
        return "homepageAdmin";
    }


    @GetMapping("/nachrichtenerstellen")
    public String nachrichtenerstellen(Model model) {
        // create model attribute to bind form data
        Nachrichten nachrichten = new Nachrichten();
        model.addAttribute("nachrichten", nachrichten);
        return "nachrichtenerstellen";
    }


    @PostMapping("/saveNachrichten")
    public String saveNachrichten( Nachrichten nachrichten) {
        nachrichtenService.saveNachrichten(nachrichten);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String user(Model model) {
        List<Nachrichten> listNachrichten = nachrichtenService.getAllNachrichten();
        model.addAttribute("listNachrichten",listNachrichten);
        return "homepageUser";
    }

    @GetMapping("/deletenachricht/{id}")
    public String deletenachricht(@PathVariable (value = "id") long id) {
        this.nachrichtenService.deleteNachrichtById(id);
        return "redirect:/admin";
    }


}
