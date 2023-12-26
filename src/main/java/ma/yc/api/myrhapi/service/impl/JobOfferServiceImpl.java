package ma.yc.api.myrhapi.service.impl;

import ma.yc.api.common.exception.business.NotFoundException;
import ma.yc.api.myrhapi.dto.JobOfferRequest;
import ma.yc.api.myrhapi.dto.JobOfferResponse;
import ma.yc.api.myrhapi.entity.Company;
import ma.yc.api.myrhapi.entity.JobOffer;
import ma.yc.api.myrhapi.mappers.JobMapper;
import ma.yc.api.myrhapi.repository.JobOfferRepository;
import ma.yc.api.myrhapi.service.JobOfferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final JobMapper jobOfferShiftBuilder;

    public JobOfferServiceImpl(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
        this.jobOfferShiftBuilder = JobMapper.INSTANCE;
    }

    @Override
    public JobOfferResponse addNewJobOffer(JobOfferRequest jobOfferRequest) {
        JobOffer jobOffer = jobOfferShiftBuilder.toEntity(jobOfferRequest);
        jobOffer = jobOfferRepository.save(jobOffer);

        return jobOfferShiftBuilder.toResponse(jobOffer);
    }

    @Override
    public Page<JobOfferResponse> getAllJobOffers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return jobOfferRepository.findAll(pageRequest)
                .map(jobOfferShiftBuilder::toResponse);
    }

    @Override
    public JobOfferResponse changeJobOfferVisibility(String jobOfferId, boolean visibility) {
        Long id = Long.parseLong(jobOfferId);
        JobOffer jobOffer =  this.jobOfferRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Job offer not found")
        );
        jobOffer.setVisibility(visibility);
        this.jobOfferRepository.save(jobOffer);
        return jobOfferShiftBuilder.toResponse(jobOffer);
    }
}
