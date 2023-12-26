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
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String resume;
    private String coverLetter;
    private String address;
    private String education;
    private String experience;

    @ManyToMany
    @JoinTable(
        name = "JOB_APPLICATION",
        joinColumns = @JoinColumn(name = "application_id"),
        inverseJoinColumns = @JoinColumn(name = "job_offer_id")
    )
    private List<JobOffer> jobOffers = new ArrayList<>();
}
