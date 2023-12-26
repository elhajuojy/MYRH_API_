package ma.yc.api.myrhapi.repository;

import ma.yc.api.myrhapi.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant,Long> {
}
