package com.perscholas.hms;

import com.perscholas.hms.data.AppointmentRepository;
import com.perscholas.hms.data.UserRepository;
import com.perscholas.hms.models.Appointment;
import com.perscholas.hms.models.Users;
import com.perscholas.hms.services.AppointmentService;
import com.perscholas.hms.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Ammu Nair
 */
@Component
@Slf4j
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class ApplicationCommandLineRunner implements CommandLineRunner {
   UserService userService;
   UserRepository userRepository;
    AppointmentService appointmentService;
    AppointmentRepository appointmentRepository;

    @Autowired
    public ApplicationCommandLineRunner(UserService userService, UserRepository userRepository, AppointmentService appointmentService, AppointmentRepository appointmentRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
    }



    @PostConstruct
    public void postConstruct(){
        log.warn("Application CommandLine Runner populating data");
    }
    @Override
    public void run(String... args) throws Exception {
        //Populating Patient data
        Users patient1=new Users("Ammu Nair","ammugn@gmail.com", "password","1988-12-05","Mill Creek,WA");
        patient1.setInsurance("Aetna");
        patient1.addAppointment(new Appointment("ammugn@gmail.com","ghouse@gmail.com","fever","flu","2022-08-7","10:00",false));
        Users patient2=new Users("Emma Morgan","emma@gmail.com", "password","1999-05-20","Mill Creek,WA");
        patient1.setInsurance("Cigna");
        Users patient3=new Users("John Doe","john@gmail.com", "password","1988-12-05","Mill Creek,WA");
        patient1.setInsurance("Aetna");

        Users doctor1=new Users("Gregory House","ghouse@gmail.com", "password","1988-12-05","Mill Creek,WA");
        patient1.setDepartment("Primary Physician");
        userRepository.save(patient1);
        userRepository.save(patient2);
        userRepository.save(patient3);
        userRepository.save(doctor1);

        //Populating Appointment data
     //   Appointment appointment1=new Appointment("ammugn@gmail.com","ghouse@gmail.com","fever","flu","2022-08-7","10:00",false);
      //  appointmentService.saveOrUpdate(appointment1);
     //     appointment1.addUsers(patient1);
      //    appointmentRepository.save(appointment1);
        /*Appointment appointment1=new Appointment("Ammu Nair","Gregory House","fever","flu","2022-23-06","10:00",true);

        appointmentService.saveOrUpdate(appointment1);
        appointment1.setPatient(patient1);
        appointment1.setDoctor(doctor1);
        appointmentService.saveOrUpdate(appointment1);*/


      //  patientService.addAppointment(101L,appointmentService.findById(1));
     //   doctorService.addAppointment(201L,appointmentService.findById(1));

       // studentService.addCourse(JAFERID, courseService.findById(1));
    }
}

