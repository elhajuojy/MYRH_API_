package ma.yc.api.myrhapi.entity;

import jakarta.persistence.*;
import lombok.*;
import ma.yc.api.myrhapi.enums.Contract;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "JOB_OFFER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Double salary;
    private String location;
    @Enumerated(EnumType.STRING)
    private Contract contract = Contract.CDI;
    private boolean visibility;
    private String education;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @ManyToMany
    @JoinTable(
        name = "JOB_APPLICATION",
        joinColumns = @JoinColumn(name = "job_offer_id"),
        inverseJoinColumns = @JoinColumn(name = "application_id")
    )
    private List<Applicant> applications = new ArrayList<>();


}
