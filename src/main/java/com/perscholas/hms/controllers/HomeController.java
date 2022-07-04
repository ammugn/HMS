package com.perscholas.hms.controllers;

import com.perscholas.hms.models.Users;
import com.perscholas.hms.services.AppointmentService;
import com.perscholas.hms.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ammu Nair
 */
@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping
public class HomeController {

    UserService userService;
    AppointmentService appointmentService;
   @Autowired
    public HomeController(UserService userService, AppointmentService appointmentService) {
        this.userService = userService;
        this.appointmentService = appointmentService;
   }

    @GetMapping(value = {"/dashboard"})
    public String homePage(Model model){
        model.addAttribute("appointments", appointmentService.findAll());
        log.info("Meditech dashboard displayed");
        return "dashboard";
    }
    @GetMapping(value = {"/", "/index"})
    public String mediTechLoginPage(){
        return "index";
    }
    @GetMapping(value ={ "/meditech/registerAdminOrDoctor"})
    public String mediTechEmployeeRegistration(Model model){
        List<String> listDepartments = Arrays.asList("Primary Physician", "Cardiology", "Orthopedic","Pediatric","Neurology");
        model.addAttribute("listDepartments", listDepartments);
        Users newUser = new Users();
        model.addAttribute("user", newUser);
        log.info("Admin/Doctor Registration page displayed");
        return "admin_doctor_registration";
    }
    @PostMapping("/meditech/saveUser")
    public String saveUser(@ModelAttribute("patient") Users user){
        userService.saveOrUpdate(user);
        log.info("Admin/Doctor user registered successfully");
        return "index";
    }
    @GetMapping("/accessdenied")
    public String accessDenied(){
       return "accessdenied";
    }
}
