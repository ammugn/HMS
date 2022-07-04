package com.perscholas.hms.data;

import com.perscholas.hms.models.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Ammu Nair
 */
public interface AuthGroupRepository extends JpaRepository<AuthGroup,Integer> {
    List<AuthGroup> findByAuthEmail(String email);
    List<String> findByRole(String role);
}
