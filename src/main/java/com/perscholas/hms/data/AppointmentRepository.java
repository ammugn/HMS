package com.perscholas.hms.data;

import com.perscholas.hms.models.Appointment;
import com.perscholas.hms.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ammu Nair
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    @Query(value ="SELECT u.name FROM users u JOIN appointment a on u.email =a.patient_email",nativeQuery = true)
    String findPatientNameForAppointments(String email);


    @Query(value ="SELECT u.name FROM users u JOIN appointment a on u.email =a.doctor_email",nativeQuery = true)
    String findDoctorNameForAppointments(String email);

    List<Appointment> findByPatientEmail(String email);

    @Query(value ="SELECT * FROM appointment a where a.patient_email=:email",nativeQuery = true)
    List<Appointment> findPatientAppointments(String email);
    //List<Appointment> findDoctorAppointments(String email);
}
