package org.fir.firsystem.Service.Implementation;

import org.fir.firsystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation {

    @Autowired
    private UserRepository userRepository;
}
