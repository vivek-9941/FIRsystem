package org.fir.firsystem.Service.Security;

import org.fir.firsystem.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private AppUserService service;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails =  service.findByUsername(username);
        if(userDetails==null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }
        else{
            return userDetails;
        }
    }
}
