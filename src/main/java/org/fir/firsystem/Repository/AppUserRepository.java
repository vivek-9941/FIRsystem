package org.fir.firsystem.Repository;

import org.fir.firsystem.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    public AppUser findByUsername(String username);
    public AppUser findByEmail(String email);

}
