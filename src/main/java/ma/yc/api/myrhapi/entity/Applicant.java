package ma.yc.api.myrhapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data

public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "JOB_APPLICATION",
        joinColumns = @JoinColumn(name = "applicant_id"),
        inverseJoinColumns = @JoinColumn(name = "job_offer_id")
    )
    private List<JobOffer> jobOffers = new ArrayList<>();
}
