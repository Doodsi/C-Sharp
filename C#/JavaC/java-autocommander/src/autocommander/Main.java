package autocommander;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400); // LOGIN: 600, 400 ------- MAIN: 880, 680
        stage.setTitle("RD's AutoCommander 2022");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
	}
	
	public static void main(String[] args) {
		System.out.println("Starting AutoCommander...\nAutoCommander is running!");
		launch(args);
		System.out.println("AutoCommander closed successfully!");
	}

}
