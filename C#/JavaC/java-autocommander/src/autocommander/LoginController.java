package autocommander;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
// import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
	// labels:
	@FXML
	private Label lblUser;
	@FXML
	private Label lblPass;
	@FXML
	private Label lblMessage;
	@FXML
	private Label lblVersion;
	
	// text & pass fields:
	@FXML
	private TextField tfUsername;
	@FXML
	private PasswordField pfPass;
	
	// button:
	@FXML
	private Button btnLogin;
	
	// image views (not required whatsoever lol):
	@FXML
	private ImageView ivHeader;
	@FXML
	private ImageView ivFooter;
	
	// za prelazak na drugu scenu (main):
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	// provjera valjanosti credetialsa:
	@FXML
	void onLoginClicked(ActionEvent event) throws IOException {
		// provjeri:
		if (tfUsername.getText().equals("admin") && pfPass.getText().equals("admin")) {
			// startuj novu scenu i odma ju initialize:
			FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
			root = loader.load();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root, 880, 680);
			stage.setResizable(true);
			stage.setScene(scene);
			stage.show();
			
			MainController mainController = (MainController) loader.getController();
			try {
				mainController.initialize();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			lblMessage.setText("Invalid username and/or password! Try again.");
		}
	}
}
