package com.perscholas.hms.services;

import com.perscholas.hms.data.AppointmentRepository;
import com.perscholas.hms.models.Appointment;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ammu Nair
 */

@Service @Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppointmentService {
    AppointmentRepository appointmentRepository;
    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }


    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    public void saveOrUpdate(Appointment a){
        log.info(a.toString());
        appointmentRepository.save(a);

    }
    public Optional<Appointment> findById(long id)
    {
        return appointmentRepository.findById(id);

    }

    public void delete(Appointment a) {
        appointmentRepository.delete(a);

    }

    public String findPatientNameForAppointments(String email) {
        return appointmentRepository.findPatientNameForAppointments(email);
    }



    public String findDoctorNameForAppointments(String email){
        return appointmentRepository.findDoctorNameForAppointments(email);

}

/*    public List<Appointment> getPatientAppointments(String name){

        return appointmentRepository.findPatientAppointments(name);
    }*/
    /*public List<Appointment> getDoctorAppointments(String name){

        return appointmentRepository.findDoctorAppointments(name);
    }*/


}
