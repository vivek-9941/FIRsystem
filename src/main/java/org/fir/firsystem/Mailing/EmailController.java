package org.fir.firsystem.Mailing;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @Autowired
    private EmailSenderService emailSenderService;
    public boolean sendEmail(String toEmail,
                            String subject,
                            String body) {
        try {
            emailSenderService.sendEmail(toEmail, body, subject);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}