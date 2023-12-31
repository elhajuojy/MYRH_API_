package ma.yc.api.myrhapi.repository;

import ma.yc.api.myrhapi.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant,Long> {


//    @Query("select a from Applicant a where a.email = ?1")
    Optional<Applicant> findByEmail(String login);
}
