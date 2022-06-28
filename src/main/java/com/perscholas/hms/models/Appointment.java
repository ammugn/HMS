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
    boolean isComplete;


    @ManyToMany(mappedBy = "appointments", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},fetch = FetchType.EAGER)
    private Set<Users> users = new LinkedHashSet<>();

   public  void addUsers(Users user){
       users.add(user);
       user.getAppointments().add(this);
   }


}
