package com.perscholas.hms.security;

import com.perscholas.hms.models.AuthGroup;
import com.perscholas.hms.models.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ammu Nair
 */
public class AppUserPrincipal implements UserDetails{

    private Users users;
    List<AuthGroup> authGroupList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authGroupSet=new HashSet<>();
         authGroupList.forEach(auth -> authGroupSet.add(new SimpleGrantedAuthority(auth.getRole())));
         return authGroupSet;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getEmail();
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
