package com.perscholas.hms.repositories;

import com.perscholas.hms.HmsApplication;
import com.perscholas.hms.data.AppointmentRepository;
import com.perscholas.hms.data.UserRepository;
import com.perscholas.hms.models.Appointment;
import com.perscholas.hms.models.Users;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ammu Nair
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.Standard.class)
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    private Users userTest,patient1,doctor1,admin1;

    private Set<Appointment> appointment;

    @BeforeEach
    void setUp() {
        userTest = Users.builder()
                .id(1L)
                .name("Test User")
                .email("test@gmail.com")
                .password("password")
                .dob("1990-10-10")
                .address("WA")
                .build();
        patient1 = Users.builder()
                .id(2L)
                .name("Ammu")
                .email("ammugn@gmail.com")
                .password("password")
                .dob("1990-10-10")
                .address("WA")
                .insurance("Aetna")
                .build();
        doctor1 = Users.builder()
                .id(3L)
                .name("Test Doctor")
                .email("doctor@gmail.com")
                .password("password")
                .dob("1990-10-10")
                .address("WA")
                .department("Cardiology")
                .isAdmin(false)
                .build();
        admin1 = Users.builder()
                .id(4L)
                .name("Test Admin")
                .email("admin@gmail.com")
                .password("password")
                .dob("1990-10-10")
                .address("WA")
                .isAdmin(true)
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @BeforeAll
    static void beforeAll() {
    }

    @AfterAll
    static void afterAll() {
    }

    @Test
    public void testfindAllPatients() {
        userRepository.save(userTest);
        userRepository.save(patient1);
        userRepository.save(doctor1);
        List<Users> patientList = userRepository.findAllPatients();
        System.out.println(patientList.toString());
        assertThat(patientList).isNotNull();
        assertThat(patientList.size()).isEqualTo(1);
        assertThat(patientList).extracting(Users::getName).containsOnly("Test Patient");

    }
    @Test
    public void testfindAllDoctors() {
        userRepository.save(userTest);
        userRepository.save(patient1);
        userRepository.save(doctor1);

        List<Users> doctorList = userRepository.findAllDoctors();
        assertThat(doctorList).isNotNull();
        assertThat(doctorList.size()).isEqualTo(1);
        assertThat(doctorList).extracting(Users::getName).containsOnly("Test Doctor");

    }
    @Test
    public void testfindAllAdmins() {
        userRepository.save(userTest);
        userRepository.save(patient1);
        userRepository.save(doctor1);
        userRepository.save(admin1);

        List<Users> doctorList = userRepository.findAllAdmins();
        assertThat(doctorList).isNotNull();
        assertThat(doctorList.size()).isEqualTo(1);
        assertThat(doctorList).extracting(Users::getName).containsOnly("Test Admin");

    }
}
