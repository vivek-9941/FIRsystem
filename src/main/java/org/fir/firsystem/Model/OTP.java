package org.fir.firsystem.Model;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDateTime;

@Cacheable
@Entity

public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String otp;
    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime expirationTime = startTime.plusMinutes(5);
}
