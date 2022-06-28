package com.perscholas.hms.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ammu Nair
 */
@Controller @Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping
public class PatientController {

  /*  PatientService patientService;
    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/patients")
    public String getAllPatients(Model model) {
        log.info("Patient list displayed");
        model.addAttribute("patients", patientService.findAll());
        return "patients";
    }
    @GetMapping("/patients/addNewPatient")
    public String addNewPatient(Model model) {
        Patient newPatient = new Patient();
        model.addAttribute("patient", newPatient);
        List<String> listInsurance = Arrays.asList("Optum", "UnitedHealthCare", "Aetna","Cigna");
        model.addAttribute("listInsurance", listInsurance);
        log.info("New Patient addition");
        return "add-patient";
    }
    @PostMapping("/patients/saveorupdatepatient")
    public String saveUpdatePatient(RedirectAttributes model, @ModelAttribute("patient") Patient patient){
        patientService.saveOrUpdate(patient);
        log.info("New Patient added successfully");
        return "redirect:/patients";

    }
    @GetMapping("/patients/edit/{id}")
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
