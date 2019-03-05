package banach.kam.productionManager.domain.enums;

import lombok.Getter;

@Getter
public enum EView {
    LOGIN("/views/login.fxml"),
    USERS("/views/users.fxml"),
    ADD_EDIT_USER("/views/addEditUser.fxml");

    private String path;

    EView(String path) {
        this.path = path;
    }
}