package ma.yc.api.myrhapi.dto;

import lombok.Data;

@Data
public class JobOfferChangeVisibilityRequest {
    String jobOfferId;
    boolean visibility;
}
