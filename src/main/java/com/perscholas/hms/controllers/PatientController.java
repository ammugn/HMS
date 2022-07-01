package com.perscholas.hms.controllers;

import com.perscholas.hms.models.Appointment;
import com.perscholas.hms.models.Users;
import com.perscholas.hms.services.AppointmentService;
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
import java.util.NoSuchElementException;

/**
 * @author Ammu Nair
 */
@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping
public class PatientController {

    UserService patientService;
    AppointmentService appointmentService;
    @Autowired
    public PatientController(UserService patientService,  AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService=appointmentService;
    }
    @GetMapping("/medihealth")
    public String getMediHealthHomepage() {
        log.info("MediHealth home page");
        return "medihealth";
    }
    @GetMapping("/medihealth/patientDashboard/logout")
    public String doLogout() {
        log.info("logging out");
        return "medihealth";
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
    @GetMapping("/medihealth/login")
    public String getPatientLogin(Model model) {
        log.info("MediHealth patient login page");
        Users newPatient = new Users();
        model.addAttribute("patient", newPatient);
        return "patient_login";
    }

    @PostMapping("/medihealth/patientDashboard")  //"/medihealth/patientDashboard/{id}"
    public String showPatientDashboard(Model model,@ModelAttribute("patient") Users patient){
        Users p=patientService.findByEmail(patient.getEmail());
        log.info(p.getEmail()+" logged in with id "+p.getId());
        model.addAttribute("patient", p);//logged patient
        return "patient_dashboard";
    }

    @GetMapping("/medihealth/patientDashboard")  //to redirect to this page
    public String displayPatientDashboard(RedirectAttributes model,@ModelAttribute("patient") Users patient){
        Users p=patientService.findByEmail(patient.getEmail());
        log.info(p.getEmail()+" logged in with id "+p.getId());
        model.addAttribute("patient", p);//logged patient
        return "patient_dashboard";
    }

    @GetMapping("/medihealth/bookAppointment/{id}")
    public String makeNewAppointment(Model model,@PathVariable("id") long id){
        Appointment appointment = new Appointment();
        model.addAttribute("appointments", appointment);
        Users p=patientService.findById(id).orElseThrow();
        model.addAttribute("patient", p);
        List<Users> listDoctors = patientService.findAllDoctors();
        model.addAttribute("doctors", listDoctors);
        log.info("New Appointment addition from patient dashboard");
        return "patient_newappointment";


    }
    @PostMapping("/medihealth/saveorupdateappointment")
    public String saveUpdateAppointment(RedirectAttributes model, @ModelAttribute("appointments") Appointment appointment) throws NoSuchElementException {


        appointmentService.saveOrUpdate(appointment);
        String patientEmail=appointment.getPatientEmail();
        log.info(patientEmail);
        String doctorEmail=appointment.getDoctorEmail();
        log.info(doctorEmail);
        Users p1=patientService.findByEmail(patientEmail);
        model.addAttribute("patient", p1);
        patientService.addAppointment(p1.getId(),appointment);
        log.info("New Appointment added successfully for");
        log.info(String.valueOf(appointmentService.findById(appointment.getId()).get().getPatientEmail()));

        return "redirect:/medihealth/patientDashboard";

    }

    @GetMapping("medihealth/viewAppointments/{id}")
    public String viewAppointments(Model model,@PathVariable("id") long id){
        Users patient=patientService.findById(id).orElseThrow();
        log.info("Fetching Appointment list for "+patient.getEmail());
        List<Appointment> appointments=appointmentService.getPatientAppointments(patient.getEmail());
        model.addAttribute("appointments", appointments);
        model.addAttribute("patient", patient);
        log.info("Appointment list "+appointments.toString());
        log.info("Appointment list for "+patient.getEmail()+" displayed");
        return "patient_appointments";

    }
}
