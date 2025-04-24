package org.fir.firsystem.Repository;

import org.fir.firsystem.Model.AppUser;
import org.fir.firsystem.Model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {
    Complaint findByUser(AppUser user);


}
