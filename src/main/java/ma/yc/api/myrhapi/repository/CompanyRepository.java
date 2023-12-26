package ma.yc.api.myrhapi.repository;

import ma.yc.api.myrhapi.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> getCompanyByLogin(String login);
    Optional<Company> getCompanyByEmail(String email);
}
