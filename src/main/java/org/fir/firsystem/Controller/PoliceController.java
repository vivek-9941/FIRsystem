package org.fir.firsystem.Controller;

import org.fir.firsystem.Service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/police")
@CrossOrigin
public class PoliceController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("/update")
    public ResponseEntity<?> updateStatusOfComplaint(@RequestParam String verdict ,@RequestParam int id) {
        return ResponseEntity.status(HttpStatus.OK).body(complaintService.updateComplaint(verdict , id));
    }

    @GetMapping("/complaints")
    public ResponseEntity<?> getAllComplaint(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(complaintService.getAllComplaints(pageNumber, size));
    }
}
