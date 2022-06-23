package com.perscholas.hms;

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
        patientService.saveOrUpdate(new Patient("Ammu","Nair","ammugn@gmail.com", "1988-12-05","Aetna","Mill Creek,WA"));
        patientService.saveOrUpdate(new Patient("Emma","Morgan","emma@gmail.com", "1999-05-20","Cigna","Bellevue,WA"));
       patientService.saveOrUpdate(new Patient("John","Doe","john@gmail.com", "1999-08-25","UnitedHealth","Bellevue,WA"));

        doctorService.saveOrUpdate(new Doctor("Gregory","House","ghouse@gmail.com","Primary Physician"));
        doctorService.saveOrUpdate(new Doctor("Meredith","Grey","mgrey@gmail.com","Cardiologist"));
        doctorService.saveOrUpdate(new Doctor("Richard","Webber","rwebber@gmail.com","Primary Physician"));
    }
}

