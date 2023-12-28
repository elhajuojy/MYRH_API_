package ma.yc.api.myrhapi.service.impl;

import ma.yc.api.myrhapi.dto.JobApplicationRequest;
import ma.yc.api.myrhapi.dto.JobApplicationResponse;
import ma.yc.api.myrhapi.repository.JobApplicationRepository;
import ma.yc.api.myrhapi.service.JobApplicationService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    //TODO : MAPPER HERE

    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    @Override
    public JobApplicationResponse applyToJob(JobApplicationRequest jobApplicationRequest) {
        //TODO : IMPLEMENT THIS METHOD
        throw new NotImplementedException("TODO : IMPLEMENT THIS METHOD");
//        return this.jobApplicationRepository.save(jobApplicationRequest)

    }
}
