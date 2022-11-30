package com.emanager.emanager_demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class diensteController {
    @GetMapping("diensteEintragen")
    public String diensteEintragen() {
        return "diensteEintragen";
    }
}
