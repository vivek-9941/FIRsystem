package org.fir.firsystem.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
public class Incidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime time;
    private LocalDate date;
    @Embedded
    private Address address;
    private String description;
}
