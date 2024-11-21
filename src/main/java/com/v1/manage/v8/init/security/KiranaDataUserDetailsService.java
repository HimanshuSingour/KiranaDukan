package com.v1.manage.v8.init.security;

import com.v1.manage.v8.init.entity.KiranaUser;
import com.v1.manage.v8.init.repositories.KiranaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class KiranaDataUserDetailsService implements UserDetailsService {

    @Autowired
    private KiranaUserRepository kiranaUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        KiranaUser algorithmEntity = kiranaUserRepository.findByUserName(username);

        if (algorithmEntity == null) {
            throw new UsernameNotFoundException("User Not Found !!");
        }
        return new KiranaDataUserDetails(algorithmEntity);
    }
}
