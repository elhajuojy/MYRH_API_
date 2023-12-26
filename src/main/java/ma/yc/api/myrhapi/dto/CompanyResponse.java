package ma.yc.api.myrhapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String login;

    @NotNull
    private String phoneNumber;

    @Email
    private String email;

    @NotNull
    private String website;

    @NotNull
    private String description;

    @NotNull
    private String image;
}
