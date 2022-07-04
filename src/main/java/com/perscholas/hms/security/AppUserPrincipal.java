package com.perscholas.hms.security;

import com.perscholas.hms.models.AuthGroup;
import com.perscholas.hms.models.Users;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author Ammu Nair
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppUserPrincipal implements UserDetails{

    Users user;
    List<AuthGroup> authGroupList;
    public AppUserPrincipal(Users user, List<AuthGroup> authGroupList) {
        this.user = user;
        this.authGroupList = authGroupList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authGroupList == null) return Collections.emptyList();
        Set<SimpleGrantedAuthority> authGroupSet=new HashSet<>();
         authGroupList.forEach(auth -> authGroupSet.add(new SimpleGrantedAuthority(auth.getRole())));
         return authGroupSet;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
