package ma.yc.api.myrhapi.service.impl;

import ma.yc.api.common.exception.business.NotFoundException;
import ma.yc.api.myrhapi.dto.JobOfferRequest;
import ma.yc.api.myrhapi.dto.JobOfferResponse;
import ma.yc.api.myrhapi.entity.Company;
import ma.yc.api.myrhapi.entity.JobOffer;
import ma.yc.api.myrhapi.mappers.JobMapper;
import ma.yc.api.myrhapi.repository.JobOfferRepository;
import ma.yc.api.myrhapi.service.EmailService;
import ma.yc.api.myrhapi.service.JobOfferService;
import ma.yc.api.myrhapi.specifications.JobOfferSpecifications;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final JobMapper jobOfferShiftBuilder;


    private final EmailService emailService;
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(JobOfferServiceImpl.class);

    public JobOfferServiceImpl(JobOfferRepository jobOfferRepository,EmailService emailService) {
        this.jobOfferRepository = jobOfferRepository;
        this.emailService = emailService;
        this.jobOfferShiftBuilder = JobMapper.INSTANCE;
    }

    @Override
    public JobOfferResponse addNewJobOffer(JobOfferRequest jobOfferRequest) {
        JobOffer jobOffer = jobOfferShiftBuilder.toEntity(jobOfferRequest);
        jobOffer = jobOfferRepository.save(jobOffer);

        return jobOfferShiftBuilder.toResponse(jobOffer);
    }

    @Override
    public Page<JobOfferResponse> getAllJobOffers(int page, int size, String title, String education) {
        return null;
    }

    @Override
    public Page<JobOfferResponse> getAllJobOffers(Map<String,String> queryParams) {
        int page = Integer.parseInt(queryParams.getOrDefault("page","0"));
        int size = Integer.parseInt(queryParams.getOrDefault("size","10"));

        //: get the query params
        String title = queryParams.getOrDefault("title","");
        String education = queryParams.getOrDefault("education","");
        String location = queryParams.getOrDefault("location","");
        logger.info("QUERY PARAMS" + queryParams.toString());
        //: build the page request
        PageRequest pageRequest = PageRequest.of(page, size);
        Specification<JobOffer> spec = Specification.where(null);

        if (title != null && !title.isEmpty())
            spec = spec.and(JobOfferSpecifications.titleLike(title));
        if (education != null && !education.isEmpty())
            spec = spec.and(JobOfferSpecifications.educationLike(education));
        if (location != null && !location.isEmpty())
            spec = spec.and(JobOfferSpecifications.locationLike(location));

        logger.info("FIND BY SPECIFICATION" + spec.toString());


        return jobOfferRepository.findAll(spec,pageRequest)
                .map(jobOfferShiftBuilder::toResponse);
    }

    @Override
    public JobOfferResponse changeJobOfferVisibility(String jobOfferId, boolean visibility) {
        Integer id = Integer.parseInt(jobOfferId);
        JobOffer jobOffer =  this.jobOfferRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Job offer not found")
        );
        jobOffer.setVisibility(visibility);
        this.jobOfferRepository.save(jobOffer);

        //: SEND EMAIL TO COMPANY TO INFORM HIM THAT HIS JOB OFFER IS NOW VISIBLE
        this.emailService.sendEmail(jobOffer.getCompany().getEmail(),
                "Job offer visibility changed",
                "Your job offer visibility has been changed to "+
                        (visibility ?"visible":"invisible"));

        return jobOfferShiftBuilder.toResponse(jobOffer);
    }


    @Override
    public JobOfferResponse getJobOfferById(Integer id) {
        return this.jobOfferRepository.findById(id)
                .map(jobOfferShiftBuilder::toResponse)
                .orElseThrow(
                        () -> new NotFoundException("Job offer not found")
                );
    }
}
