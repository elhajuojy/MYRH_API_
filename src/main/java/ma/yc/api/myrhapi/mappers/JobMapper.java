package ma.yc.api.myrhapi.mappers;

import ma.yc.api.myrhapi.dto.JobOfferRequest;
import ma.yc.api.myrhapi.dto.JobOfferResponse;
import ma.yc.api.myrhapi.entity.JobOffer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobMapper {
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    JobOffer toEntity(JobOfferRequest jobOfferRequest);

    JobOfferRequest toRequest(JobOffer jobOffer);

    JobOfferResponse toResponse(JobOffer jobOffer);
}