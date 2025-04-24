package org.fir.firsystem.Service;

import org.fir.firsystem.Model.AppUser;
import org.fir.firsystem.Model.Complaint;

public interface AppUserService {
    AppUser save(AppUser appUser);
    AppUser findByUsername(String username);
    public String getToken(String email);
    public Complaint findcomplaintByUser(String username);
    public AppUser findByEmail(String email);
    public String validateUser(AppUser user);
}
