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
import org.springframework.validation.BindingResult;
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
public class AdminController {

   UserService userService;
   AppointmentService appointmentService;

    @Autowired
    public AdminController(UserService userService, AppointmentService appointmentService) {
        this.userService = userService;
        this.appointmentService = appointmentService;
    }


    @GetMapping("/meditech/patients")
    public String getAllPatients(Model model) {
        log.info("Patient list displayed");
        model.addAttribute("patients", userService.findAllPatients());
        return "patients";
    }
    @GetMapping("/meditech/patients/addNewPatient")
    public String addNewPatient(Model model) {
        Users newPatient = new Users();
        model.addAttribute("patient", newPatient);
        List<String> listInsurance = Arrays.asList("United Health", "Aetna","Cigna");
        model.addAttribute("listInsurance", listInsurance);
        log.info("New Patient addition");
        return "add-patient";
    }
    @PostMapping("/meditech/patients/saveorupdatepatient")
    public String saveUpdatePatient(RedirectAttributes model, @ModelAttribute("patient") Users patient){
        userService.saveOrUpdate(patient);
        log.info("New Patient added successfully");
        return "redirect:/meditech/patients";

    }

    @GetMapping("/meditech/patients/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Users patient = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));

        model.addAttribute("patient", patient);
        List<String> listInsurance = Arrays.asList("United Health","Aetna","Cigna");
        model.addAttribute("listInsurance", listInsurance);
        log.info("Patient edit page");
        return "add-patient";
    }
    @PostMapping("/meditech/patients/update/{id}")
    public String updateUser(@PathVariable("id") long id,
                             BindingResult result, Model model) {
        Users patient = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
        if (result.hasErrors()) {
            patient.setId(id);
            return "add-patient";
        }

        userService.saveOrUpdate(patient);
        log.info("Patient details updated");
        return "redirect:/meditech/patients";
    }

    @GetMapping("/meditech/patients/delete/{id}")
    public String deletePatient(@PathVariable("id") long id, Model model) {
        Users patient = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
        userService.delete(patient);
        log.info("Patient deleted successfully");
        return "redirect:/meditech/patients";
    }


    /***** Admin controllers for Doctors *******/
    @GetMapping("/meditech/doctors")
    public String getAllDoctors(Model model) {
        log.info("Doctor list displayed");
        model.addAttribute("doctors", userService.findAllDoctors());
        return "doctors";
    }

    @GetMapping("/meditech/doctors/addNewDoctor")
    public String addNewDoctor(Model model) {
        Users doctor = new Users();
        model.addAttribute("doctor", doctor);
        List<String> listDepartments = Arrays.asList("Primary Physician","Cardiology", "Orthopedic","Pediatric","Neurology");
        model.addAttribute("listDepartments", listDepartments);
        log.info("New Doctor addition");
        return "add-doctor";
    }

    @PostMapping("/meditech/doctors/saveorupdatedoctor")
    public String saveUpdateDoctor(RedirectAttributes model, @ModelAttribute("doctor") Users doctor){
        userService.saveOrUpdate(doctor);
        log.info("New Doctor added successfully");
        return "redirect:/meditech/doctors";
    }

    @GetMapping("/meditech/doctors/edit/{id}")
    public String showUpdateDoctorForm(@PathVariable("id") long id, Model model) {
        Users doctor = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id:" + id));

        model.addAttribute("doctor", doctor);
        List<String> listDepartments = Arrays.asList("Primary Physician", "Cardiology", "Orthopedic","Pediatric","Neurology");
        model.addAttribute("listDepartments", listDepartments);
        log.info("in showUpdateDoctorForm ()");
        return "add-doctor";
    }

    @GetMapping("/meditech/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable("id") long id, Model model) {
        Users doctor = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id:" + id));
        userService.delete(doctor);
        return "redirect:/meditech/doctors";
    }


    /*****Admin controllers for Appointments*******/

