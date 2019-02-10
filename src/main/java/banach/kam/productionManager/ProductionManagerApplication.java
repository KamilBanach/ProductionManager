package banach.kam.productionManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductionManagerApplication extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setScene(new Scene(new Pane(), 800, 600));
		stage.show();
	}

	@Override
	public void init() throws Exception {
		SpringApplication.run(ProductionManagerApplication.class);
	}
}

