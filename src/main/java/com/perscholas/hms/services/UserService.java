package com.perscholas.hms.services;
import com.perscholas.hms.data.AppointmentRepository;
import com.perscholas.hms.data.UserRepository;
import com.perscholas.hms.models.Appointment;
import com.perscholas.hms.models.Users;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
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
public class UserService {
    UserRepository userRepository;
    AppointmentRepository appointmentRepository;

    @Autowired
    public UserService(UserRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.userRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Users> findAll(){
        return userRepository.findAll();
    }
    public List<Users> findAllPatients(){
        return userRepository.findAllPatients();
    }
    public List<Users> findAllDoctors(){
        return userRepository.findAllDoctors();
    }

    public void saveOrUpdate(Users u){
        log.info(u.toString());
        userRepository.save(u);

    }
    public Optional<Users> findById(long id)
    {
        return userRepository.findById(id);
    }

    public Optional<Users> findByName(String name)
    {

        return userRepository.findByName(name);
    }

    public void delete(Users users) {
        userRepository.delete(users);
    }
    public void addAppointment(Long id, Appointment appointment) throws NoSuchElementException {

        Users user = userRepository.findById(id).orElseThrow();
        appointment = appointmentRepository.save(appointment);
        user.addAppointment(appointment);
        userRepository.save(user);
    }
    public void removeAppointment(Long id, Appointment appointment) throws NoSuchElementException {

        Users user = userRepository.findById(id).orElseThrow();
        user.removeAppointment(appointment);
        //  appointmentRepository.delete(appointment);
        userRepository.save(user);
    }
    public Users findByEmail(String email){

        return userRepository.findByEmail(email);
    }
}
