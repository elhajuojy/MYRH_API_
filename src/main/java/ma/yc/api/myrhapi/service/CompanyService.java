package ma.yc.api.myrhapi.service;

import ma.yc.api.myrhapi.dto.CompanyRequest;
import ma.yc.api.myrhapi.dto.CompanyResponse;
import ma.yc.api.myrhapi.entity.Company;

import java.util.List;

public interface CompanyService extends CompanyServiceAuth {

    List<CompanyResponse> getAll();

    CompanyResponse add(CompanyRequest companyRequest);
}
