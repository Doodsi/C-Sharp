package database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import objects.Truck;

public class TruckDatabase implements ITruckDB {
	static Logger logger = Logger.getLogger("database/TruckDatabase.java");
	
	List<Truck> database = new ArrayList<Truck>();
	
	public TruckDatabase() {
		FileHandler fileHandler;
		
		try {
			fileHandler = new FileHandler("Logs/TruckDB.log");
			logger.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			logger.info("Logger inicijaliziran!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		logger.info("TruckDB init!");
		
		// TODO: dodaj nekoliko inserta u truck DB (kasnije)
		database.add(new Truck(1, "Volvo", "FH16", 2008, "VolvoFH750", 750, 2, 890540, 45000));
	}

	// implementiranje interfacea:
	@Override
	public Truck get(int id) {
		for (Truck t : database) {
			if (t.getID() == id) {
				return t;
			}
		}
		return null;
	}
	
	@Override
	public void add(Truck truck) {
		database.add(truck);
	}
	
	@Override
	public boolean update(Truck truck) {
		for (Truck t : database) {
			if (t.getID() == truck.getID()) {
				t.setMake(truck.getMake());
				t.setModel(truck.getModel());
				t.setYear(truck.getYear());
				t.setEngine(truck.getEngine());
				t.setHorsepower(truck.getHorsepower());
				t.setDrivetrain(truck.getDrivetrain());
				t.setMileage(truck.getMileage());
				t.setPrice(truck.getPrice());
				
				return true;
			}
		}
		database.add(truck);
		return false;
	}
	
	@Override
	public void remove(Truck truck) {
		database.remove(truck);
	}
	
	@Override
	public List<Truck> getAllTrucks() {
		logger.warning("'GetAllTrucks' called!");
		return database;
	}
}
