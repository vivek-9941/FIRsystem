package org.fir.firsystem.Controller;

import org.fir.firsystem.Model.AppUser;
import org.fir.firsystem.Model.Complaint;
import org.fir.firsystem.Service.AppUserService;
import org.fir.firsystem.Service.ComplaintService;
import org.fir.firsystem.utility.Utility_class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/complaint")
public class ComplaintController {
    @Autowired
    private ComplaintService service;
    @Autowired
    AppUserService appUserService;
    @Autowired
    private AppUserService userService;
    @Autowired
    private Utility_class util; // Properly injected utility class

    @PostMapping("/save")
    public ResponseEntity<?> saveComplaint(@RequestBody Complaint complaint) throws Exception {
        System.out.println(complaint);
        Complaint savedComplaint = service.saveComplaint(complaint);
        if(savedComplaint != null) {
            return new ResponseEntity<>(savedComplaint, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchComplaint() {
        String username = util.getCurrentUsername(); // Call method on the injected instance
        List<Complaint> complaints = userService.findcomplaintByUser(username);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    // In your controller direct extract the user from the token
    @GetMapping("/test-auth")
    public ResponseEntity<?> testAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authentication: " + auth);
        System.out.println("Principal: " + auth.getPrincipal());
        System.out.println("Authorities: " + auth.getAuthorities());
        service.toString();
        String username = Utility_class.getCurrentUsername();
        AppUser user = appUserService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

}