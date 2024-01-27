package database;

// import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
// import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.Serializable; // serijalizacija

import objects.Auto;

public class AutoDatabase implements IAutoDB, Serializable {
	// logger:
	static Logger logger = Logger.getLogger("database/AutoDatabase.java");
	
	// lista:
	List<Auto> database = new ArrayList<Auto>();
	
	public AutoDatabase() {
		
		FileHandler fileHandler;
		
		try {
			fileHandler = new FileHandler("Logs/AutoDB.log");
			logger.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			logger.info("Logger uspjesno inicijaliziran!");
		} catch (IOException e) {
			logger.warning("Greska pri inicijalizaciji loggera!");
			e.printStackTrace();
		}
		
		logger.info("AUTO DB inicijalizirana!");
		
		// TODO: nekoliko inserta u DB
		database.add(new Auto(1, "Zastava", "125pz", 1975, "1.3", "Benziner", "rwd", 50000, 2000, "125pz.jpg"));
		database.add(new Auto(2, "Ford", "Taunus", 1977, "1.3", "Benziner", "rwd", 70000, 3000, "taunus.jpg"));
		database.add(new Auto(3, "Volkswagen", "Golf MK2", 1988, "1.6", "Diesel", "fwd", 300000, 3500, "golfmk2.jpg"));
		database.add(new Auto(4, "Ford", "Focus MK1", 1999, "1.4 Zetec", "Benziner", "fwd", 134000, 4500, "focusmk1.jpg"));
		database.add(new Auto(5, "Volkswagen", "Golf MK5", 2005, "2.0 SDI", "Diesel", "fwd", 214500, 8000, "golfmk5.jpg"));
		database.add(new Auto(6, "Volkswagen", "Golf MK7", 2014, "1.6 TDI", "Diesel", "fwd", 87000, 12000, "14golfmk7.jpeg"));
		database.add(new Auto(7, "Dacia", "Sandero", 2014, "1.5 DCI", "Diesel", "fwd", 130000, 5000, "sandero.jpg"));
		database.add(new Auto(8, "Peugeot", "308", 2014, "1.6 HDI", "Diesel", "fwd", 160000, 8000, "peugeot308.jpg"));
		database.add(new Auto(9, "Skoda", "Fabia", 2015, "1.0 MPI", "Benziner", "fwd", 115000, 7000, "fabia2015.jpg"));
		database.add(new Auto(10, "Ford", "Focus MK3.5", 2015, "1.6 TDCI", "Diesel", "fwd", 140000, 8000, "focus2015.jpeg"));
		database.add(new Auto(11, "Lancia", "Ypsilon", 2017, "1.2", "Benziner", "fwd", 20000, 7000, "lancia2017.jpg"));
		database.add(new Auto(12, "Fiat", "Panda", 2019, "1.2", "Benziner", "fwd", 4500, 8500, "panda2019.jpg"));
		database.add(new Auto(13, "Volkswagen", "Golf MK7", 2013, "2.0 TDI", "Diesel", "fwd", 170000, 9500, "13golfmk7.jpg"));
		database.add(new Auto(14, "Toyota", "Yaris", 2018, "1.0 Turbo", "Benziner", "fwd", 34000, 11000, "yaris2018.jpg"));
		database.add(new Auto(15, "Hyundai", "i10", 2017, "1.0", "Benziner", "fwd", 65000, 6000, "i10.jpg"));
		database.add(new Auto(16, "Volkswagen", "Passat B8", 2016, "1.6 TDI", "Diesel", "fwd", 120000, 14000, "passatb8.jpg"));
		database.add(new Auto(17, "Nissan", "Micra", 2013, "1.0", "Benziner", "fwd", 60000, 7500, "micra13.jpg"));
		database.add(new Auto(18, "Opel", "Astra", 2015, "1.6", "Diesel", "fwd", 180000, 8000, "astra2015.jpg"));
		database.add(new Auto(19, "Ford", "Focus MK3.5", 2019, "1.5 TDCI", "Diesel", "fwd", 90000, 13000, "focus19.jpg"));
		database.add(new Auto(20, "Volkswagen", "Golf MK8", 2021, "2.0 TDI", "Diesel", "fwd", 17000, 26000, "golfmk8.jpg"));
		
		// serijalizacija:
		/*
		try {
			FileOutputStream fileOut = new FileOutputStream("Data/Autos.ser");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(database);
			objectOut.close();
			fileOut.close();
			System.out.println("MSG: Serijalizirano u 'Data/Autos.ser'");
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		// deserializacija:
		
	}
	
	// implementiraj interface IAutoDB:
	@Override
	public Auto get(int id) {
		for (Auto a : database) {
			if (a.getID() == id) {
				logger.info("Dohvatio ID " + id);
				return a;
			}
		}
		logger.info("Nisam dohvatio ID " + id);
		return null;
	}
	
	@Override
	public void add(Auto auto) {
		logger.info("Dodao novi objekt (" + auto + ")\n");
		database.add(auto);
	}
	
	@Override
	public boolean update(Auto bu, Auto au) {
		for (Auto a : database) {
			if (a.getID() == bu.getID()) {
				a.setMake(au.getMake());
				a.setModel(au.getModel());
				a.setYear(au.getYear());
				a.setEngine(au.getEngine());
				a.setFuel(au.getFuel());
				a.setDrivetrain(au.getDrivetrain());
				a.setMileage(au.getMileage());
				a.setPrice(au.getPrice());
				
				logger.info("Uspjesno updateovao objekt '" + au.toString() + "'!");
				return true;
			}
		}
		database.add(au);
		return false;
	}
	
	@Override
	public void remove(Auto auto) {
		logger.warning("Obrisao objekt (" + auto + ")\n");
		database.remove(auto);
	}
	
	@Override
	public List<Auto> getAllAutos() {
		logger.info("Citava baza pregledana!");
		return database;
	}
}
