package nz.co.propellerhead.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nz.co.propellerhead.springboot.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
