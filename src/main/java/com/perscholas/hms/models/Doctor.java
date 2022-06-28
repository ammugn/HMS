package com.perscholas.hms.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.*;

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
@SequenceGenerator(name="dseq", initialValue=100)
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="dseq")
    Long id;
    @NonNull
    String name;
    @NonNull
    String email;
    @NonNull
    String department;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new LinkedHashSet<>();
    public void addAppointment(Appointment a){
        appointments.add(a);
        a.setDoctor(this);
    }
    public void removeAppointment(Appointment a){
        appointments.remove(a);
        a.setDoctor(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor)) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(name, doctor.name) && Objects.equals(email, doctor.email) && Objects.equals(department, doctor.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, department);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                '}';
    }


}
