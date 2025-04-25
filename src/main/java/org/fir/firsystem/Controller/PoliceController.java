package org.fir.firsystem.Controller;

import org.fir.firsystem.Service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/police")
public class PoliceController {

    @Autowired
    private ComplaintService complaintService;

}
