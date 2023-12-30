package ma.yc.api.myrhapi.entity;

import jakarta.persistence.*;
import lombok.*;
import ma.yc.api.myrhapi.enums.Contract;

import java.time.LocalDate;
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
    private Integer id;
    private String title;
    private String description;
    private Double salary;
    private String location;
    @Enumerated(EnumType.STRING)
    private Contract contract = Contract.CDI;
    private boolean visibility;
    private String education;
    @Column(name = "CREATION_DATE")

    private LocalDate creationDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "JOB_APPLICATION",
        joinColumns = @JoinColumn(name = "job_offer_id"),
        inverseJoinColumns = @JoinColumn(name = "applicant_id")
    )
    private List<Applicant> applications = new ArrayList<>();


}
