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

    @Query(value = "select * from users where insurance IS NOT NULL",nativeQuery = true)
    List<Users> findAllPatients();

    @Query(value = "select * from users where department IS NOT NULL and is_admin IS FALSE",nativeQuery = true)
    List<Users> findAllDoctors();
    @Query(value = "select * from users where is_admin IS TRUE",nativeQuery = true)
    List<Users> findAllAdmins();

   /* @Query(nativeQuery = true)
    List<Appointment> findDoctorAppointments(String email);
    @Query(nativeQuery = true)
    List<Appointment> findPatientAppointments(String email);*/


}
