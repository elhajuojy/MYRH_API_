package ma.yc.api.myrhapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobOfferApplicationsPageResponse {
    private String id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String cvUrl;
}
