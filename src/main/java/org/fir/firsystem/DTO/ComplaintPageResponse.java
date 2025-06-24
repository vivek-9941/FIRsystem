package org.fir.firsystem.DTO;

import org.fir.firsystem.Model.Complaint;
import java.util.List;

public class ComplaintPageResponse {
    private List<Complaint> complaints;
    private long total;

    public ComplaintPageResponse(List<Complaint> complaints, long total) {
        this.complaints = complaints;
        this.total = total;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public long getTotal() {
        return total;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
