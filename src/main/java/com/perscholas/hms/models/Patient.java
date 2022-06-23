package com.perscholas.hms.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Ammu Nair
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name="pseq", initialValue=200)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pseq")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    @NonNull
    String email;
    @NonNull
    String dob;
    @NonNull
    String insurance;
    @NonNull
    String address;

   /* @ToString.Exclude
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<Appointment> appointmentList=new java.util.ArrayList<>();

    public void addAppointment(Appointment a){
        appointmentList.add(a);

    }
    public void removeAppointment(Appointment a){
        appointmentList.remove(a);
        a.setPatient(null);
    }

    @ToString.Exclude
    @ManyToMany(mappedBy = "patientList", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    List<Doctor> doctorsList = new ArrayList<>();

    public void addDoctor(Doctor doctor) {
        doctorsList.add(doctor);
        doctor.getPatientList().add(this);
    }
*/
    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", insurance='" + insurance + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(firstName, patient.firstName) && Objects.equals(lastName, patient.lastName) && Objects.equals(dob, patient.dob) && Objects.equals(insurance, patient.insurance) && Objects.equals(address, patient.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dob, insurance, address);
    }
}
