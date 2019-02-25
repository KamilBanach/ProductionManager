package banach.kam.productionManager.controller;

import banach.kam.productionManager.domain.AuthUserDB;
import banach.kam.productionManager.service.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LoginController {

    @FXML private TextField user;
    @FXML private PasswordField password;
    @FXML private Button closeButton;

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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Błąd");
        alert.setHeaderText("Nieprawidłowa nazwa użytkownika lub hasło");
        alert.setContentText("Wprowadź prawidłowe dane logowania");
        alert.showAndWait();
    }

    @FXML
    public void exit() {
        ((Stage) closeButton.getScene().getWindow()).close();
    }

}
