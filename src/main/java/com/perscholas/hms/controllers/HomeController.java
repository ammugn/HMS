package com.perscholas.hms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ammu Nair
 */
@Controller
public class HomeController {

    @GetMapping(value = {"/dashboard"})
    public String homePage(){
        return "dashboard";
    }
    @GetMapping(value = {"/", "index"})
    public String mediTechLoginPage(){
        return "index";
    }
    @GetMapping(value ={ "/medihealth/registerAdminOrDoctor"})
    public String mediTechEmployeeRegistration(Model model){
        List<String> listDepartments = Arrays.asList("Primary Physician", "Cardiology", "Orthopedic","Pediatric","Neurology");
        model.addAttribute("listDepartments", listDepartments);
        return "admin_doctor_registration";
    }
}
