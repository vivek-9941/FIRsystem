package org.fir.firsystem.utility;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
//this is the utility class to extract the username from token
//as always we need username or object to do operation we cannot always send the user object from frontend
public class Utility_class {
    public  String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // Returns the username
    }
}