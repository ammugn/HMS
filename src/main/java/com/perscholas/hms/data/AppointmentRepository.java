package com.perscholas.hms.data;

import com.perscholas.hms.models.Appointment;
import com.perscholas.hms.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ammu Nair
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
     /*   @Query(nativeQuery = true)
        List<Appointment> findPatientAppointments(String name);*/


        /*@Query(nativeQuery = true)
        List<Appointment> findDoctorAppointments(String name);*/
}
