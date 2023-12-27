package ma.yc.api.myrhapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantResponse {
    private Long id;
    private String title;
    private String description;
    private MultipartFile resume;
    private String phone;
}
