package com.perscholas.hms.services;

import com.perscholas.hms.HmsApplication;
import com.perscholas.hms.data.AppointmentRepository;
import com.perscholas.hms.data.UserRepository;
import com.perscholas.hms.models.Users;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * @author Ammu Nair
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
//@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HmsApplication.class)
class UserServicesTest {
    @MockBean
    UserService userService;

    @Mock
    static
    UserRepository userRepository;
    static
    AppointmentRepository appointmentRepository;

    @BeforeEach
    void setUp() {}

    @AfterEach
    void tearDown() {}

    @BeforeAll
    static void beforeAll() {



    }

    @AfterAll
    static void afterAll() {}

    @Test
    void findAllUsers(){


        Users patient1=new Users("Ammu Nair","ammugn@gmail.com", "password","1988-12-05","Mill Creek,WA");
        patient1.setInsurance("Aetna");
        userService.saveOrUpdate(patient1);

        List<Users> expected = new ArrayList<>(Arrays.asList(patient1));
        log.info("Expected"+expected.toString());
        log.info("Actual"+userService.findAllUsers().toString());
        assertThat(userService.findAllUsers()).hasSameElementsAs(expected);



        given(userService.findAllUsers()).willReturn((List<Users>) patient1);
        List<Users> result = userService.findAllUsers();
        assertEquals(result.size(), 1);

    }
}
