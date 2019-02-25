package banach.kam.productionManager.view;

import banach.kam.productionManager.service.LoginService;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LoginView {

    private Stage stage;
    private Parent parent;

    //@Autowired
    private LoginService loginService;

    public LoginView(Stage stage, Parent parent) {
        this.stage = stage;
        this.parent = parent;
        init();
    }

    private void init() {
        stage.setTitle("Logowanie");
        Scene scene = new Scene(parent, 700, 400);
        stage.setScene(scene);
    }

    public void setVisible(boolean shouldVisible) {
        if (shouldVisible)
            stage.show();
        else stage.close();
    }

}
