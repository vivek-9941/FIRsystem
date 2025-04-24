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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ComplaintServiceTest {
    @InjectMocks
    private ComplaintServiceImplementation complaintService;
    @Mock
    private GroqController groqController;
    @Mock
    private EmailController emailController;
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

        incidence = new Incidence();
        incidence.setDescription("This is a description");

        complaint = new Complaint();
        complaint.setIncidence(incidence);
        complaint.setUser(user);
        complaint.setIncidence(incidence);
        complaint.setAccused(accused);
        complaint.setVictim(victim);
    }

    private void commonmocks() {
        when(incidenceService.save(any(Incidence.class))).thenReturn(incidence);
        when(complaintRepository.save(any(Complaint.class))).thenAnswer(i -> i.getArgument(0));
    }

    @Test
    void AccusedVictimSAvedTest() {
        when(personService.save(accused)).thenReturn(accused);
        when(personService.save(victim)).thenReturn(victim);
        commonmocks();

        Complaint result = complaintService.saveComplaint(complaint);

        verify(personService).save(accused);
        verify(personService).save(victim);
        assertEquals(accused, result.getAccused());
        assertEquals(victim, result.getVictim());
    }

    @Test
    void GroqcontrollerTest() {
        when(groqController.callApi(anyString()))
                .thenReturn("summary")
                .thenReturn("[\"Cyber Crimes\"]");
        commonmocks();

        Complaint result = complaintService.saveComplaint(complaint);

        verify(groqController, atLeast(2)).callApi(anyString());

    }

    @Test
    void testappuserTest() {
        when(personService.save(any(Person.class))).thenReturn(new Person());
        when(appUserService.findByUsername("john_doe123")).thenReturn(user);
        commonmocks();

        Complaint result = complaintService.saveComplaint(complaint);

        verify(appUserService).findByUsername("john_doe123");
        assertEquals(user, result.getUser());
    }

    @Test
    void testEmailIsSent() {
        when(personService.save(any(Person.class))).thenReturn(new Person());
        when(appUserService.findByUsername("john_doe123")).thenReturn(user);
        when(complaintRepository.save(any(Complaint.class))).thenAnswer(i -> {
            Complaint c = i.getArgument(0);
            c.setId(101);
            return c;
        });
        commonmocks();

        complaintService.saveComplaint(complaint);

        verify(emailController).sendEmail(
                eq("john@doe.com"),
                contains("FIR Complaint Registered Successfully"),
                contains("101")
        );
    }
}