package org.fir.firsystem.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    @Embedded
    private UserAddress address;


}
