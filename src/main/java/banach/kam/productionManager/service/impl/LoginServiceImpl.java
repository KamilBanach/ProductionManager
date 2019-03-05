package banach.kam.productionManager.service.impl;

import banach.kam.productionManager.dao.AuthDao;
import banach.kam.productionManager.domain.AuthUserDB;
import banach.kam.productionManager.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    private final AuthDao authDao;

    @Autowired
    public LoginServiceImpl(AuthDao authDao) {
        this.authDao = authDao;
    }

    @Override
    public boolean checkUserCredentials(String login, String password) {
        return false;
    }

    @Override
    public List<AuthUserDB> findAll() {
        return authDao.findAll();
    }
}
