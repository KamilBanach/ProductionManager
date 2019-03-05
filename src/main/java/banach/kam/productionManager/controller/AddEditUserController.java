package banach.kam.productionManager.controller;

import banach.kam.productionManager.domain.AuthUserDB;
import banach.kam.productionManager.domain.enums.EAuthUserRole;
import banach.kam.productionManager.service.AuthUserService;
import banach.kam.productionManager.utils.PasswordUtils;
import banach.kam.productionManager.utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AddEditUserController implements Initializable {

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField login;
    @FXML private PasswordField password;
    @FXML private PasswordField rePassword;
    @FXML private ComboBox<EAuthUserRole> roles;
    @FXML private CheckBox active;

    private final AuthUserService authUserService;
    private final AuthManagementController authManagementController;

    @Autowired
    public AddEditUserController(AuthUserService authUserService, AuthManagementController authManagementController) {
        this.authUserService = authUserService;
        this.authManagementController = authManagementController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roles.setItems(FXCollections.observableArrayList(EAuthUserRole.values()));
    }

    @FXML
    public void save() {
        if (validatePassword()) {
            AuthUserDB authUserDB = getEntityToSave();
            authUserService.saveOrUpdate(authUserDB);
            authManagementController.getAuthUserTable().getItems().add(authUserDB);
            authManagementController.getAuthUserTable().refresh();
            close();
        } else {
            showWrongPasswordAlert();
        }
    }

    private void showWrongPasswordAlert() {
        String title = "Błąd";
        String header = "Nieprawidłowe hasło";
        String content = "Wprowadź prawidłowe hasło";
        Alert alert = ViewUtils.createAlert(title, header, content, Alert.AlertType.ERROR);
        alert.showAndWait();
    }

    private boolean validatePassword() {
        return password.getText().equals(rePassword.getText());
    }

    private AuthUserDB getEntityToSave() {
        String salt = PasswordUtils.getSalt(30);
        return AuthUserDB.builder()
                .firstName(firstName.getText())
                .lastName(lastName.getText())
                .login(login.getText())
                .password(PasswordUtils.generateSecurePassword(password.getText(), salt))
                .role(roles.getValue())
                .active(active.isSelected())
                .salt(salt)
                .build();
    }

    @FXML
    public void close() {
        ((Stage) firstName.getScene().getWindow()).close();
    }
}
