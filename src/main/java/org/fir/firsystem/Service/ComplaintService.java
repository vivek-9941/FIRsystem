package org.fir.firsystem.Service;

import org.fir.firsystem.Model.Complaint;

import java.util.List;

public interface ComplaintService {
    Complaint saveComplaint(Complaint complaint);
    Complaint updateComplaint(Complaint complaint);
    List<Complaint> getAllComplaints(Integer pageNumber, Integer pageSize);
}
