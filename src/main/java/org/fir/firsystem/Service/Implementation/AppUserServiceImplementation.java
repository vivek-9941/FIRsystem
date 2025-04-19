package org.fir.firsystem.Service.Implementation;

import org.fir.firsystem.Model.AppUser;
import org.fir.firsystem.Model.Complaint;
import org.fir.firsystem.Repository.AppUserRepository;
import org.fir.firsystem.Repository.ComplaintRepository;
import org.fir.firsystem.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImplementation implements AppUserService {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ComplaintRepository complaintRepository;
    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }
    @Override
    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public Complaint findcomplaintByUser(String username){
       AppUser user =  appUserRepository.findByUsername(username);
       return complaintRepository.findByUser(user);
    }


}
