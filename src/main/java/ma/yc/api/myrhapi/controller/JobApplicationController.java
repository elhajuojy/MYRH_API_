package ma.yc.api.myrhapi.controller ;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping ;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("api/v1/job_applications")
class JobApplicationController {


    @PostMapping
    public void applyToJob(){
        //TODO : CALL THE SERVICE TO JOB APPLICATION (job_id , applicant_id , )
    }
}