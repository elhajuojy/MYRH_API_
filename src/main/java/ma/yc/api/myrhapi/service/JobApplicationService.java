package ma.yc.api.myrhapi.service;

import ma.yc.api.myrhapi.dto.JobApplicationRequest;
import ma.yc.api.myrhapi.dto.JobApplicationResponse;

public interface JobApplicationService {

    public JobApplicationResponse applyToJob(
            JobApplicationRequest jobApplicationRequest);
}
