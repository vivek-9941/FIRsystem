package org.fir.firsystem.Service.Implementation;

import org.fir.firsystem.Repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation {

    @Autowired
    private AppUserRepository userRepository;
}
