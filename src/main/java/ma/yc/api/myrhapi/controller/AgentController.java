package ma.yc.api.myrhapi.controller;

import ma.yc.api.myrhapi.dto.JobOfferChangeVisibilityRequest;
import ma.yc.api.myrhapi.dto.JobOfferResponse;
import ma.yc.api.myrhapi.service.JobOfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/agents")
public class AgentController {

    private final JobOfferService jobOfferService;

    public AgentController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @PostMapping("/job_offers")
    public ResponseEntity<JobOfferResponse> changeJobOfferVisibility(
           @RequestBody JobOfferChangeVisibilityRequest jobOfferChangeVisibilityRequest
    ) {
        return ResponseEntity.ok(jobOfferService.changeJobOfferVisibility(
                jobOfferChangeVisibilityRequest.getJobOfferId(),
                jobOfferChangeVisibilityRequest.isVisibility()
        ));
    }


}
