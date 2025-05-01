package org.fir.firsystem.Service.Security;

import org.fir.firsystem.Model.AppUser;
import org.fir.firsystem.Repository.AppUserRepository;
import org.fir.firsystem.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
//    @Autowired
//    private AppUserService service;

    @Autowired
    private AppUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user =  repository.findByUsername(username);
        System.out.println(user);
        if(user==null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }
        else{
            return new userdetails(user);
        }
    }
}
