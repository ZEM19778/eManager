package com.emanager.emanager_demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
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
        return "AdminPage";
    }
}
