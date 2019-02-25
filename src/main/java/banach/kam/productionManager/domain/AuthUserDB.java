package banach.kam.productionManager.domain;

import banach.kam.productionManager.domain.enums.EAuthUserRoles;
import lombok.Data;

import javax.persistence.*;

@Data
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

    @Enumerated(EnumType.STRING)
    private EAuthUserRoles role;
}
