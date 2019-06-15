package banach.kam.productionManager.controller;

import banach.kam.productionManager.domain.AuthUserDB;
import banach.kam.productionManager.domain.enums.EView;
import banach.kam.productionManager.service.LoginService;
import banach.kam.productionManager.utils.I18nUtils;
import banach.kam.productionManager.utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class LoginController {

    private Parent usersPane ;
    private Stage usersDialog ;

    @FXML private TextField user;
    @FXML private PasswordField password;
    @FXML private Button usersButton;

    @Autowired
    private LoginService loginService;

    @FXML
    public void login(ActionEvent event) {
        boolean loginSuccess = loginService.checkUserCredentials(user.getText(), password.getText());
        List<AuthUserDB> list = loginService.findAll();
        if (loginSuccess)
            System.out.println("zalogowano");
        else
            showBadCredentialsAlert();
    }

    private void showBadCredentialsAlert() {
        String title = I18nUtils.getLabel("alert.error.title");
        String content = I18nUtils.getLabel("dialog.login.fail.content");
        Alert alert = ViewUtils.createAlert(title, null, content, Alert.AlertType.ERROR);
        alert.show();
    }

    @FXML
    public void exit() {
        ((Stage) usersButton.getScene().getWindow()).close();
    }

    @FXML
    public void showUserManagementDialog() throws IOException {
        Stage usersDialog = ViewUtils.createDialog(EView.USERS, usersButton.getScene().getWindow());
        usersDialog.show();
    }

    public void setUsersButtonVisibility(boolean shouldVisible) {
        usersButton.setVisible(shouldVisible);
    }
}
