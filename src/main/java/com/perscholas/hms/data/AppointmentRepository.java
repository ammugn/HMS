package com.perscholas.hms.data;

import com.perscholas.hms.models.Appointment;
import com.perscholas.hms.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ammu Nair
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
        }
