package ma.yc.api.myrhapi.mappers;

import ma.yc.api.myrhapi.dto.CompanyRequest;
import ma.yc.api.myrhapi.dto.CompanyResponse;
import ma.yc.api.myrhapi.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    Company toEntity(CompanyRequest companyRequest);
    CompanyRequest toRequest(Company company);

    @Mappings(
            {
                    @org.mapstruct.Mapping(target = "image", source = "imagePath")
            }
    )
    CompanyResponse toResponse(Company company);

}
