package org.fir.firsystem.Service;

import org.fir.firsystem.Model.AppUser;

public interface AppUserService {
    AppUser save(AppUser appUser);
    AppUser findByUsername(String username);
}
