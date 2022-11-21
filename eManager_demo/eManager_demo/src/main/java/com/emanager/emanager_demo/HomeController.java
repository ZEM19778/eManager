package com.emanager.emanager_demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("/")
    public String start(){
        return "start";
    }

    @GetMapping("/user")
    public String home() {
        return "homepageUser";
    }

    @PostMapping(value = "/user")
    public String Dienste() {
        return "redirect:/diensteEintragen";
    }


    @GetMapping("/admin")
    public String admin() {
        return "AdminPage";
    }
}
