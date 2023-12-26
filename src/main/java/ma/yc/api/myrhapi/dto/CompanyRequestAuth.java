package ma.yc.api.myrhapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestAuth {

    @NotNull(message = "login is required")
    private String login;

    @NotNull(message = "password is required")
    private String password;
}
