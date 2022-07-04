package com.perscholas.hms.services;
import com.perscholas.hms.data.AppointmentRepository;
import com.perscholas.hms.data.AuthGroupRepository;
import com.perscholas.hms.data.UserRepository;
import com.perscholas.hms.models.Appointment;
import com.perscholas.hms.models.AuthGroup;
import com.perscholas.hms.models.Users;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    AuthGroupRepository authGroupRepository;

    @Autowired

    public UserService(UserRepository patientRepository, AppointmentRepository appointmentRepository, AuthGroupRepository authGroupRepository) {
        this.userRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.authGroupRepository = authGroupRepository;
    }

    public List<Users> findAllUsers(){
        return userRepository.findAll();
    }
    public List<Users> findAllPatients(){
        return userRepository.findAllPatients();
    }
    public List<Users> findAllDoctors(){
        return userRepository.findAllDoctors();
    }

    public void saveOrUpdate(Users u){
        AuthGroup authGroup=new AuthGroup();
        log.info(u.toString());
        userRepository.save(u);
        authGroup.setAuthEmail(u.getEmail());
        if(u.getIsAdmin()){
            authGroup.setRole("ROLE_ADMIN");
            log.info("Admin role assigned");
        } else if (u.getInsurance()==null) {
            authGroup.setRole("ROLE_DOCTOR");
            log.info("Doctor role assigned");
        }
        else if (u.getDepartment()==null) {
            authGroup.setRole("ROLE_PATIENT");
            log.info("Patient role assigned");
        }
        authGroupRepository.save(authGroup);


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
       Users currentUser= userRepository.findById(users.getId()).get();
       currentUser.setAppointments(new HashSet<>());
       userRepository.save(currentUser);
       userRepository.deleteById(currentUser.getId());

    }
    public void addAppointment(Long id, Appointment appointment) throws NoSuchElementException {

        Users user = userRepository.findById(id).orElseThrow();
        appointment = appointmentRepository.save(appointment);
      //  user.addAppointment(appointment);
        userRepository.save(user);
    }
    public void removeAppointment(Long id, Appointment appointment) throws NoSuchElementException {

        Users user = userRepository.findById(id).orElseThrow();
    //    user.removeAppointment(appointment);
        //  appointmentRepository.delete(appointment);
        userRepository.save(user);
    }
    public Users findByEmail(String email){

        return userRepository.findByEmail(email);
    }
}
