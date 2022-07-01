package com.perscholas.hms.utils;

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
        Users patient2=new Users("Emma Morgan","emma@gmail.com", "password","1999-05-20","Redmond,WA");
        patient2.setInsurance("Cigna");
        Users patient3=new Users("John Doe","john@gmail.com", "password","1978-07-10","Bellevue,WA");
        patient3.setInsurance("United Health");

        Users doctor1=new Users("Gregory House","ghouse@gmail.com", "password","1968-03-05","Seattle,WA");
        doctor1.setDepartment("Primary Physician");
        Users doctor2=new Users("Meredith Grey","mgrey@gmail.com", "password","1982-05-05","Seattle,WA");
        doctor2.setDepartment("Cardiology");
        Users doctor3=new Users("Richard Webber","rwebber@gmail.com", "password","1959-11-22","Seattle,WA");
        doctor3.setDepartment("Orthopedic");
        userRepository.save(patient1);
        userRepository.save(patient2);
        userRepository.save(patient3);
        userRepository.save(doctor1);
        userRepository.save(doctor2);
        userRepository.save(doctor3);


        //Populating Appointment data
        Appointment appointment1=new Appointment("ammugn@gmail.com","ghouse@gmail.com","fever","flu","2022-08-7","10:00",false);
        appointmentService.saveOrUpdate(appointment1);
        appointment1.addUsers(patient1);
        appointmentService.saveOrUpdate(appointment1);



    }
}

