package com.perscholas.hms.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ammu Nair
 */
@Controller
@Slf4j
public class HomeController {

    @GetMapping(value = {"/", "index"})
    public String homePage(){

        return "dashboard";
    }
    @GetMapping(value = {"login"})
    public String loginPage(){
        log.info("In security");
        log.info("Test");
        return "index";
    }

}
