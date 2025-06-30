package org.fir.firsystem.Service;

import org.fir.firsystem.DTO.ComplaintPageResponse;
import org.fir.firsystem.Model.Complaint;

public interface ComplaintService {
    Complaint saveComplaint(Complaint complaint);
    Complaint updateComplaint(String verdict,int id);
    ComplaintPageResponse getAllComplaints(Integer page, Integer size);
}
