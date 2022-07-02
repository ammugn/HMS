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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Ammu Nair
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ExtendWith(MockitoExtension.class)
@Slf4j
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = HmsApplication.class)
//@ContextConfiguration(classes = { SpringTestConfiguration.class })
class UserServicesTest {
//    @InjectMocks
  UserService userService;

    @Mock
    UserRepository userRepository;
    @Mock
    AppointmentRepository appointmentRepository;
    private Users userTest;

    @BeforeEach
    void init() {
        userService = new UserService(userRepository, appointmentRepository);
                userTest = Users.builder()
                .id(1L)
                .name("Test User")
                .email("test@gmail.com")
                .password("password")
                .dob("1990-10-10")
                .address("WA")
                .build();
    }

    @AfterEach
    void tearDown() {}

    @BeforeAll
    static void beforeAll() {

    }

    @AfterAll
    static void afterAll() {}
    @DisplayName("JUnit test for findAllUsers method")
    @Test
    void findAllUsers(){

        Users user1=Users.builder()
                .name("Ammu Nair")
                .email("ammugn@gmail.com")
                .password("password")
                .dob("1988-12-05")
                .address("WA")
                .build();
        given(userRepository.findAll()).willReturn(List.of(userTest,user1));

        List<Users> usersList = userService.findAllUsers();
        assertThat(usersList).isNotNull();
        assertThat(usersList.size()).isEqualTo(2);

    }


    @DisplayName("JUnit test for saveorupdateUser method")
    @Test
    public void findByEmailTest(){

        userService.findByEmail("testing@gmail.com");
        assertThat(userTest).extracting(Users::getName).isEqualTo("Test User");
    }

    @DisplayName("JUnit test for delete method")
    @Test
    public void deleteTest(){
        Long userId=1L;
        willDoNothing().given(userRepository).deleteById(userId);

        // when -  action or the behaviour that we are going test
        userService.delete(userTest);

        // then - verify the output
        verify(userRepository, times(1)).deleteById(userId);
    //    assertThat(userService.findById(1L)).isEmpty();
    //    assertThat(userService.findById(2L)).isEmpty();
    }
}
