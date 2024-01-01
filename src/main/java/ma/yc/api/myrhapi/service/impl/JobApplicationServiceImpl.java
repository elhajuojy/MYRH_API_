package ma.yc.api.myrhapi.service.impl;

import ma.yc.api.common.exception.business.NotFoundException;
import ma.yc.api.myrhapi.dto.ApplicantResponse;
import ma.yc.api.myrhapi.dto.JobApplicationRequest;
import ma.yc.api.myrhapi.dto.JobApplicationResponse;
import ma.yc.api.myrhapi.entity.Applicant;
import ma.yc.api.myrhapi.entity.JobApplication;
import ma.yc.api.myrhapi.entity.JobOffer;
import ma.yc.api.myrhapi.mappers.ApplicantMapper;
import ma.yc.api.myrhapi.mappers.JobApplicationMapper;
import ma.yc.api.myrhapi.repository.ApplicantRepository;
import ma.yc.api.myrhapi.repository.JobApplicationRepository;
import ma.yc.api.myrhapi.repository.JobOfferRepository;
import ma.yc.api.myrhapi.service.JobApplicationService;
import ma.yc.api.myrhapi.utils.FileUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobOfferRepository jobOfferRepository;
    private final ApplicantRepository applicantRepository;
    //TODO : MAPPER HERE
    private final JobApplicationMapper jobApplicationMapper;
    private final ApplicantMapper applicantMapper;
    Logger logger = org.slf4j.LoggerFactory.getLogger(JobApplicationServiceImpl.class);
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, JobOfferRepository jobOfferRepository, ApplicantRepository applicantRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobOfferRepository = jobOfferRepository;
        this.applicantRepository = applicantRepository;
        this.jobApplicationMapper = JobApplicationMapper.INSTANCE;
        this.applicantMapper = ApplicantMapper.INSTANCE;
    }

    @Override
    public JobApplicationResponse applyToJob(JobApplicationRequest jobApplicationRequest) {
        //: IMPLEMENT THIS METHOD
        JobApplication jobApplication = jobApplicationMapper.toEntity(jobApplicationRequest);
        logger.info("JOB APPLICATION SAVED WITH ID : " + jobApplicationRequest.toString());

        //BEFORE CONVERTING TO JOB APPLICATION ENTITY I NEED TO UPLOAD THE CV TO THE CLOUD OR STATIC FOLDER IN THE SERVER
        //FIRST OF ALL NEED TO GET THE JOB_OFFER_ID AND THE APPLICANT_ID
        JobOffer jobOffer = this.jobOfferRepository.findById(jobApplicationRequest.getJobOfferId())
                .orElseThrow(() -> new RuntimeException("Job Offer Not Found"));

        Applicant applicant = this.applicantMapper.toEntityFromJobApplicationRequest(jobApplicationRequest);
        //TODO: CV UPLOADING
//        applicant.setResumePath(FileUtils.uploadFileToFileSystem(jobApplicationRequest.getResume()));
        //save this application to the database
        applicant  = this.applicantRepository.save(applicant);
        logger.info("APPLICANT SAVED WITH ID : " + applicant.getId());
        applicant.getJobOffers().add(jobOffer);
        this.applicantRepository.save(applicant);

//
//        jobApplication.setApplicant(applicant);
//        jobApplication.setJobOffer(jobOffer);
//        jobApplication.setDate(java.time.LocalDateTime.now().toString());
//        jobApplication.setStatus("PENDING");
//        jobApplication = this.jobApplicationRepository.save(jobApplication);
//        this.applicantRepository.save(applicant);
        logger.info("JOB OFFER SAVED WITH ID : " + jobOffer.getId());


        return jobApplicationMapper.toResponse(jobApplication);

    }
}
