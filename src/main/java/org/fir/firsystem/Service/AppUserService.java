package org.fir.firsystem.Service;

import org.fir.firsystem.Model.AppUser;
import org.fir.firsystem.Model.Complaint;

import java.util.List;

public interface AppUserService {
    AppUser save(AppUser appUser);
    AppUser savepolice(AppUser appUser);
    AppUser findByUsername(String username);
    public String getToken(String email);
    public List<Complaint> findcomplaintByUser(String username);
    public AppUser findByEmail(String email);
    public String validateUser(AppUser user);
    public String validatePolice(AppUser user);
     Boolean checkuserpresent(String email,String username);
}
