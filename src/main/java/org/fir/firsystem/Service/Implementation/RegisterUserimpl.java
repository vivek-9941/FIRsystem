package org.fir.firsystem.Service.Implementation;
import org.fir.firsystem.Model.AppUser;
import org.fir.firsystem.Repository.AppUserRepository;
import org.fir.firsystem.Service.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserimpl implements RegisterUser {
    @Autowired
    private AppUserRepository appUserRepository;
    @Override
    public AppUser registerUser(AppUser person) {
        AppUser appUser = appUserRepository.findByUsername(person.getUsername());
        if(appUser != null) {
            throw new DuplicateKeyException(person.getUsername()+" already exists~~!!");
        }
        return appUserRepository.save(person);
    }

}
