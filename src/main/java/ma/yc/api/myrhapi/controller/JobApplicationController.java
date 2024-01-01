package ma.yc.api.myrhapi.controller ;

import io.micrometer.core.annotation.Timed;
import ma.yc.api.myrhapi.dto.JobApplicationRequest;
import ma.yc.api.myrhapi.dto.JobApplicationResponse;
import ma.yc.api.myrhapi.service.JobApplicationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/job_applications")
//ALLOW ALL HEADERS AND ORIGINS
@CrossOrigin("*")
class JobApplicationController {
    private final JobApplicationService jobApplicationService;
    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }


    @PostMapping
    @Timed
    public JobApplicationResponse applyToJob(
            @ModelAttribute
            @Validated
            JobApplicationRequest jobApplicationRequest
    ){
        //: CALL THE SERVICE TO JOB APPLICATION (job_id , applicant_id )
        return this.jobApplicationService.applyToJob(jobApplicationRequest);
    }
}