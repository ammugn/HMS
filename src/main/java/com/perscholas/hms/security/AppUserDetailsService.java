package com.perscholas.hms.security;

import com.perscholas.hms.data.AuthGroupRepository;
import com.perscholas.hms.models.AuthGroup;
import com.perscholas.hms.models.Users;
import com.perscholas.hms.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Ammu Nair
 */
@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AppUserDetailsService implements UserDetailsService {
    AuthGroupRepository authGroupRepository;
    UserService userService;
    @Autowired
    public AppUserDetailsService(AuthGroupRepository authGroupRepository, UserService userService) {
        this.authGroupRepository = authGroupRepository;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = null;
        try {
            user = userService.findByEmail(username);

        } catch (NoSuchElementException ex){
            log.warn("Couldn't find email: " + username);
            ex.printStackTrace();
        } catch(UsernameNotFoundException e){
            log.warn("Couldn't find email: " + username);
            e.printStackTrace();
        }
        List<AuthGroup> authGroups = authGroupRepository.findByAuthEmail(username);
        return new AppUserPrincipal(user, authGroups);
    }
}
