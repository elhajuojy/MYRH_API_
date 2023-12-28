package ma.yc.api.myrhapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantRequest {
    @NotNull(message = "title is required")
    private String title;
    @NotNull(message = "description is required")
    private String description;
    @NotNull(message = "resume is required")
    private MultipartFile resume;
    @NotNull(message = "phone is required")
    private String phone;
    @NotNull(message = "email is required")
    private String email;
    @NotNull(message = "address is required")
    private String address;
    @NotNull(message = "education is required")
    private String education;
    @NotNull(message = "experience is required")
    private String experience;
    private MultipartFile coverLetter;


}
