package org.fir.firsystem;

import org.fir.firsystem.Model.Complaint;
import org.fir.firsystem.Model.Incidence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FirSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirSystemApplication.class, args);
	}

}
