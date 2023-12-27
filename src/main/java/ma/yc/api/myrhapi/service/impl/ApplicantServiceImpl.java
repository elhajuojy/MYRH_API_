package ma.yc.api.myrhapi.service.impl;

import ma.yc.api.myrhapi.dto.ApplicantRequest;
import ma.yc.api.myrhapi.dto.ApplicantResponse;
import ma.yc.api.myrhapi.mappers.ApplicantMapper;
import ma.yc.api.myrhapi.repository.ApplicantRepository;
import ma.yc.api.myrhapi.service.ApplicantService;

public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;
    private final

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

    @Override
    public void delete(Long id) {

    }
}
