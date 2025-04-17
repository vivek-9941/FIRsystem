package org.fir.firsystem.Service.Implementation;

import org.fir.firsystem.Model.AppUser;
import org.fir.firsystem.Model.Complaint;
import org.fir.firsystem.Model.Incidence;
import org.fir.firsystem.Model.Person;
import org.fir.firsystem.Repository.AppUserRepository;
import org.fir.firsystem.Repository.ComplaintRepository;
import org.fir.firsystem.Repository.IncidenceRepository;
import org.fir.firsystem.Repository.PersonRepository;
import org.fir.firsystem.Service.ComplaintSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintSaveimpl implements ComplaintSave {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private IncidenceRepository incidenceRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Complaint saveComplaint(Complaint complaint) {
        System.out.println(complaint);
        Person savedAccused = personRepository.save(complaint.getAccused());
        Person savedVictim = personRepository.save(complaint.getVictim());
        Incidence saved_incidence = incidenceRepository.save(complaint.getIncidence());
        AppUser user = appUserRepository.findByUsername(complaint.getUser().getUsername());

        Complaint savedComplaint = new Complaint();

        savedComplaint.setAccused(savedAccused);
        savedComplaint.setVictim(savedVictim);
        savedComplaint.setIncidence(saved_incidence);
        savedComplaint.setEvidenceLink(complaint.getEvidenceLink());
        savedComplaint.setStatus(complaint.getStatus());
        savedComplaint.setUser(user);

        return complaintRepository.save(savedComplaint);
    }


}
