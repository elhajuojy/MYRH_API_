package ma.yc.api.myrhapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String login;
    private String password;
    private String email;
    private String website;
    private String description;
    private String imagePath;
    private boolean enabled = false;

    @OneToMany(mappedBy = "company")
    private List<JobOffer> jobOffers = new ArrayList<>();
}
