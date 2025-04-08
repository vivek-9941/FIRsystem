package org.fir.firsystem.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;
@Entity
@Data
@RequiredArgsConstructor
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true)
    @ElementCollection
    private List<String> Accused_details;

    private String evidenceLink;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id") // foreign key in Complaint table
    private AppUser user;
}
