package ma.yc.api.myrhapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ValidationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @Column(unique = true)
    private String login;
    private LocalDateTime sendAt ;

    public ValidationCode(String login , String code) {
        this.login = login;
        this.code = code;
        this.sendAt = LocalDateTime.now();
    }
}
