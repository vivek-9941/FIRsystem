package org.fir.firsystem.Controller;


import org.fir.firsystem.Mailing.EmailController;
import org.fir.firsystem.Model.AppUser;
import org.fir.firsystem.Service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserController {


    @Autowired
    private EmailController emailController;


    //    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody AppUser user) {
//
//    }
    @Autowired
    private OtpService otpService;

    @PostMapping("/sendOtp")
    public ResponseEntity<?> sendOtp(@RequestParam String email) {
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000);


        String subject = "Your OTP for e-FIR";
        String body = "Dear user,\n\nYour OTP is: " + otp +
                "\n\nValid for 2 minutes. Do not share it.\n\n- e-FIR Team";


        boolean verdict = emailController.sendEmail(email, subject, body);
        if (verdict) {
            otpService.saveOtp(email, otp); // Save to Redis with TTL

            return ResponseEntity.ok().body("OTP sent successfully");
        } else {
            return ResponseEntity.ok().body("OTP not sent");
        }
    }


    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = otpService.verifyOtp(email, otp);

        if (isValid) {
            otpService.deleteOtp(email); // Optional: clean up manually
            return ResponseEntity.ok().body("OTP verified successfully!");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired OTP");
        }
    }


//    @PostMapping("/verifyOtp")
//    public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
//        User user = userService.verifyOtp(email, otp);
//        String token = jwtUtil.generateToken(user.getEmail());
//        return ResponseEntity.ok().body(token);
//    }
}