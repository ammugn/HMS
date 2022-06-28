package com.perscholas.hms.data;

import com.perscholas.hms.models.Appointment;
import com.perscholas.hms.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ammu Nair
 */
@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
    Optional<Users> findByName(String name);


 /*   @Query(value = "select * from course where id  > :id")
    List<Users> findAllPatients(String email);


    @Query(nativeQuery = true)
    List<Appointment> findDoctorAppointments(String email);
    @Query(nativeQuery = true)
    List<Appointment> findPatientAppointments(String email);*/
}
