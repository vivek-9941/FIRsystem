package org.fir.firsystem.Service;

import org.fir.firsystem.Model.AppUser;
import org.fir.firsystem.Model.Complaint;

import java.util.List;

public interface AppUserService {
    int first=234;
    AppUser save(AppUser appUser);
    AppUser savepolice(AppUser appUser);
    AppUser findByUsername(String username);
    String getToken(String email);
    List<Complaint> findcomplaintByUser(String username);
    AppUser findByEmail(String email);
    String validateUser(AppUser user);
    String validatePolice(AppUser user);
    Boolean checkuserpresent(String email,String username);
}
