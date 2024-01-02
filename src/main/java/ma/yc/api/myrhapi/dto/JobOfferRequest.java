package ma.yc.api.myrhapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.yc.api.myrhapi.enums.Contract;

@Data
@AllArgsConstructor
public class JobOfferRequest {

    private  String title;
    private  String description;
    private  double salary;
    private  String location;
    private  Contract contract;
    private  String education;
    private String company_id;

}
