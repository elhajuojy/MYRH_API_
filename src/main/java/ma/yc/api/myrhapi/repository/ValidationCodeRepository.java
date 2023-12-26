package ma.yc.api.myrhapi.repository;

import ma.yc.api.myrhapi.entity.ValidationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValidationCodeRepository extends JpaRepository<ValidationCode,Long>{
    Optional<ValidationCode> getValidationCodeByLogin(String login);
}
