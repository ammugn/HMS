package com.perscholas.hms.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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
@Table(name="appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    String patientEmail;
    @NonNull
    String doctorEmail;
    @NonNull
    String issue;
    @NonNull
    String diagnosis;
    @NonNull
    String appointmentDate;
    @NonNull
    String appointmentTime;
    @NonNull
    boolean isComplete;//pass isComplete as hidden field


    @ManyToMany(mappedBy = "appointments", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},fetch = FetchType.EAGER)
    private Set<Users> users = new LinkedHashSet<>();

   public  void addUsers(Users user){
       users.add(user);
       user.getAppointments().add(this);
   }
    public void removeUsers(Users user) {
        users.remove(user);
        user.setAppointments(null);
    }

}
