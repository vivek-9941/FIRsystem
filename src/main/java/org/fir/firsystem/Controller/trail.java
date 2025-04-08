package org.fir.firsystem.Controller;

import org.fir.firsystem.Model.AppUser;
import org.fir.firsystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/users")
public class trail {

        @Autowired
        private UserRepository appUserRepository;

        @PostMapping
        public ResponseEntity<AppUser> createUser(@RequestBody AppUser user) {
            AppUser savedUser = appUserRepository.save(user);
            return ResponseEntity.ok(savedUser);
        }
    }


