package org.fir.firsystem.Controller;


import org.fir.firsystem.Mailing.EmailController;
import org.fir.firsystem.Model.AppUser;
import org.fir.firsystem.Service.AppUserService;
import org.fir.firsystem.Service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private EmailController emailController;
    @Autowired
    private AppUserService appUserService;

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
        System.out.println(isValid);
        if (isValid) {
            otpService.deleteOtp(email); // Optional: clean up manually
            String token = appUserService.getToken(email);
            return ResponseEntity.ok().body(token);
//            return ResponseEntity.ok().body("correct otp OTP");

        } else {
            return ResponseEntity.badRequest().body("Invalid or expired OTP");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AppUser user) {

       if( appUserService.checkuserpresent(user.getEmail() , user.getUsername()) ) {
           return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("User already exists");
       }
       else {
           System.out.println(user);
           AppUser saveduser = appUserService.save(user);
           System.out.println(user);
           if (saveduser != null) {
               return ResponseEntity.ok().body(saveduser);
           } else {
               return ResponseEntity.badRequest().body("Something went wrong");
           }
       }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AppUser user) {
        System.out.println(user);
        String token =  appUserService.validateUser(user);
        System.out.println(token);
        if(token != null) {
            return ResponseEntity.ok().body(token);
        }
        else{
            return ResponseEntity.badRequest().body("Username or password is incorrect");
        }
    }
}