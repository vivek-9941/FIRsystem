package org.fir.firsystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Entity
@Data
@RequiredArgsConstructor

public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ElementCollection
    private List<String> Accused_details;

    private String evidence;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id") // foreign key in Complaint table
    private AppUser user;
}
