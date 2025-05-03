package org.fir.firsystem.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor

public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accused_id")
    private Person accused;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "victim_id")
    private Person victim ;
    @Column(nullable = true)
    private String evidenceLink;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "incidence_id")
    private Incidence incidence;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = true)  // Add nullable = true here
    private AppUser user;
}
