package ma.yc.api.myrhapi.mappers;

import ma.yc.api.myrhapi.dto.ApplicantRequest;
import ma.yc.api.myrhapi.dto.ApplicantResponse;
import ma.yc.api.myrhapi.dto.JobApplicationRequest;
import ma.yc.api.myrhapi.entity.Applicant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApplicantMapper {

    ApplicantMapper INSTANCE  = Mappers.getMapper(ApplicantMapper.class);
    public Applicant toEntity(ApplicantRequest applicantRequest);

    @Mappings(
            {
                    @Mapping(source = "phone", target = "phoneNumber"),
            }
    )
    public Applicant toEntityFromJobApplicationRequest(JobApplicationRequest jobApplicationRequest);
    public ApplicantRequest toRequest(Applicant applicant);

    public ApplicantResponse toResponse(Applicant applicant);



}