    @GetMapping("/meditech/appointments")
    public String getAllAppointments(Model model) {
        log.info("Appointment list displayed");
        model.addAttribute("appointments", appointmentService.findAll());
         appointmentService.findAll();
        return "appointments";
    }
//    @GetMapping("/appointments/addNewAppointment")
//    public String addNewAppointment(Model model) {
//        Appointment appointment = new Appointment();
//        model.addAttribute("appointments", appointment);
//        List<Users> listDoctors = userService.findAllDoctors();
//        model.addAttribute("doctors", listDoctors);
//        log.info("New Appointment addition");
//        return "add-appointments";
//    }
    @GetMapping("/meditech/appointments/addNewAppointment/{id}")
    public String makeAppointment(@PathVariable("id") long id,Model model,RedirectAttributes redirect) {
        Appointment appointment = new Appointment();
        model.addAttribute("appointments", appointment);
        Users p=userService.findById(id).orElseThrow();
        model.addAttribute("patient", p);
        List<Users> listDoctors = userService.findAllDoctors();
        model.addAttribute("doctors", listDoctors);
        log.info("New Appointment addition from patients view");
        return "add-appointments";
    }

    @PostMapping("/meditech/appointments/saveorupdateappointment")
    public String saveUpdateAppointment(RedirectAttributes model, @ModelAttribute("appointments") Appointment appointment) throws NoSuchElementException {

        appointmentService.saveOrUpdate(appointment);
        String patientEmail=appointment.getPatientEmail();
        log.info(patientEmail);
        String doctorEmail=appointment.getDoctorEmail();
        log.info(doctorEmail);
        Users p1=userService.findByEmail(patientEmail);
        model.addAttribute("patient", p1);
        userService.addAppointment(p1.getId(),appointment);
        appointmentService.saveOrUpdate(appointment);
        log.info("New Appointment added successfully");
        log.info(String.valueOf(appointmentService.findById(appointment.getId())));
        return "redirect:/meditech/appointments";

    }

    //saveorupdateappointment/{id} not used
    @PostMapping("/meditech/appointments/saveorupdateappointment/{id}")
    public String saveAppointment(@PathVariable("id") long id,@ModelAttribute("patientId") Object flashAttribute, @ModelAttribute("appointments") Appointment appointment,RedirectAttributes model) throws NoSuchElementException{
        appointmentService.saveOrUpdate(appointment);
        String patientEmail=appointment.getPatientEmail();
        log.info(patientEmail);
        String doctorEmail=appointment.getDoctorEmail();
        log.info(doctorEmail);
        Users p1=userService.findByEmail(patientEmail);
        model.addAttribute("patient", p1);
        Users d1=userService.findByEmail(doctorEmail);
        userService.addAppointment(p1.getId(),appointment);
       // userService.addAppointment(d1.getId(),appointment);
        log.info("New Appointment added successfully");

        appointmentService.saveOrUpdate(appointment);
        return "redirect:/meditech/appointments";
    }


    @GetMapping("/meditech/appointments/edit/{id}")
    public String showUpdateAppointmentForm(@PathVariable("id") long id, Model model) {
        Appointment appointment = appointmentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appointment Id:" + id));
        model.addAttribute("appointments", appointment);
        List<Users> listDoctors = userService.findAllDoctors();
        model.addAttribute("doctors", listDoctors);
        String patientEmail=appointment.getPatientEmail();
        Users p=userService.findByEmail(patientEmail);
        // patientService.addAppointment(id,appointment);
        model.addAttribute("patient", p);
        return "add-appointments";
    }
/*    @PostMapping("/appointments/update/{id}")
    public String updateAppointment(@PathVariable("id") long id,
                               BindingResult result, Model model) {
        Appointment appointment = appointmentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appointment id:" + id));
        if (result.hasErrors()) {
            appointment.setId(id);
            return "add-appointments";
        }

        appointmentService.saveOrUpdate(appointment);
        return "redirect:/appointments";
    }*/

    @GetMapping("/meditech/appointments/delete/{id}")
    public String deleteAppointments(@PathVariable("id") long id, Model model) {
        Appointment appointment = appointmentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appoinment Id:" + id));
      /*  String patientEmail = appointment.getPatientEmail();
        String doctorEmail = appointment.getDoctorEmail();
        Users p1 = userService.findByEmail(patientEmail);
        Users d1 = userService.findByEmail(doctorEmail);
        userService.removeAppointment(p1.getId(), appointment);
        userService.removeAppointment(d1.getId(), appointment);*/
         appointmentService.delete(appointment);

        return "redirect:/meditech/appointments";



    }
}
