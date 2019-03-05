package banach.kam.productionManager.domain;

import banach.kam.productionManager.domain.enums.EAuthUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pm_auth")
public class AuthUserDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Boolean active;
    private String salt;

    @Enumerated(EnumType.STRING)
    private EAuthUserRole role;
}
