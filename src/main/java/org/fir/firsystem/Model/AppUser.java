package org.fir.firsystem.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column( nullable = true)
    private String firstName;
    @Column( nullable = true)
    private String lastName;
    @Column( nullable = false, unique = true)
    private String username;
    @Column( nullable = false)
    private String password;
    @Column( nullable = true)
    private String aadharNumber;
    @Column( nullable = true)
    private String email;
    @Column( nullable = true)
    @Embedded
    private Address address;

//    @OneToMany
//    @JsonManagedReference
//    List<Complaint> listComplaints;


}
