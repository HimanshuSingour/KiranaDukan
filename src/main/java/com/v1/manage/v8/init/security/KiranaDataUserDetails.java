package com.v1.manage.v8.init.security;

import com.v1.manage.v8.init.entity.KiranaUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class KiranaDataUserDetails implements UserDetails {

    private KiranaUser kiranaUser;

    public KiranaDataUserDetails(KiranaUser kiranaUser) {
        this.kiranaUser = kiranaUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert the user's roles to SimpleGrantedAuthorities (one per role)
        return kiranaUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name())) // Use role's name for authority
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return kiranaUser.getPassword();
    }

    @Override
    public String getUsername() {
        return kiranaUser.getUsername();
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
