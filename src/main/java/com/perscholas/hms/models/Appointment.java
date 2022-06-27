package com.perscholas.hms.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Ammu Nair
 */
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    String patientName;
    @NonNull
    String doctorName;
    @NonNull
    String issue;
    @NonNull
    String diagnosis;
    @NonNull
    String appointmentDate;
    @NonNull
    String appointmentTime;
    @NonNull
    boolean isComplete;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")

    private Patient patient;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Appointment() {

        this.diagnosis="No diagnosis yet";
       this.isComplete=false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return isComplete == that.isComplete && Objects.equals(id, that.id) && patientName.equals(that.patientName) && doctorName.equals(that.doctorName) && issue.equals(that.issue) && diagnosis.equals(that.diagnosis) && appointmentDate.equals(that.appointmentDate) && appointmentTime.equals(that.appointmentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientName, doctorName, issue, diagnosis, appointmentDate, appointmentTime, isComplete);
    }
}
