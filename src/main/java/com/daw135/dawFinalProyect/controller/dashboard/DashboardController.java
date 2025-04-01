package com.daw135.dawFinalProyect.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @RequestMapping("/")
    public String getView() {
        return "pages/dashboard/dashboard";
    }
    
}
