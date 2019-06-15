package banach.kam.productionManager.controller;

import banach.kam.productionManager.domain.AuthUserDB;
import banach.kam.productionManager.domain.enums.EAuthUserRole;
import banach.kam.productionManager.domain.enums.EView;
import banach.kam.productionManager.service.AuthUserService;
import banach.kam.productionManager.utils.I18nUtils;
import banach.kam.productionManager.utils.ViewUtils;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
@Slf4j
public class AuthManagementController implements Initializable {

    @FXML private TableView<AuthUserDB> authUserTable;
    @FXML private TableColumn<AuthUserDB, Integer> id;
    @FXML private TableColumn<AuthUserDB, String> firstName;
    @FXML private TableColumn<AuthUserDB, String> lastName;
    @FXML private TableColumn<AuthUserDB, String> login;
    @FXML private TableColumn<AuthUserDB, EAuthUserRole> role;
    @FXML private TableColumn<AuthUserDB, Boolean> active;

    private final AuthUserService authUserService;
    private boolean editMode;

    @Autowired
    public AuthManagementController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        active.setCellValueFactory(obj -> {
            SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(obj.getValue().getActive());
            booleanProperty.addListener((observableValue, val, newVal) -> obj.getValue().setActive(newVal));
            return booleanProperty;
        });
        active.setCellFactory(c -> new CheckBoxTableCell<>());
        authUserTable.setItems(authUserService.findAll());
    }

    @FXML
    public void addNewUser() {
        editMode = false;
        createAndShowUsersDialog();
    }

    @FXML
    public void editUser() {
        if (authUserTable.getSelectionModel().isEmpty()) {
            String title = I18nUtils.getLabel("dialog.user.edit.fail.title");
            String content = I18nUtils.getLabel("dialog.user.edit.fail.content");
            ViewUtils.createAlert(title, null, content, Alert.AlertType.WARNING);
        } else {
            editMode = true;
            createAndShowUsersDialog();
        }
    }

    @FXML
    public void refreshTable() {
        authUserTable.setItems(authUserService.findAll());
        authUserTable.refresh();
    }

    @FXML
    public void close() {
        ((Stage) authUserTable.getScene().getWindow()).close();
    }

    public TableView<AuthUserDB> getAuthUserTable() {
        return authUserTable;
    }

    public boolean isEditMode() {
        return editMode;
    }

    private void createAndShowUsersDialog() {
        Stage usersDialog;
        try {
            usersDialog = ViewUtils.createDialog(EView.ADD_EDIT_USER, authUserTable.getScene().getWindow());
        } catch (IOException ex) {
            log.error("View create error " + ex.getMessage());
            return;
        }
        usersDialog.show();
    }
}
