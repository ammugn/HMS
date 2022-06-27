package com.perscholas.hms.controllers;

import com.perscholas.hms.models.Appointment;
import com.perscholas.hms.models.Doctor;
import com.perscholas.hms.models.Patient;
import com.perscholas.hms.services.AppointmentService;
import com.perscholas.hms.services.DoctorService;
import com.perscholas.hms.services.PatientService;
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
import java.util.Optional;

/**
 * @author Ammu Nair
 */
@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping
public class AdminController {

    PatientService patientService;
    DoctorService doctorService;
    AppointmentService appointmentService;
    @Autowired
    public AdminController(PatientService patientService, DoctorService doctorService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
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
    }


    /***** Admin controllers for Doctors *******/
    @GetMapping("/doctors")
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
        log.info("in showUpdateDoctorForm ()");
        return "add-doctor";
    }
/*    @PostMapping("/doctors/update/{id}")
    public String updateDoctor(@PathVariable("id") long id,
                               BindingResult result, Model model) {
        Doctor doctor = doctorService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id:" + id));
        if (result.hasErrors()) {
            doctor.setId(id);
            return "add-doctor";
        }

        doctorService.saveOrUpdate(doctor);
        log.info("in updateDoctor()");
        return "redirect:/doctors";
    }*/

    @GetMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable("id") long id, Model model) {
        Doctor doctor = doctorService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id:" + id));
        doctorService.delete(doctor);
        return "redirect:/doctors";
    }


    /*****Admin controllers for Appointments*******/

    @GetMapping("/appointments")
    public String getAllAppointments(Model model) {
        log.info("Appointment list displayed");
        model.addAttribute("appointments", appointmentService.findAll());
        return "appointments";
    }
    @GetMapping("/appointments/addNewAppointment")
    public String addNewAppointment(Model model) {
        Appointment appointment = new Appointment();
        model.addAttribute("appointments", appointment);
        List<Doctor> listDoctors = doctorService.findAll();
        model.addAttribute("doctors", listDoctors);
        log.info("New Appointment addition");
        return "add-appointments";
    }
    @GetMapping("/appointments/addNewAppointment/{id}")
    public String makeAppointment(@PathVariable("id") long id,Model model,RedirectAttributes redirect) {
        Appointment appointment = new Appointment();
        model.addAttribute("appointments", appointment);
        Patient p=patientService.findById(id).orElseThrow();
       // patientService.addAppointment(id,appointment);
        model.addAttribute("patient", p);
        List<Doctor> listDoctors = doctorService.findAll();
        model.addAttribute("doctors", listDoctors);
        redirect.addFlashAttribute("patientId",id);
        log.info("New Appointment addition from patients view");
        return "add-appointments";
    }

    @PostMapping("/appointments/saveorupdateappointment")
    public String saveUpdateAppointment(RedirectAttributes model, @ModelAttribute("appointments") Appointment appointment) throws NoSuchElementException {

        /*appointmentService.saveOrUpdate(appointment);
        String patientName=appointment.getPatientName();
        log.info(patientName);
        String doctorName=appointment.getDoctorName();
        log.info(patientName);
        Optional<Patient> p1= patientService.findByName(patientName);
                Optional<Doctor> d1=doctorService.findByName(doctorName);
        patientService.addAppointment(p1.orElseThrow().getId(),appointment);
        doctorService.addAppointment(d1.orElseThrow().getId(),appointment);

        log.info("New Appointment added successfully");
        return "redirect:/appointments";*/
        appointmentService.saveOrUpdate(appointment);
        String patientName = appointment.getPatientName();
        String doctorName = appointment.getDoctorName();
        log.info(patientName);
        Optional<Patient> p1 = patientService.findByName(patientName);
        Optional<Doctor> d1 = doctorService.findByName(doctorName);
        log.info(p1.get().getId()+" "+p1.get().getName());
        appointment.setDoctor(d1.get());
        appointment.setPatient(p1.get());
        log.info(appointment.toString());
        appointmentService.saveOrUpdate(appointment);

      //  patientService.addAppointment(p1.get().getId(), appointment);

      //  doctorService.addAppointment(d1.get().getId(), appointment);


        log.info("New Appointment added successfully");
        return "redirect:/appointments";

    }
    @PostMapping("/appointments/saveorupdateappointment/{id}")
    public String saveAppointment(@ModelAttribute("patientId") Object flashAttribute, @ModelAttribute("appointments") Appointment appointment) throws NoSuchElementException{
        String patientName = appointment.getPatientName();
        String doctorName = appointment.getDoctorName();
        Optional<Patient> p1 = patientService.findByName(patientName);
        Optional<Doctor> d1 = doctorService.findByName(doctorName);
        appointment.setDoctor(d1.orElseThrow());
        appointment.setPatient(p1.orElseThrow());
        appointmentService.saveOrUpdate(appointment);
        patientService.addAppointment(p1.orElseThrow().getId(), appointment);
        doctorService.addAppointment(d1.orElseThrow().getId(), appointment);

        log.info("New Appointment added successfully from patients view");
        return "redirect:/appointments";
    }


    @GetMapping("/appointments/edit/{id}")
    public String showUpdateAppointmentForm(@PathVariable("id") long id, Model model) {
        Appointment appointment = appointmentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appointment Id:" + id));

        model.addAttribute("appointments", appointment);
        List<Doctor> listDoctors = doctorService.findAll();
        model.addAttribute("doctors", listDoctors);
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

    @GetMapping("/appointments/delete/{id}")
    public String deleteAppointments(@PathVariable("id") long id, Model model) {
        Appointment appointment = appointmentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appoinment Id:" + id));
         appointmentService.delete(appointment);

        return "redirect:/appointments";
    }
}
