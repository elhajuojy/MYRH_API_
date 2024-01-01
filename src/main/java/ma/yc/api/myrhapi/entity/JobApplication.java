package ma.yc.api.myrhapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Entity
@Table(name = "JOB_APPLICATION")
@Data
public class JobApplication {



    @EmbeddedId
    private JobApplicationId jobApplicationId;
    private String status = "PENDING";

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "DATE")
    private String date = java.time.LocalDateTime.now().toString();

    @ManyToOne()
    @MapsId("applicantId")
    private Applicant applicant;
    @ManyToOne()
    @MapsId("jobOfferId")
    private JobOffer jobOffer;
}
