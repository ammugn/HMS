package com.perscholas.hms;

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
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;

/**
 * @author Ammu Nair
 */
@Component
@Slf4j
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class ApplicationCommandLineRunner implements CommandLineRunner {
    PatientService patientService;
    DoctorService doctorService;
    AppointmentService appointmentService;
    @Autowired
    public ApplicationCommandLineRunner(PatientService patientService, DoctorService doctorService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    @PostConstruct
    public void postConstruct(){
        log.warn("Application CommandLine Runner populating data");
    }
    @Override
    public void run(String... args) throws Exception {
        //Populating Patient data
        Patient patient1=new Patient("Ammu Nair","ammugn@gmail.com", "1988-12-05","Aetna","Mill Creek,WA");
        Patient patient2=new Patient("Emma Morgan","emma@gmail.com", "1999-05-20","Cigna","Bellevue,WA");
        Patient patient3=new Patient("John Doe","john@gmail.com", "1999-08-25","UnitedHealth","Bellevue,WA");

        patientService.saveOrUpdate(patient1);
        patientService.saveOrUpdate(patient2);
        patientService.saveOrUpdate(patient3);
     //Populating Doctor data
        Doctor doctor1=new Doctor("Gregory House","ghouse@gmail.com","Primary Physician");
        Doctor doctor2=new Doctor("Meredith Grey","mgrey@gmail.com","Cardiologist");
        Doctor doctor3=new Doctor("Richard Webber","rwebber@gmail.com","Primary Physician");
        doctorService.saveOrUpdate(doctor1);
        doctorService.saveOrUpdate(doctor2);
        doctorService.saveOrUpdate(doctor3);

        //Populating Appointment data
        Appointment appointment1=new Appointment("Ammu Nair","Gregory House","fever","flu","2022-23-06","10:00",true);

        appointmentService.saveOrUpdate(appointment1);
        appointment1.setPatient(patient1);
        appointment1.setDoctor(doctor1);
        appointmentService.saveOrUpdate(appointment1);



    }
}

