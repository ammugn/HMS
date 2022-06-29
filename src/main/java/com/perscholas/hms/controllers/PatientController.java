package com.perscholas.hms.controllers;

import com.perscholas.hms.models.Users;
import com.perscholas.hms.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ammu Nair
 */
@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping
public class PatientController {

    UserService patientService;
    @Autowired
    public PatientController(UserService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/medihealth")
    public String getMediHealthHomepage(Model model) {
        log.info("MediHealth home page");

        return "medihealth";
    }
    @GetMapping("/medihealth/login")
    public String getPatientLogin(Model model) {
        log.info("MediHealth patient login page");

        return "patient_login";
    }
    @GetMapping("/medihealth/registerPatient")
    public String registerPatient(Model model) {
        Users newPatient = new Users();
        model.addAttribute("patient", newPatient);
        List<String> listInsurance = Arrays.asList("United Health", "Aetna","Cigna");
        model.addAttribute("listInsurance", listInsurance);
        log.info("New Patient registration page");
        return "patient_registration";
    }
    @PostMapping("/medihealth/savePatient")
    public String savePatient(RedirectAttributes model, @ModelAttribute("patient") Users patient){
        patientService.saveOrUpdate(patient);
        log.info("New Patient registered successfully");
        return "redirect:/medihealth/login";

    }
   /* @GetMapping("/patients/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Patient patient = patientService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));

        model.addAttribute("patient", patient);
        List<String> listInsurance = Arrays.asList("Optum", "UnitedHealthCare", "Aetna","Cigna");
        model.addAttribute("listInsurance", listInsurance);
        return "add-patient";
    }
    @PostMapping("/patients/update/{id}")
    public String updateUser(@PathVariable("id") long id,
                             BindingResult result, Model model) {
        Patient patient = patientService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
        if (result.hasErrors()) {
            patient.setId(id);
            return "add-patient";
        }

        patientService.saveOrUpdate(patient);
        return "redirect:/patients";
    }

    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable("id") long id, Model model) {
        Patient patient = patientService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
        patientService.delete(patient);
        return "redirect:/patients";
    }*/
}
