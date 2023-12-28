package ma.yc.api.myrhapi.controller ;

import ma.yc.api.myrhapi.dto.JobApplicationRequest;
import ma.yc.api.myrhapi.service.JobApplicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping ;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("api/v1/job_applications")
class JobApplicationController {
    private final JobApplicationService jobApplicationService;
    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }


    @PostMapping
    public void applyToJob(
            @RequestBody JobApplicationRequest jobApplicationRequest
    ){
        //TODO : CALL THE SERVICE TO JOB APPLICATION (job_id , applicant_id , )
        this.jobApplicationService.applyToJob(jobApplicationRequest);
    }
}