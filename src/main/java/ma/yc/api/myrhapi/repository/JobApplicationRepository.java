package ma.yc.api.myrhapi.repository;

import ma.yc.api.myrhapi.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface
JobApplicationRepository extends JpaRepository<JobApplication,Long> {
    public List<JobApplication> findByStatus(String status );

}
