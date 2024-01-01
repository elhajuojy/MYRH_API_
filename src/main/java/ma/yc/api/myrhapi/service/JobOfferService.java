package ma.yc.api.myrhapi.service;

import ma.yc.api.myrhapi.dto.JobOfferApplicationsPageResponse;
import ma.yc.api.myrhapi.dto.JobOfferRequest;
import ma.yc.api.myrhapi.dto.JobOfferResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface JobOfferService {

    JobOfferResponse addNewJobOffer(JobOfferRequest jobOfferRequest);
    Page<JobOfferResponse> getAllJobOffers(int page, int size ,String title, String education);
    Page<JobOfferResponse> getAllJobOffers(Map<String,String> queryParams);

    JobOfferResponse changeJobOfferVisibility(String jobOfferId, boolean visibility);

    JobOfferResponse getJobOfferById(Long id);

    Page<JobOfferResponse> getJobOffersByCompanyId(Long id, Map<String, String> queryParams);

    List<JobOfferApplicationsPageResponse> getJobOfferApplicationByJobOfferIdAndCompanyId(String id, String jobOfferId, Map<String, String> queryParams);

//    Page<JobOfferResponse> searchJobOffers(int page, int size, String... search);
}
