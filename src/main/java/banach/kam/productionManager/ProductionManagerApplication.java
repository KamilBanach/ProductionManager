package banach.kam.productionManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class ProductionManagerApplication extends Application {

	private ConfigurableApplicationContext springContext;
	private Parent rootNode;
	private FXMLLoader fxmlLoader;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		fxmlLoader.setLocation(getClass().getResource("/views/Login.fxml"));
		rootNode = fxmlLoader.load();
        stage.setTitle("Logowanie");
        Scene scene = new Scene(rootNode, 700, 400);
        stage.setScene(scene);
        stage.show();
		//LoginView loginView = new LoginView(stage, rootNode);
        //loginView.setVisible(true);
	}

	@Override
	public void stop() {
		springContext.stop();
	}

	@Override
	public void init() {
		springContext = SpringApplication.run(ProductionManagerApplication.class);
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(springContext::getBean);
	}
}

