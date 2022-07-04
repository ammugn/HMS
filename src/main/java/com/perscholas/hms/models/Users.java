package com.perscholas.hms.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Entity
@Table(name="users")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    String name;
    @NonNull
    @Column(unique = true)
    String email;
    @NonNull
    @Setter(AccessLevel.NONE)
    String password;
    @NonNull
    String dob;
    @NonNull
    String address;

    String insurance;

    String department;

    Boolean isAdmin=false;


    public Users( String name,  String email, String password,  String dob,  String address) {
        this.name = name;
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.dob = dob;
        this.address = address;
    }

    public void setPassword(String password) {

        this.password = new BCryptPasswordEncoder().encode(password);
    }
//owning side
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},fetch = FetchType.EAGER)
    @JoinTable(name = "users_appointments",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "appointments_id"))
    private Set<Appointment> appointments = new LinkedHashSet<>();



    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
        appointment.getUsers().add(this);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
        appointment.setUsers(null);
    }

}
