package ma.yc.api.myrhapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data

public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String coverLetterPath;
    private String resumePath;
    private String address;
    private String education;
    private String password;
    private String experience;
    private String imagePath;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "JOB_APPLICATION",
        joinColumns = @JoinColumn(name = "applicant_id"),
        inverseJoinColumns = @JoinColumn(name = "job_offer_id")
    )
    private List<JobOffer> jobOffers = new ArrayList<>();
}
