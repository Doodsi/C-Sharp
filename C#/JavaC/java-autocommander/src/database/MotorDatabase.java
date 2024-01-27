package database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import objects.Motor;

public class MotorDatabase implements IMotorDB {
	static Logger logger = Logger.getLogger("database/MotorDatabase.java");
	
	List<Motor> database = new ArrayList<Motor>();
	
	public MotorDatabase() {
		FileHandler fileHandler;
		
		try {
			fileHandler = new FileHandler("Logs/MotorDB.log");
			logger.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			logger.info("Logger inicijaliziran!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		logger.info("MotorDB init!");
		
		// TODO: napravi nekoliko inserta u motor database:
		database.add(new Motor(1, "Husqvarna", "Nuda 900R", 2012, "naked", 900, 270, 23000, 14500));
		
	}
	
	// interface implementation:
	@Override
	public Motor get(int id) {
		for (Motor m : database) {
			if (m.getID() == id) {
				return m;
			}
		}
		return null;
	}
	
	@Override
	public void add(Motor motor) {
		database.add(motor);
	}
	
	@Override
	public boolean update(Motor motor) {
		for (Motor m : database) {
			if (m.getID() == motor.getID()) {
				m.setMake(motor.getMake());
				m.setModel(motor.getModel());
				m.setYear(motor.getYear());
				m.setType(motor.getType());
				m.setKubikaza(motor.getKubikaza());
				m.setWeight(motor.getWeight());
				m.setMileage(motor.getMileage());
				m.setPrice(motor.getPrice());
				
				return true;
			}
		}
		database.add(motor);
		return false;
	}
	
	@Override
	public void remove (Motor motor) {
		database.remove(motor);
	}
	
	@Override
	public List<Motor> getAllMotors() {
		logger.warning("'GetAllMotors' called!");
		return database;
	}
}
