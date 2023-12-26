package ma.yc.api.myrhapi.controller;

import io.micrometer.core.annotation.Timed;
import ma.yc.api.myrhapi.dto.CompanyRequest;
import ma.yc.api.myrhapi.dto.CompanyRequestAuth;
import ma.yc.api.myrhapi.dto.CompanyResponse;
import ma.yc.api.myrhapi.service.CompanyService;
import ma.yc.api.myrhapi.dto.CompanyRequest;
import ma.yc.api.myrhapi.dto.CompanyResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return companyService.getAll();
    }

    @PostMapping()
    @Timed
    public CompanyResponse addNewCompany(@ModelAttribute @Validated CompanyRequest companyRequest) {
        return companyService.add(companyRequest);
    }

    @PostMapping("/auth")
    public CompanyResponse authentication(@RequestBody @Validated CompanyRequestAuth companyRequestAuth) {
        return companyService.authentification(companyRequestAuth.getLogin(), companyRequestAuth.getPassword());
    }


    @GetMapping("/sendCodeValidation/{email}")
    public boolean sendCodeValidation(@PathVariable String email) {
        return companyService.sendCodeValidation(email);
    }

    @GetMapping("/verifyCodeValidation/{login}/{code}")
    public boolean verifyCodeValidation(@PathVariable String login, @PathVariable String code) {
        return companyService.verifyCodeValidation(login, code);
    }


}