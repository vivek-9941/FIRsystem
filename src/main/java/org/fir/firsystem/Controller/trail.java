package org.fir.firsystem.Controller;

import org.fir.firsystem.Model.*;
import org.fir.firsystem.Repository.ComplaintRepository;
import org.fir.firsystem.Repository.AppUserRepository;
import org.fir.firsystem.Repository.IncidenceRepository;
import org.fir.firsystem.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class trail {

        @Autowired
        private AppUserRepository appUserRepository;

        @Autowired
        private ComplaintRepository complaintRepository;

        @Autowired
        private IncidenceRepository incidenceRepository;

        @Autowired
        private PersonRepository personRepository;

        @PostMapping("/")
        public ResponseEntity<Complaint> createUser(@RequestBody Complaint complaint) {
            System.out.println(complaint);
            Person accused = complaint.getAccused();
            Person victim = complaint.getVictim();

            Person savedAccused = personRepository.save(accused);
            Person savedVictum = personRepository.save(victim);

            int accued_id = savedVictum.getId();
            int victim_id = savedAccused.getId();

            Incidence incidence = complaint.getIncidence();
            Incidence saved_incidence = incidenceRepository.save(incidence);
            int incidence_id = saved_incidence.getId();

            AppUser saved_user = appUserRepository.save(complaint.getUser());

            Complaint savedComplaint = new Complaint();
            savedComplaint.setAccused(savedAccused);
            savedComplaint.setVictim(savedVictum);
            savedComplaint.setIncidence(saved_incidence);
            savedComplaint.setEvidenceLink(complaint.getEvidenceLink());
            savedComplaint.setStatus(complaint.getStatus());
            savedComplaint.setUser(saved_user);

            return ResponseEntity.status(HttpStatus.OK).body(complaintRepository.save(savedComplaint));
        }
    }


