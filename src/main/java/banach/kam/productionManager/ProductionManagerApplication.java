package banach.kam.productionManager;

import banach.kam.productionManager.controller.LoginController;
import banach.kam.productionManager.utils.ApplicationContextProvider;
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
	private static String[] args;

	public static void main(String[] args) {
		ProductionManagerApplication.args = args;
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		fxmlLoader.setLocation(getClass().getResource("/views/login.fxml"));
		rootNode = fxmlLoader.load();
        stage.setTitle("Logowanie");
        Scene scene = new Scene(rootNode, 700, 400);
        stage.setScene(scene);
        setComponentsVisibility(args.length > 0 && args[0].equals("admin"));
        stage.show();
	}

	@Override
	public void stop() {
		springContext.stop();
	}

	@Override
	public void init() {
		springContext = SpringApplication.run(ProductionManagerApplication.class);
		ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();
		applicationContextProvider.setApplicationContext(springContext);
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(springContext::getBean);
	}

	private void setComponentsVisibility(boolean visible) {
		((LoginController) fxmlLoader.getController()).setUsersButtonVisibility(visible);
	}
}

