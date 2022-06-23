package com.perscholas.hms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ammu Nair
 */
@Controller
public class HomeController {

    @GetMapping(value = {"/", "index"})
    public String homePage(){

        return "dashboard";
    }
}
