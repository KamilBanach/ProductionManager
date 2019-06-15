package banach.kam.productionManager.utils;

import banach.kam.productionManager.domain.enums.EView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class ViewUtils {

    public static Stage createDialog(EView view, Window owner) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(getContext()::getBean);
        loader.setLocation(getResource(view.getPath()));
        loader.setResources(ResourceProvider.getLocalizationBundle());
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initOwner(owner);
        return stage;
    }

    public static Alert createAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }

    private static ApplicationContext getContext() {
        return ApplicationContextProvider.getApplicationContext();
    }

    private static URL getResource(String path) {
        return ViewUtils.class.getResource(path);
    }

    public static Alert createConfirmAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        return alert;
    }
}
