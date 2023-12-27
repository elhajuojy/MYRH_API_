package ma.yc.api.myrhapi.mappers;

import ma.yc.api.myrhapi.dto.ApplicantRequest;
import ma.yc.api.myrhapi.dto.ApplicantResponse;
import ma.yc.api.myrhapi.entity.Applicant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApplicantMapper {

    ApplicantMapper INSTANCE  = Mappers.getMapper(ApplicantMapper.class);
    public Applicant toEntity(ApplicantRequest applicantRequest);
    public ApplicantRequest toRequest(Applicant applicant);

    public ApplicantResponse toResponse(Applicant applicant);


}