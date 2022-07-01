package com.perscholas.hms.repositories;

import com.perscholas.hms.data.UserRepository;
import com.perscholas.hms.models.Users;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ammu Nair
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.Standard.class)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
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
    public void testfindAll() {
        Users patient1 = new Users("Ammu Nair", "ammugn@gmail.com", "password", "1988-12-05", "Mill Creek,WA");
        patient1.setInsurance("Aetna");
        userRepository.save(patient1);

        Iterable<Users> employees = userRepository.findAll();
        assertThat(employees).extracting(Users::getName).containsOnly("Ammu Nair");

    }
}
