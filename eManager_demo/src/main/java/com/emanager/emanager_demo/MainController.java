package com.emanager.emanager_demo;
import org.springframework.beans.factory.annotation.Autowired;
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

}
