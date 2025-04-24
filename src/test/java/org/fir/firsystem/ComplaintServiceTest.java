package org.fir.firsystem;


import org.fir.firsystem.GenAi.GroqController;
import org.fir.firsystem.Mailing.EmailController;
import org.fir.firsystem.Model.AppUser;
import org.fir.firsystem.Model.Complaint;
import org.fir.firsystem.Model.Incidence;
import org.fir.firsystem.Model.Person;
import org.fir.firsystem.Repository.ComplaintRepository;
import org.fir.firsystem.Service.AppUserService;
import org.fir.firsystem.Service.Implementation.ComplaintServiceImplementation;
import org.fir.firsystem.Service.IncidenceService;
import org.fir.firsystem.Service.PersonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComplaintServiceTest {
    @InjectMocks
    private ComplaintServiceImplementation complaintService;
    @Mock
    private GroqController groqController;
    @Mock
    private EmailController emailController;
    ;
    @Mock
    private AppUserService appUserService;
    @Mock
    private ComplaintRepository complaintRepository;
    @Mock
    private IncidenceService incidenceService;
    @Mock
    private PersonService personService;

    private static Person accused;
    private static Person victim;
    private static AppUser user;
    private static Complaint complaint;
    private static Incidence incidence;

    @BeforeAll
    static void setup() {
        accused = new Person();
        accused.setFirstName("Accused");

        victim = new Person();
        victim.setFirstName("Victim");

        user = new AppUser();
        user.setFirstName("john");
        user.setLastName("doe");
        user.setEmail("john@doe.com");
        user.setUsername("john_doe123");

        Incidence incidence = new Incidence();
        incidence.setDescription("This is a description");

        complaint = new Complaint();
        complaint.setIncidence(incidence);
        complaint.setUser(user);
        complaint.setIncidence(incidence);
        complaint.setAccused(accused);
        complaint.setVictim(victim);


    }
    private void commonmocks(){
        when(incidenceService.save(incidence)).thenReturn(incidence);
        when(complaintRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        when(groqController.callApi(anyString())).thenReturn("summary", "[\"Cyber Crimes\"]");
    }
        @Test
        void AccusedVictimSAvedTest(){
          when(personService.save(accused)).thenAnswer(i -> i.getArgument(0));
          when(personService.save(victim)).thenAnswer(i -> i.getArgument(0));
            commonmocks();
            complaintService.saveComplaint(complaint);
            verify(personService).save(accused);
            verify(personService.save(victim));

        }
        @Test
    void GroqcontrollerTest(){
        when(groqController.callApi(anyString())).thenReturn("summary");
        commonmocks();
        verify(complaintRepository).save(complaint);
        }

        @Test
    void testappuserTest(){
            when(personService.save(any())).thenReturn(new Person());
            when(appUserService.findByUsername("john_doe")).thenReturn(user);
            commonmocks();

            complaintService.saveComplaint(complaint);

            verify(appUserService).findByUsername("john_doe");

        }


    @Test
    void testEmailIsSent() {
        when(personService.save(any())).thenReturn(new Person());
        when(appUserService.findByUsername("john_doe")).thenReturn(user);
        when(complaintRepository.save(any())).thenAnswer(i -> {
            Complaint c = i.getArgument(0);
            c.setId(101);
            return c;
        });
        commonmocks();

        complaintService.saveComplaint(complaint);

        verify(emailController).sendEmail(
                eq("john@example.com"),
                contains("FIR Complaint Registered Successfully"),
                contains("Your FIR Complaint ID is: 101")
        );
    }

}
