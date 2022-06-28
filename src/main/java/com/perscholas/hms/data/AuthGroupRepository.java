package com.perscholas.hms.data;

import com.perscholas.hms.models.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ammu Nair
 */
public interface AuthGroupRepository extends JpaRepository<AuthGroup,Integer> {
    AuthGroup findByaEmail(String email);
}
