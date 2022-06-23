package com.perscholas.hms.services;

import com.perscholas.hms.data.PatientRepository;
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
public class PatientService {
    PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> findAll(){
         return patientRepository.findAll();
    }

    public void saveOrUpdate(Patient p){
        log.info(p.toString());
        patientRepository.save(p);

    }
    public Optional<Patient> findById(long id)
    {
        return patientRepository.findById(id);
    }

    public void delete(Patient patient) {
       patientRepository.delete(patient);
    }
}
