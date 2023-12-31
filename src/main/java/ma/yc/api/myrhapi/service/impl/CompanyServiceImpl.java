package ma.yc.api.myrhapi.service.impl;

import com.github.javafaker.Faker;
import jakarta.activation.FileDataSource;
import ma.yc.api.common.exception.business.NotFoundException;
import ma.yc.api.myrhapi.dto.CompanyRequest;
import ma.yc.api.myrhapi.dto.CompanyResponse;
import ma.yc.api.myrhapi.dto.JobOfferResponse;
import ma.yc.api.myrhapi.entity.Company;
import ma.yc.api.myrhapi.entity.ValidationCode;
import ma.yc.api.myrhapi.mappers.CompanyMapper;
import ma.yc.api.myrhapi.repository.CompanyRepository;
import ma.yc.api.myrhapi.repository.ValidationCodeRepository;
import ma.yc.api.myrhapi.service.CompanyService;
import ma.yc.api.myrhapi.service.EmailService;
import ma.yc.api.myrhapi.utils.FileUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyShiftBuilder;
    private final ValidationCodeRepository validationCodeRepository;


    //: inject email provider
    private final EmailService emailService;
    private final Faker faker;

    @Value("${folder.path}")
    private String FOLDER_PATH;
    Logger logger = org.slf4j.LoggerFactory.getLogger(CompanyServiceImpl.class);
    public CompanyServiceImpl(CompanyRepository companyRepository, ValidationCodeRepository validationCodeRepository, EmailService emailService) {
        this.companyRepository = companyRepository;
        this.validationCodeRepository = validationCodeRepository;
        this.emailService = emailService;
        this.companyShiftBuilder = CompanyMapper.INSTANCE;
        this.faker = new Faker();
    }

    @Override
    public List<CompanyResponse> getAll() {
        return companyRepository.findAll().stream()
                .map(companyShiftBuilder::toResponse)
                .collect(Collectors.toList());

    }

    @Override
    public CompanyResponse add(CompanyRequest companyRequest) {
        logger.info("ADD NEW COMPANY WITH THIS INFORMATION : " + companyRequest.toString());
        //SEND EMAIL TO ADMIN TO VALIDATE THE COMPANY
        this.companyRepository.getCompanyByLogin(companyRequest.getLogin()).ifPresent(
                company -> {
                    throw new RuntimeException("login already exist");
                }
        );

        Company company = companyShiftBuilder.toEntity(companyRequest);
        company.setEnabled(false);
        //: FIRST UPLOAD THE IMAGE TO THE SERVER THEN SAVE THE PATH IN THE DATABASE

//        company.setImagePath(FileUtils.uploadFileToFileSystem(companyRequest.getImage()));
        company = companyRepository.save(company);

        //  : validation valable 3 min pour la confirmation de son inscription via un email/sms
        this.sendCodeValidation(company.getEmail());


        return companyShiftBuilder.toResponse(company);
    }



    @Override
    public boolean isEnabled() {
        // TODO: Implement isEnabled logic
        throw new UnsupportedOperationException("Not yet implemented");
    }

        @Override
        public CompanyResponse authentification(String login, String password) {
            if (login.isEmpty() || password.isEmpty() || login.isBlank() || password.isBlank()) {
                throw new RuntimeException("login or password is empty");
            }


            Company company  = companyRepository.getCompanyByLogin(login).orElseThrow(
                    () -> new RuntimeException("login is incorrect")
            );

            if (!company.isEnabled()) {
                throw new RuntimeException("company login are not allowed , to enable your account make sure to verify your email");
            }
            if (company.getPassword().equals(password)) {
                return companyShiftBuilder.toResponse(company);
            }

            throw new RuntimeException("password is incorrect");
        }

    @Override
    public boolean sendCodeValidation(String email) {
        //: send code validation to email
        if (this.companyRepository.getCompanyByEmail(email).isEmpty()) {
            throw new RuntimeException("email not found");
        }
        Optional<ValidationCode> validationCode = this.validationCodeRepository.getValidationCodeByLogin(email);
         //: UPDATE THE CODE VALIDATION
        validationCode.ifPresent(this.validationCodeRepository::delete);
        logger.info("send code validation to email : " + email);
        String code = this.faker.code().asin();
        logger.info("code validation : " + code);

        // : SAVE THE CODE VALIDATION IN THE CODE_VALIDATION TABLE IN DATABASE
        this.validationCodeRepository.save(new ValidationCode(email, code));
        return this.emailService.sendEmail(email, "code validation account company",
                this.faker.lorem().sentence(10) + " : " + code
                );
    }

    @Override
    public boolean verifyCodeValidation(String login, String code) {
        Optional<Company> company = this.companyRepository.getCompanyByLogin(login);
        if (company.isEmpty()) {
            throw new RuntimeException("login not found");
        }
        if (company.get().isEnabled()) {
            throw new RuntimeException("company is already enabled");
        }
        //TODO : VERIFY THE CODE VALIDATION BY CHECKING THE CODE IN THE CODE_VALIDATION TABLE IN DATABASE
        ValidationCode validationCode = this.validationCodeRepository.getValidationCodeByLogin(company.get().getEmail()).orElseThrow(
                () -> new NotFoundException("code validation not found")
        );

        if (validationCode.getCode().equals(code)) {
            // VERIFY TIME IS LESS THAN 3 MIN
            LocalDateTime now = LocalDateTime.now();
            if (now.isAfter(validationCode.getSendAt().plusMinutes(3))) {
                logger.info("code validation is expired");
                throw new RuntimeException("code validation is expired");
            }
            logger.info("code validation is valid");
            company.get().setEnabled(true);
            this.companyRepository.save(company.get());
            return true;
        }

        return false;
    }


}
