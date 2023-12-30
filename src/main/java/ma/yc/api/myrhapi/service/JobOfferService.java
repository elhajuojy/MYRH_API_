package ma.yc.api.myrhapi.service;

import ma.yc.api.myrhapi.dto.JobOfferRequest;
import ma.yc.api.myrhapi.dto.JobOfferResponse;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface JobOfferService {

    JobOfferResponse addNewJobOffer(JobOfferRequest jobOfferRequest);
    Page<JobOfferResponse> getAllJobOffers(int page, int size ,String title, String education);
    Page<JobOfferResponse> getAllJobOffers(Map<String,String> queryParams);

    JobOfferResponse changeJobOfferVisibility(String jobOfferId, boolean visibility);

    JobOfferResponse getJobOfferById(Integer id);

//    Page<JobOfferResponse> searchJobOffers(int page, int size, String... search);
}
