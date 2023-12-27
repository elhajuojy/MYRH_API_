package ma.yc.api.myrhapi.service;

import ma.yc.api.myrhapi.dto.ApplicantRequest;
import ma.yc.api.myrhapi.dto.ApplicantResponse;

public interface ApplicantService {


    public ApplicantResponse register(ApplicantRequest applicantRequest);
    public ApplicantResponse update(ApplicantRequest applicantRequest);
    public ApplicantResponse findById(Long id);
    public void delete(Long id);
}
