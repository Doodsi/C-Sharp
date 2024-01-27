package autocommander;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

// importiranje objekata i baza:
import objects.Auto;
import database.AutoDatabase;

public class MainController {
	// panes:
	@FXML
	private Pane paneMain;
	@FXML
	private Pane paneContent;
	@FXML
	private Pane paneImage;
	@FXML
	private Pane paneControls;
	@FXML
	private Pane paneAddNew;
	
	// combo box:
	@FXML
	private ComboBox<Auto> cbViewing; // svaki auto vuce svoju sliku; novi (added) auti imaju generic sliku (mby will improve u buducnosti)
		
	// buttons:
	@FXML
	private Button btnRefresh;
	@FXML
	private Button btnLogout;
	@FXML
	private Button btnBack;
	@FXML
	private Button btnNext;
	@FXML
	private Button btnFirst;
	@FXML
	private Button btnLast;
	@FXML
	private Button btnUpdatePane; // UPDATE pane
	@FXML
	private Button btnAddNew; // INSERT pane
	@FXML
	private Button btnAdd; // INSERT btn
	@FXML
	private Button btnUpdate;  // UPDATE btn
	@FXML
	private Button btnCancel; // cancel za update/add new operaciju
	@FXML
	private Button btnDelete; // DELETE btn
	
	// labels:
	@FXML
	private Label lblControls;
	@FXML
	private Label lblAdd;
	@FXML
	private Label lblStatus; // indicator za add new
	@FXML
	private Label lblCurrent;
	@FXML
	private Label lblMake;
	@FXML
	private Label lblModel;
	@FXML
	private Label lblYear;
	@FXML
	private Label lblEngine;
	@FXML
	private Label lblFuel;
	@FXML
	private Label lblDrivetrain;
	@FXML
	private Label lblMileage;
	@FXML
	private Label lblPrice;
	
	// text fields:
	@FXML
	private TextField tfMake;
	@FXML
	private TextField tfModel;
	@FXML
	private TextField tfYear;
	@FXML
	private TextField tfEngine;
	@FXML
	private TextField tfFuel;
	@FXML
	private TextField tfDrivetrain;
	@FXML
	private TextField tfMileage;
	@FXML
	private TextField tfPrice;
	
	// image:
	@FXML
	private ImageView ivCar;
	
	// druga scena:
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	// DRIVER:
	FileHandler fileHandler;
	static Logger logger = Logger.getLogger("autocommander/MainController.java");
	
	// Auto golfMK5 = new Auto(20, "Volkswagen", "Golf MK5", 2005, "2.0 SDI", "Diesel", "fwd", 214500, 8000);
	// Auto golfMK7 = new Auto(20, "Volkswagen", "Golf MK7", 2014, "1.6 TDI", "Diesel", "fwd", 87000, 12000);
	AutoDatabase auti = new AutoDatabase();
	
