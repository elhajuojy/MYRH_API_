package ma.yc.api.myrhapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.yc.api.myrhapi.enums.Contract;

@Data
@AllArgsConstructor
public class JobOfferRequest {

    private final String title;
    private final String description;
    private final double salary;
    private final String location;
    private final Contract contract;
    private final String education;
}
