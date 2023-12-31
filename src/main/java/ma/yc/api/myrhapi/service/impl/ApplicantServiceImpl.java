package ma.yc.api.myrhapi.service.impl;

import ma.yc.api.common.exception.business.BadRequestException;
import ma.yc.api.common.exception.business.NotFoundException;
import ma.yc.api.myrhapi.dto.ApplicantRequest;
import ma.yc.api.myrhapi.dto.ApplicantResponse;
import ma.yc.api.myrhapi.entity.Applicant;
import ma.yc.api.myrhapi.mappers.ApplicantMapper;
import ma.yc.api.myrhapi.repository.ApplicantRepository;
import ma.yc.api.myrhapi.service.ApplicantService;
import org.springframework.stereotype.Service;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;

    public ApplicantServiceImpl(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
        this.applicantMapper = ApplicantMapper.INSTANCE;
    }

    @Override
    public ApplicantResponse register(ApplicantRequest applicantRequest) {

        return this.applicantMapper.
                toResponse(this.applicantRepository.save
                        (this.applicantMapper.toEntity(applicantRequest)
                        )
                );

    }

    @Override
    public ApplicantResponse update(ApplicantRequest applicantRequest) {
        return null;
    }



    @Override
    public ApplicantResponse findById(Long id) {
        return null;
    }


    private Applicant findByEmail(String email) {
        return this.applicantRepository.findByEmail(email ).orElseThrow(
                ()-> new NotFoundException("Applicant with email "+email+" not found")

        );
    }

    @Override
    public void delete(Long id) {

    }




    @Override
    public ApplicantResponse authentication(String login, String password) {
        Applicant applicant =this.findByEmail(login);
        //hashing password
        if(applicant.getPassword().equals(password)){
            return this.applicantMapper.toResponse(applicant);
        }

        else {
            throw new BadRequestException("password is not correct");
        }


    }
}