	public void initialize() throws Exception {
		// inicijalizacija prilikom switchanja na scenu
		try {
			fileHandler = new FileHandler("Logs/AutoCommander.log");
			logger.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			logger.info("AutoCommander inicijaliziran!");
		} catch (IOException e) {
			logger.warning("Greska pri inicijalizaciji! Restartujte aplikaciju.");
			e.printStackTrace();
		}
		
		paneControls.setDisable(true);
		btnDelete.setDisable(true);
		
		try {
			cbViewing.getItems().clear();
			for (Auto a : auti.getAllAutos()) {
				cbViewing.getItems().addAll(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warning("Dogodila se pogreska!");
		}
	}
	
	@FXML
	public void reinitialize() throws Exception {
		logger.warning("Reinicijaliziram!");
		
		try {
			cbViewing.getItems().clear();
			
			for (Auto a : auti.getAllAutos()) {
				cbViewing.getItems().addAll(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warning("Greska prilikom reinicijalizacije!");
		}
		
		btnDelete.setDisable(false);
	}
	
	@FXML
	public void viewSelected(ActionEvent event) {		
		// ovisno o odabranom autu u cbViewing pokazuje info (lambda)
		cbViewing.setOnAction((action) -> {
			Auto odabran = cbViewing.getSelectionModel().getSelectedItem();
			paneControls.setDisable(false);
						
			try {
				outputData(odabran);
			} catch (Exception e) {
				logger.warning("Osvjezavam prikaz CB...");
			}
		});
		
		btnDelete.setDisable(false);
	}
	
	// funkcija za postavljanje svih labela i image viewa:
	public void outputData(Auto selected) throws Exception {
		// pokazi na labels:
		lblMake.setText(selected.getMake());
		lblModel.setText(selected.getModel());
		lblYear.setText(String.valueOf(selected.getYear()));
		lblEngine.setText(selected.getEngine());
		lblFuel.setText(selected.getFuel());
		lblDrivetrain.setText(selected.getDrivetrain().toUpperCase());
		lblMileage.setText(String.valueOf(selected.getMileage()) + " km");
		lblPrice.setText(String.valueOf(selected.getPrice()) + " EUR");
						
		// pokazi image:
		String picture = "file:Resources/Auto/" + selected.getImagePath();
		Image autoImg = new Image(picture);
		ivCar.setImage(autoImg);
		
		// logaj:
		logger.info("Odabran objekt '" + selected + "' (" + selected.getID() + ")");
		
		btnUpdatePane.setDisable(false);
	}
		
	// samples:
	public void viewSamples(ActionEvent event) {
		// test - ser:
		try {
			FileOutputStream fileOut = new FileOutputStream("Data/Autos.ser");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(auti);
			objectOut.close();
			fileOut.close();
			System.out.println("MSG: Serijalizirano u 'Data/Autos.ser'");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// test - deser:
		
		try {
			FileInputStream fileIn = new FileInputStream("Data/Autos.ser");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			AutoDatabase deser = (AutoDatabase)objectIn.readObject();
			System.out.println(deser);
			objectIn.close();
			fileIn.close();
			System.out.println("Deserijalizirao!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		
	}
	
	public void addToCB(Auto auto) throws IOException {
		cbViewing.getItems().add(auto);
	}
	
	public void removeFromCB(Auto auto) throws IOException {
		cbViewing.getItems().remove(auto);
	}
	
	// LOGOUT btn - nazad na login screen:
	@FXML
	public void onLogoutClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
		root = loader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root, 600, 400);
		stage.setScene(scene);
		stage.show();
		
		logger.warning("Korisnik odjavljen!");
	}
	
	// ADD NEW BUTTON - pokazuje pane za insert:
	@FXML
	public void showInsertPane(ActionEvent event) throws IOException {
		logger.info("Unos novog objekta omogucen, spreman.");
		paneAddNew.setDisable(false);
		lblAdd.setText("Add new object:");
		btnCancel.setDisable(false);
		btnUpdate.setDisable(true); // ne mozes updateovat ako stvaras novi
		btnAdd.setDisable(false);
		lblStatus.setText("Ready to insert.");
		
		// disable controls pane, combobox, labels & image:
		paneControls.setDisable(true);
	}
	
	// EDIT CURRENT BTN - UPDATE CURRENTLY VIEWING:
	@FXML
	public void showUpdatePane(ActionEvent event) throws IOException {
		Auto forUpdate = cbViewing.getSelectionModel().getSelectedItem();
		
		logger.info("Update postojuceg objekta omogucen, spreman.");
		paneAddNew.setDisable(false);
		lblAdd.setText("Update object:");
		btnCancel.setDisable(false);
		btnUpdate.setDisable(false);
		btnAdd.setDisable(true); // ne mozes insertat ako updateujes!
		lblStatus.setText("Ready to update.");
		
		// disable controls pane, combobox, labels & image:
		paneControls.setDisable(true);
		paneControls.setDisable(true);
		
		// fill text fields with currently viewing values:
		tfMake.setText(forUpdate.getMake());
		tfModel.setText(forUpdate.getModel());
		tfYear.setText(String.valueOf(forUpdate.getYear()));
		tfEngine.setText(forUpdate.getEngine());
		tfFuel.setText(forUpdate.getFuel());
		tfDrivetrain.setText(forUpdate.getDrivetrain());
		tfMileage.setText(String.valueOf(forUpdate.getMileage()));
		tfPrice.setText(String.valueOf(forUpdate.getPrice()));
	}
	
	@FXML
	public void DeleteObject(ActionEvent event) throws IOException {
		// brise odabrani objekt (onaj sta trenutno gledas) iz baze
		Auto forDelete = cbViewing.getSelectionModel().getSelectedItem();
		auti.remove(forDelete);
		removeFromCB(forDelete);
		logger.warning("Obrisao objekt " + forDelete.toString() + "(" + forDelete.getID() + ")");
		
		try {
			reinitialize();
		} catch (Exception e) {
			logger.warning("Greska prilikom reinicijalizacije!");
		}
		
	}
	
	@FXML
	public void InsertNewObject(ActionEvent event) throws IOException {
		// insertuje novi objekt u bazu
		// dohvati nove vrijednosti iz text fieldova:
		int new_id = auti.getAllAutos().size() + 1;
		String new_make = tfMake.getText();
		String new_model = tfModel.getText();
		int new_year = Integer.parseInt(tfYear.getText());
		String new_engine = tfEngine.getText();
		String new_fuel = tfFuel.getText();
		String new_drivetrain = tfDrivetrain.getText(); // toupper!
		double new_mileage = Double.parseDouble(tfMileage.getText());
		int new_price = Integer.parseInt(tfPrice.getText());
		
		// ubaci novi objekt u bazu:
		try {
			Auto novi = new Auto(new_id, new_make, new_model, new_year, new_engine, new_fuel, new_drivetrain, new_mileage, new_price, "generic.jpg");
			auti.add(novi);
			addToCB(novi);

			lblStatus.setText("Inserted!");
		} catch (Exception e) {
			logger.warning("Fatalna greska, nemoguci unos! Pokusajte ponovno i popunite SVA polja!");
			lblStatus.setText("Failed to insert!");
			e.printStackTrace();
		}
		
		clearContents();
	}
	
	@FXML
	public void UpdateExistingObject(ActionEvent event) throws IOException {
		// updateuje odabrani objekt
		Auto selected = cbViewing.getSelectionModel().getSelectedItem(); // trenutni odabrani je onaj koji hocu update
		Auto forUpdate = new Auto(
				selected.getID(),
				tfMake.getText(),
				tfModel.getText(),
				Integer.parseInt(tfYear.getText()),
				tfEngine.getText(),
				tfFuel.getText(),
				tfDrivetrain.getText(),
				Double.parseDouble(tfMileage.getText()),
				Integer.parseInt(tfPrice.getText()),
				selected.getImagePath());
		
		try {
			auti.update(selected, forUpdate);
			
			lblStatus.setText("Updated!");
		} catch (Exception e) {
			logger.warning("Greska prilikom updatea objekta!");
			lblStatus.setText("Failed to update!");
			e.printStackTrace();
		}
		
		clearContents();
	}
			
	// Cancel (btn):
	@FXML
	public void cancelOperation(ActionEvent event) throws IOException {	
		// enable controls pane (revert):
		paneControls.setDisable(false);
		paneContent.setDisable(false);
		paneImage.setDisable(false);
		cbViewing.setDisable(false);
		
		// clear text fields (possible future feature - napravit jos jedan button za clear fields, but whatever):
		tfMake.clear();
		tfModel.clear();
		tfYear.clear();
		tfEngine.clear();
		tfFuel.clear();
		tfDrivetrain.clear();
		tfMileage.clear();
		tfPrice.clear();
		
		// disable add/update pane:
		paneAddNew.setDisable(true);
		
		// cosmetic:
		lblAdd.setText("Insert new or update existing:");
		lblStatus.setText("Canceled. Waiting again...");
		
		logger.info("Korisnik prekinuo operaciju.");
	}
	
	public void clearContents() {
		paneControls.setDisable(false);
		paneAddNew.setDisable(true);
		
		tfMake.clear();
		tfModel.clear();
		tfYear.clear();
		tfEngine.clear();
		tfFuel.clear();
		tfDrivetrain.clear();
		tfMileage.clear();
		tfPrice.clear();
		
		lblAdd.setText("Insert new or update existing:");
		lblStatus.setText("Done! Waiting...");
	}
	
	/*
	 * ==========================================
	 * 				CONTROLS PANE
	 * ==========================================
	 */
	
	// Previous btn:
	@FXML
	public void goBack(ActionEvent event) throws IOException {
		// pokazi auto sa ID = trenutni - 1
		boolean found = false;
		Auto odabran = cbViewing.getSelectionModel().getSelectedItem();
		int trazeni = odabran.getID() - 1;
		
		logger.info("Trazim objekt sa ID = " + trazeni + " ...");
		
		for (Auto a : auti.getAllAutos()) {
			if (a.getID() == trazeni) {
				found = true;
				cbViewing.getSelectionModel().selectPrevious();
				logger.info(found + " -> Nasao objekt sa ID = " + trazeni + "!");
				try {
					outputData(a);
				} catch (Exception e) {
					logger.warning("Osvjezavam prikaz...");
				}
				break;
			}
		}
	}
	
	// Next btn:
	@FXML
	public void goNext(ActionEvent event) throws IOException {
		// pokazi auto sa ID = trenutni + 1
		boolean found = false;
		Auto odabran = cbViewing.getSelectionModel().getSelectedItem();
		int trazeni = odabran.getID() + 1;
		
		logger.info("Trazim objekt sa ID = " + trazeni + " ...");
		
		for (Auto a : auti.getAllAutos()) {
			if (a.getID() == trazeni) {
				found = true;
				cbViewing.getSelectionModel().selectNext();
				logger.info(found + " -> Nasao objekt sa ID = " + trazeni + "!");
				try {
					outputData(a);
				} catch (Exception e) {
					logger.warning("Osvjezavam prikaz...");
				}
				break;
			}
		}
	}
	
	// First btn - najstariji objekt u bazi:
	@FXML
	public void goFirst(ActionEvent event) throws IOException {
		// pokazi auto sa ID == 1
		boolean found = false;
		int trazeni = 1;
		
		logger.info("Trazim prvi objekt u bazi (ID = " + trazeni + ") ...");
		
		for (Auto a : auti.getAllAutos()) {
			if (a.getID() == trazeni) {
				found = true;
				cbViewing.getSelectionModel().selectFirst();
				logger.info(found + " -> Nasao prvi objekt (ID = " + trazeni + ")!");
				try {
					outputData(a);
				} catch (Exception e) {
					logger.warning("Osvjezavam prikaz...");
				}
				break;
			}
		}
	}
	
	// Last btn - najnoviji objekt u bazi:
	@FXML
	public void goLast(ActionEvent event) throws IOException {
		// pokazi auto sa ID = sizeof db
		boolean found = false;
		int trazeni = auti.getAllAutos().size();
		
		logger.info("Trazim zadnji objekt u bazi (ID = " + trazeni + ") ...");
		
		for (Auto a : auti.getAllAutos()) {
			if (a.getID() == trazeni) {
				found = true;
				cbViewing.getSelectionModel().selectLast();
				logger.info(found + " -> Nasao zadnji objekt (ID = " + trazeni + ")!");
				try {
					outputData(a);
				} catch (Exception e) {
					logger.warning("Osvjezavam prikaz...");
				}
				break;
			}
		}
	}
}
