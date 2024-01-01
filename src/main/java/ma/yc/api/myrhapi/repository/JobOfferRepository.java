package ma.yc.api.myrhapi.repository;
import ma.yc.api.myrhapi.entity.JobOffer;
import ma.yc.api.myrhapi.enums.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface JobOfferRepository extends JpaRepository<JobOffer,Long> , JpaSpecificationExecutor<JobOffer> {

    List<JobOffer> findByContract(Contract contract);

    List<JobOffer> findByTitle(String title);
    Page<JobOffer> findAllByCompanyId(Long id, org.springframework.data.domain.Pageable pageable);
    List<JobOffer> findByIdAndCompanyId(Long id, Long companyId);


}
