package ma.yc.api.myrhapi.mappers;

import ma.yc.api.myrhapi.dto.JobApplicationRequest;
import ma.yc.api.myrhapi.dto.JobApplicationResponse;
import ma.yc.api.myrhapi.entity.JobApplication;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobApplicationMapper {
    JobApplicationMapper INSTANCE = Mappers.getMapper(JobApplicationMapper.class);

    JobApplication toEntity(JobApplicationRequest jobApplicationRequest);
    JobApplicationResponse toResponse(JobApplication jobApplication);




}
