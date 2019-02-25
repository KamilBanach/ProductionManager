package banach.kam.productionManager.service;

import banach.kam.productionManager.domain.AuthUserDB;

import java.util.List;

public interface LoginService {

    boolean checkUserCredentials(String login, String password);

    List<AuthUserDB> findAll();
}
