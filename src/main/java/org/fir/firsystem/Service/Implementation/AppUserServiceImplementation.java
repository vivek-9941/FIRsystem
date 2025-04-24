package org.fir.firsystem.Service.Implementation;

import org.fir.firsystem.Model.AppUser;
import org.fir.firsystem.Model.Complaint;
import org.fir.firsystem.Repository.AppUserRepository;
import org.fir.firsystem.Repository.ComplaintRepository;
import org.fir.firsystem.Service.AppUserService;
import org.fir.firsystem.Service.Security.JWTServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImplementation implements AppUserService {

    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    JWTServiceImplementation jwtService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ComplaintRepository complaintRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public AppUser save(AppUser appUser) {
        appUser.setPassword(passwordEncoder
                .encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }



    public Complaint findcomplaintByUser(String username) {
        AppUser user = appUserRepository.findByUsername(username);
        return complaintRepository.findByUser(user);
    }

    @Override
    public AppUser findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public String validateUser(AppUser u) {


        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        u.getUsername(), u.getPassword()
                )
        );
        if (authenticate.isAuthenticated()) {
            //after login send the token
            return jwtService.generateToken(u.getUsername(), "USER");

        }
        return null;

    }


public String getToken(String email) {
    AppUser user = findByEmail(email);
    return jwtService.generateToken(user.getUsername(), "USER");
}

// here after the opt verification the jwt will be sent
//also if user done manual login using password here send the token back

}
