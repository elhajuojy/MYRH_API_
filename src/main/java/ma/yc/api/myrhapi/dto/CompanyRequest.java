package ma.yc.api.myrhapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {
    @NotNull(message = "Company name is required")
    private String name;

    @NotNull(message = "Company address is required")
    private String address;

    @NotNull(message = "Company login is required")
    private String login;

    @NotNull(message = "Company phone number is required")
    private String phoneNumber;

    @NotNull(message = "Company password is required")
    private String password;

    @Email(message = "Email should be valid")
    private String email;

//    @NotNull(message = "Company website is required")

    private String website;

    @NotNull(message = "Company description is required")
    private String description;

    @NotNull(message = "Company image is required")
    private MultipartFile image;
}
