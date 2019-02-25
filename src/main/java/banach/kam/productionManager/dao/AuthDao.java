package banach.kam.productionManager.dao;

import banach.kam.productionManager.domain.AuthUserDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthDao extends JpaRepository<AuthUserDB, Integer> {
}
