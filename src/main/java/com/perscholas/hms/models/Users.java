package com.perscholas.hms.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @Column(unique = true)
    String email;

    String password;

    String dob;

    String address;

    String insurance;

    String department;

    public Users( String name,  String email, String password,  String dob,  String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.address = address;
    }


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
