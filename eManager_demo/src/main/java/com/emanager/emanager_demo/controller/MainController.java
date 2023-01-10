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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService service;

    @Autowired
    private DiensteServiceIn diensteService;

    @Autowired
    private NachrichtenServiceIn nachrichtenService;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/admin/login")
    public String viewAdminLoginPage() {
        return "admin_login";
    }

    @GetMapping("/admin/home")
    public String homepageAdmin(Model model) {
        List<Nachrichten> listNachrichten = nachrichtenService.getAllNachrichten();
        model.addAttribute("listNachrichten",listNachrichten);
        return "homepageAdmin";
    }


    @GetMapping("/user/login")
    public String viewUserLoginPage() {
        return "user_login";
    }

    @GetMapping("/user/home")
    public String user(Model model) {
        List<Nachrichten> listNachrichten = nachrichtenService.getAllNachrichten();
        model.addAttribute("listNachrichten",listNachrichten);
        return "homepageUser";
    }


    @GetMapping("user/kalender")
    public String kalender() {
        return "kalender";
    }



    @GetMapping("admin/userverwaltung")
    public String userverwaltung(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers",listUsers);
        return "userverwaltung";
    }


    @GetMapping("admin/showNewUserForm")
    public String showNewUserForm(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);

        return "newuser";
    }

    @PostMapping("/admin/saveUser")
    public String saveUser(User user) {
        // save User to database
        service.saveUser(user);

        return "redirect:/admin/userverwaltung";
    }


    @GetMapping("/admin/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

        // get employee from the service
        User user = service.getUserById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "updateuser";
    }


    @GetMapping("/admin/deleteuser/{id}")
    public String deleteuser(@PathVariable (value = "id") long id) {


        this.service.deleteUsereById(id);
        return "redirect:/admin/userverwaltung";
    }


//////////////////////

    @GetMapping("user/diensteEintragen")
    public String diensteEintragen(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String benutzername = authentication.getName();
        List<Dienste> listDienste = diensteService.findDiensteByMitarbeiterLike(benutzername);
        model.addAttribute("listDienste",listDienste);

        return "diensteEintragen";
    }


    @GetMapping("user/diensteerstellen")
    public String diensteerstellen(Model model) {
        // create model attribute to bind form data
        Dienste dienste = new Dienste();
        model.addAttribute("dienste", dienste);
        return "diensteerstellen";
    }

    @PostMapping("/user/saveDienste")
    public String saveDienste( Dienste dienste) {
        String username;
        LocalTime von = dienste.getZeitvon();
        LocalTime bis = dienste.getZeitbis();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        dienste.setMitarbeiter(username);
        Duration diff = Duration.between(von, bis);
        Long d = diff.toMinutes();
        int stunde = 60;
        double stunden = (double) d /  stunde;
        float stundenzahl = (float) stunden;
        dienste.setDauer(stundenzahl);
        diensteService.saveDienste(dienste);

        return "redirect:/user/diensteEintragen";
    }




    @GetMapping("/admin/nachrichtenerstellen")
    public String nachrichtenerstellen(Model model) {
        // create model attribute to bind form data
        Nachrichten nachrichten = new Nachrichten();
        model.addAttribute("nachrichten", nachrichten);
        return "nachrichtenerstellen";
    }


    @PostMapping("/admin/saveNachrichten")
    public String saveNachrichten( Nachrichten nachrichten) {
        nachrichtenService.saveNachrichten(nachrichten);
        return "redirect:/admin/home";
    }



    @GetMapping("/admin/deletenachricht/{id}")
    public String deletenachricht(@PathVariable (value = "id") long id) {
        this.nachrichtenService.deleteNachrichtById(id);
        return "redirect:/admin/home";
    }


}
