package com.perscholas.hms.services;

import com.perscholas.hms.data.DoctorRepository;
import com.perscholas.hms.models.Doctor;
import com.perscholas.hms.models.Patient;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ammu Nair
 */

@Service @Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DoctorService {
    DoctorRepository doctorRepository;
    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
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

    public void delete(Doctor doctor) {
        doctorRepository.delete(doctor);
    }
}
