package com.perscholas.hms.data;

import com.perscholas.hms.models.Patient;
import com.perscholas.hms.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ammu Nair
 */
@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
}
