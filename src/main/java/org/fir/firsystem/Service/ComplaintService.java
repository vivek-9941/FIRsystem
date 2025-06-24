package org.fir.firsystem.Service;

import org.fir.firsystem.DTO.ComplaintPageResponse;
import org.fir.firsystem.Model.Complaint;

import java.util.List;

public interface ComplaintService {
    Complaint saveComplaint(Complaint complaint);
    Complaint updateComplaint(Complaint complaint);
    ComplaintPageResponse getAllComplaints(Integer page, Integer size);
}
