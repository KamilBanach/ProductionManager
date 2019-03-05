package banach.kam.productionManager.service.impl;

import banach.kam.productionManager.dao.AuthDao;
import banach.kam.productionManager.domain.AuthUserDB;
import banach.kam.productionManager.service.AuthUserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthDao authDao;

    @Autowired
    public AuthUserServiceImpl(AuthDao authDao) {
        this.authDao = authDao;
    }

    @Override
    public ObservableList<AuthUserDB> findAll() {
        return FXCollections.observableArrayList(authDao.findAll());
    }

    @Override
    public void saveOrUpdate(AuthUserDB entityToSave) {
        authDao.save(entityToSave);
    }

    @Override
    public void deleteById(Integer id) {
        authDao.deleteById(id);
    }
}
