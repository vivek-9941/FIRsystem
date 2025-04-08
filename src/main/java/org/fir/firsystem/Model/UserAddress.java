package org.fir.firsystem.Model;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
public class UserAddress {
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    public UserAddress() {

    }
}
