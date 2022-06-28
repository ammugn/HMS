package com.perscholas.hms.controllers;

import com.perscholas.hms.services.DoctorService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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
DoctorService doctorService;
  @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

   /* @GetMapping("/doctors")
    public String getAllDoctors(Model model) {
        log.info("Doctor list displayed");
        model.addAttribute("doctors", doctorService.findAll());
        return "doctors";
    }
    @GetMapping("/doctors/addNewDoctor")
    public String addNewDoctor(Model model) {
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        List<String> listDepartments = Arrays.asList("Primary Physician", "Cardiology", "Orthopedic","Pediatric");
        model.addAttribute("listDepartments", listDepartments);
        log.info("New Doctor addition");
        return "add-doctor";
    }
    @PostMapping("/doctors/saveorupdatedoctor")
    public String saveUpdateDoctor(RedirectAttributes model, @ModelAttribute("doctor") Doctor doctor){
        doctorService.saveOrUpdate(doctor);
        log.info("New Doctor added successfully");
        return "redirect:/doctors";
    }

    @GetMapping("/doctors/edit/{id}")
    public String showUpdateDoctorForm(@PathVariable("id") long id, Model model) {
        Doctor doctor = doctorService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id:" + id));

        model.addAttribute("doctor", doctor);
        List<String> listDepartments = Arrays.asList("Primary Physician", "Cardiology", "Orthopedic","Pediatric");
        model.addAttribute("listDepartments", listDepartments);
        return "add-doctor";
    }
    @PostMapping("/doctors/update/{id}")
    public String updateDoctor(@PathVariable("id") long id,
                             BindingResult result, Model model) {
        Doctor doctor = doctorService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id:" + id));
        if (result.hasErrors()) {
            doctor.setId(id);
            return "add-doctor";
        }

        doctorService.saveOrUpdate(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable("id") long id, Model model) {
        Doctor doctor = doctorService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id:" + id));
        doctorService.delete(doctor);
        return "redirect:/doctors";
    }*/
}
