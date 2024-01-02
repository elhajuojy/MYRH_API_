package ma.yc.api.myrhapi.service.impl;

import jakarta.transaction.Transactional;
import ma.yc.api.common.exception.business.NotFoundException;
import ma.yc.api.myrhapi.dto.JobOfferApplicationsPageResponse;
import ma.yc.api.myrhapi.dto.JobOfferRequest;
import ma.yc.api.myrhapi.dto.JobOfferResponse;
import ma.yc.api.myrhapi.entity.JobApplication;
import ma.yc.api.myrhapi.entity.JobOffer;
import ma.yc.api.myrhapi.mappers.JobMapper;
import ma.yc.api.myrhapi.repository.CompanyRepository;
import ma.yc.api.myrhapi.repository.JobApplicationRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final JobMapper jobOfferShiftBuilder;
    private final EmailService emailService;
    private final CompanyRepository companyRepository;
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(JobOfferServiceImpl.class);

    public JobOfferServiceImpl(JobOfferRepository jobOfferRepository, JobApplicationRepository jobApplicationRepository, EmailService emailService, CompanyRepository companyRepository) {
        this.jobOfferRepository = jobOfferRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.emailService = emailService;
        this.companyRepository = companyRepository;
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

        logger.info("QUERY PARAMS" + queryParams.toString());
        //: build the page request
        PageRequest pageRequest = PageRequest.of(page, size);

        Specification<JobOffer> spec = getJobOfferSpecification(queryParams);

        return jobOfferRepository.findAll(spec,pageRequest)
                .map(jobOfferShiftBuilder::toResponse);
    }

    private Specification<JobOffer> getJobOfferSpecification(Map<String, String> queryParams) {

        Specification<JobOffer> spec = Specification.where(null);
        String title = queryParams.getOrDefault("title","");
        String education = queryParams.getOrDefault("education","");
        String location = queryParams.getOrDefault("location","");
        String visibility = queryParams.getOrDefault("visibility","");

        if (title != null && !title.isEmpty())
            spec = spec.and(JobOfferSpecifications.titleLike(title));
        if (education != null && !education.isEmpty())
            spec = spec.and(JobOfferSpecifications.educationLike(education));
        if (location != null && !location.isEmpty())
            spec = spec.and(JobOfferSpecifications.locationLike(location));
        if (visibility != null && !visibility.isEmpty())
            spec = spec.and(JobOfferSpecifications.visibilityIs(visibility));

        logger.info("FIND BY SPECIFICATION" + spec.toString());
        return spec;
    }

    @Override
    public JobOfferResponse changeJobOfferVisibility(String jobOfferId, boolean visibility) {
        Long id = Long.parseLong(jobOfferId);
        JobOffer jobOffer =  this.jobOfferRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Job offer not found")
        );
        jobOffer.setVisibility(visibility);
        this.jobOfferRepository.save(jobOffer);

        //: SEND EMAIL TO COMPANY TO INFORM HIM THAT HIS JOB OFFER IS NOW VISIBLE
//        jobOffer.getCompany().getEmail()
        this.emailService.sendEmail("company-email@egmail.com",
                "Job offer visibility changed"+jobOffer.getTitle(),
                "Your job offer visibility has been changed to "+ jobOffer.getTitle() + " to " +
                        (visibility ?"visible":"invisible"));

        return jobOfferShiftBuilder.toResponse(jobOffer);
    }


    @Override
    public JobOfferResponse getJobOfferById(Long id) {
        return this.jobOfferRepository.findById(id)
                .map(jobOfferShiftBuilder::toResponse)
                .orElseThrow(
                        () -> new NotFoundException("Job offer not found")
                );
    }

    @Override
    public Page<JobOfferResponse> getJobOffersByCompanyId(Long id, Map<String, String> queryParams) {
        return
                this.jobOfferRepository.findAllByCompanyId(id,PageRequest.of(
                        Integer.parseInt(queryParams.getOrDefault("page","0")),
                        Integer.parseInt(queryParams.getOrDefault("size","10"))
                )).map(jobOfferShiftBuilder::toResponse);
    }

    @Override
    public List<JobOfferApplicationsPageResponse> getJobOfferApplicationByJobOfferIdAndCompanyId(
            String id, String jobOfferId, Map<String, String> queryParams) {
        //FIND ALL JOB OFFER RELATED TO THE COMPANY FOR THE BETWEEN TABLE JOB APPLICATION


        JobOffer jobOffer = this.jobOfferRepository.findById(Long.parseLong(jobOfferId)).orElseThrow(
                () -> new NotFoundException("Job offer not found")
        );

        List<JobApplication> jobApplications = this.jobApplicationRepository.findAllByJobOffer(jobOffer);
        //:MAPPING TO DTO
        List<JobOfferApplicationsPageResponse> jobOfferApplicationsPageResponses  = new ArrayList<>();
        jobApplications.forEach(jobApplication -> {
            logger.info("JOB APPLICATION" + jobApplication.getStatus());
            //TODO: MAPPING TO DTO THEN RETURN THE LIST
            jobOfferApplicationsPageResponses.add(
                    JobOfferApplicationsPageResponse.builder()
                            //TODO: ID IS THE JOB OFFER ID
                            .id(jobApplication.getJobOffer().getId().toString())
                            .cvUrl(jobApplication.getApplicant().getResumePath())
                            .email(jobApplication.getApplicant().getEmail())
                            .fullName(jobApplication.getApplicant().getFirstName()+" "+jobApplication.getApplicant().getLastName())
                            .phoneNumber(jobApplication.getApplicant().getPhoneNumber())
                            .build()
            );
        });
        return jobOfferApplicationsPageResponses;
    }
}
