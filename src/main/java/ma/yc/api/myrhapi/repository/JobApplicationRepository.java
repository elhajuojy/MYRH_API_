package ma.yc.api.myrhapi.repository;

import ma.yc.api.myrhapi.entity.Applicant;
import ma.yc.api.myrhapi.entity.JobApplication;
import ma.yc.api.myrhapi.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface
JobApplicationRepository extends JpaRepository<JobApplication,Integer> {
    public List<JobApplication> findByStatus(String status );
    public List<JobApplication> findByJobOfferId(Long id);

    List<JobApplication> findAllByJobOffer(JobOffer jobOffer);
}
