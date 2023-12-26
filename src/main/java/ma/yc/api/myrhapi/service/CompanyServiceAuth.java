package ma.yc.api.myrhapi.service;

import ma.yc.api.myrhapi.dto.CompanyRequest;
import ma.yc.api.myrhapi.dto.CompanyResponse;

public interface CompanyServiceAuth  {
    
    boolean isEnabled();
    
    CompanyResponse authentification(String login, String password);
    boolean sendCodeValidation(String login);
    boolean verifyCodeValidation(String login, String code);



}
