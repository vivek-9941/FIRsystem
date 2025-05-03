package org.fir.firsystem;

import org.fir.firsystem.Model.*;
import org.fir.firsystem.Service.AppUserService;
import org.fir.firsystem.Service.ComplaintService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class ComplaintServiceIntegrationTest {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private AppUserService appUserService;

    @Test
    public void testEmailIsSentWhenComplaintIsSaved() {
        // Setup user in DB (if required)
        AppUser user = new AppUser();
        user.setUsername("testuser");
        user.setEmail("yourdestinationemail@gmail.com"); // <- recipient
        user.setFirstName("Test");
        user.setLastName("User");
        user.setPassword("dummy"); // Required field
        appUserService.save(user); // Make sure this persists in DB

        // Mock or prepare complaint data
        Person victim = new Person(1, "Doe", "M", "30",new Address("pune", "pune", "pine","pune","pune"));
        Person accused = new Person(1, "Doe", "M", "30",new Address("pune", "pune", "pine","pune","pune"));
//        Incidence incidence = new Incidence("Sample incident description");

        Complaint complaint = new Complaint();
        complaint.setVictim(victim);
        complaint.setAccused(accused);
//        complaint.setIncidence(incidence);
        complaint.setEvidenceLink("https://evidence-link.com");

        // Now call the actual service that should send the email
        Complaint savedComplaint = complaintService.saveComplaint(complaint);

        // Assert the complaint is saved (you may want to validate further)
        assertNotNull(savedComplaint);
        assertNotNull(savedComplaint.getId());

        // You won't be able to "assert" email delivery here unless you use a mock SMTP server.
        // But manually check your inbox for the real test result.
    }
}
