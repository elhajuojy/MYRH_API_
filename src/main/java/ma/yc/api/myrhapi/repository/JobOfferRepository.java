package ma.yc.api.myrhapi.repository;
import ma.yc.api.myrhapi.entity.JobOffer;
import ma.yc.api.myrhapi.enums.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobOfferRepository extends JpaRepository<JobOffer,Long> {

    List<JobOffer> findByContract(Contract contract);

    List<JobOffer> findByTitle(String title);
}
