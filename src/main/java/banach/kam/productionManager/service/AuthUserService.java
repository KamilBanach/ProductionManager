package banach.kam.productionManager.service;

import banach.kam.productionManager.domain.AuthUserDB;
import javafx.collections.ObservableList;

public interface AuthUserService {

    ObservableList<AuthUserDB> findAll();

    void saveOrUpdate(AuthUserDB entityToSave);

    void deleteById(Integer id);
}
