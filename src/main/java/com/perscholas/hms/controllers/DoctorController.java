package com.perscholas.hms.controllers;

import com.perscholas.hms.models.Users;
import com.perscholas.hms.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ammu Nair
 */
@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping
public class DoctorController {
    UserService doctorService;
  @Autowired
    public DoctorController(UserService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/meditech/saveorupdatedoctor")
    public String editProfile(@ModelAttribute("doctor")Users doctor){
        doctorService.saveOrUpdate(doctor);
        log.info("Doctor profile edited successfully");
        return "redirect:/doctors";
    }


}
