package ma.yc.api.myrhapi.controller ;
import ma.yc.api.myrhapi.dto.JobOfferRequest;
import ma.yc.api.myrhapi.dto.JobOfferResponse;
import ma.yc.api.myrhapi.service.JobOfferService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/job_offers")
public class JobOfferController {

    private final JobOfferService jobOfferService;

    public JobOfferController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @GetMapping
    public ResponseEntity<Page<JobOfferResponse>> getAllJobOffers() {
        return ResponseEntity.ok(
                this.jobOfferService.getAllJobOffers(0, 10)
        );
    }

    @PostMapping
    public ResponseEntity<JobOfferResponse> addNewOffer(@RequestBody JobOfferRequest jobOfferRequest) {
        return ResponseEntity.ok(jobOfferService.addNewJobOffer(jobOfferRequest));
    }
}
