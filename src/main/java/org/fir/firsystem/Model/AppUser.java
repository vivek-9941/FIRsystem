package org.fir.firsystem.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AppUser  {

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

    @Enumerated(EnumType.STRING)
    Role role;


//    @OneToMany
//    @JsonManagedReference
//    List<Complaint> listComplaints;


}
