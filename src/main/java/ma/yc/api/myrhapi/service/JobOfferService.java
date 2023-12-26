package ma.yc.api.myrhapi.service;

import ma.yc.api.myrhapi.dto.JobOfferRequest;
import ma.yc.api.myrhapi.dto.JobOfferResponse;
import org.springframework.data.domain.Page;

public interface JobOfferService {

    JobOfferResponse addNewJobOffer(JobOfferRequest jobOfferRequest);
    Page<JobOfferResponse> getAllJobOffers(int page, int size);

    JobOfferResponse changeJobOfferVisibility(String jobOfferId, boolean visibility);
}
