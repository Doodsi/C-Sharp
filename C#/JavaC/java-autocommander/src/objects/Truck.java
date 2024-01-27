package objects;

// import java.util.Objects;

public class Truck extends Vehicle {
	// truck-specific stuff:
	protected String engine;
	protected int horsepower;
	protected int drivetrain; // how many wheels drive the truck (2, 4, 6, 8.. idk)
	protected double mileage;
	protected int price; // eur
	
	// def constructor:
	public Truck(int id, String make, String model, int year, String engine, int horsepower, int drivetrain, double mileage, int price) {
		super(id, make, model, year);
		this.engine = engine;
		this.horsepower = horsepower;
		this.drivetrain = drivetrain;
		this.mileage = mileage;
		this.price = price;
	}
	
	// copy constructor:
	public Truck(Truck og) {
		super(og.id, og.make, og.model, og.year);
		this.engine = og.engine;
		this.horsepower = og.horsepower;
		this.drivetrain = og.drivetrain;
		this.mileage = og.mileage;
		this.price = og.price;
	}
	
	// getteri:
	public String getEngine() {
		return engine;
	}
	
	public int getHorsepower() {
		return horsepower;
	}
	
	public int getDrivetrain() {
		return drivetrain;
	}
	
	public double getMileage() {
		return mileage;
	}
	
	public int getPrice() {
		return price;
	}
	
	// setteri:
	public void setEngine(String engine) {
		this.engine = engine.toUpperCase();
	}
	
	public void setHorsepower(int horsepower) {
		this.horsepower = horsepower;
	}
	
	public void setDrivetrain(int drivetrain) { // how many wheels drive the truck!
		if (drivetrain != 0) {
			if (drivetrain % 2 == 0) {
				this.drivetrain = drivetrain;
			} else {
				this.drivetrain = 0;
			}
		} else {
			this.drivetrain = 0;
		}
	}
	
	public void setMileage(double mileage) {
		this.mileage = mileage;
	}
	
	public void setPrice(int price) { // in EUR
		this.price = price;
	}

}
