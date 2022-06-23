package com.perscholas.hms.data;

import com.perscholas.hms.models.Doctor;
import com.perscholas.hms.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ammu Nair
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
