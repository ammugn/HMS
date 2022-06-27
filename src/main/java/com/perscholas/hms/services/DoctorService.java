package com.perscholas.hms.services;

import com.perscholas.hms.data.AppointmentRepository;
import com.perscholas.hms.data.DoctorRepository;
import com.perscholas.hms.models.Appointment;
import com.perscholas.hms.models.Doctor;
import com.perscholas.hms.models.Patient;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Ammu Nair
 */

@Service @Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DoctorService {
    DoctorRepository doctorRepository;
    AppointmentRepository appointmentRepository;
    @Autowired
    public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }

    public void saveOrUpdate(Doctor doctor){
        log.info(doctor.toString());
        doctorRepository.save(doctor);

    }
    public Optional<Doctor> findById(long id)
    {
        return doctorRepository.findById(id);
    }

    public Optional<Doctor> findByName(String name){
        return doctorRepository.findByName(name);
    }

    public void delete(Doctor doctor) {
        doctorRepository.delete(doctor);
    }

    public void addAppointment(Long id, Appointment appointment) throws NoSuchElementException {

        Doctor doctor = doctorRepository.findById(id).orElseThrow();
        appointment = appointmentRepository.save(appointment);
        doctor.addAppointment(appointment);
        doctorRepository.save(doctor);
    }


}
