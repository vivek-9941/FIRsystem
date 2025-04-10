package org.fir.firsystem.Model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Embeddable

public class Accused
{
    private String firstName;
    private String lastName;
    private String phone;
    @Embedded
    private Address address;
}
