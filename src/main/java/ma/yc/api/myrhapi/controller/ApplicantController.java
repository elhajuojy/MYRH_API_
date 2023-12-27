package ma.yc.api.myrhapi.controller ;

import ma.yc.api.myrhapi.dto.ApplicantRequest;
import ma.yc.api.myrhapi.dto.ApplicantResponse;
import ma.yc.api.myrhapi.service.ApplicantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/applicants")
public class ApplicantController {


    private final ApplicantService applicantService ;

    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @PostMapping
    public ResponseEntity<ApplicantResponse> register(
            @RequestBody ApplicantRequest applicantRequest){
        //TODO : CALL THE SERVICE TO REGISTER NEW  APPLICANT TO PLATFORM (resume , applicant_id ... )

        return ResponseEntity.ok().body(
                this.applicantService.register(applicantRequest)
        );
    }
}