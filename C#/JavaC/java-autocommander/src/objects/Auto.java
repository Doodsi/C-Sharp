package objects;

// import java.util.Objects;

public class Auto extends Vehicle implements java.io.Serializable {
	// car-specific:
	protected String engine;
	protected String fuel;
	protected String drivetrain;
	protected double mileage;
	protected int price; // in eur
	protected String img; // image name in Resources/Auto dir
	
	// def constructor:
	public Auto(int id, String make, String model, int year, String engine, String fuel, String drivetrain, double mileage, int price, String img) {
		super(id, make, model, year);
		this.engine = engine;
		this.fuel = fuel;
		this.drivetrain = drivetrain;
		this.mileage = mileage;
		this.price = price;
		this.img = img;
	}
	
	// copy:
	public Auto(Auto og) {
		super(og.id, og.make, og.model, og.year);
		this.engine = og.engine;
		this.fuel = og.fuel;
		this.drivetrain = og.drivetrain;
		this.mileage = og.mileage;
		this.price = og.price;
		this.img = og.img;
	}
	
	// override tostring:
	@Override
	public String toString() {
		return returnYear() + " " + getMake() + " " + getModel();
	}
	
	// getteri:
	public String getEngine() {
		return engine;
	}
	
	public String getFuel() {
		return fuel;
	}
	
	public String getDrivetrain() {
		return drivetrain;
	}
	
	public double getMileage() {
		return mileage;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getImagePath() {
		return img;
	}
	
	public String returnYear() {
		return String.valueOf(getYear());
	}
	
	// setteri:
	public void setEngine(String engine) {
		this.engine = engine.toUpperCase();
	}
	
	public void setFuel(String fuel) {
		this.fuel = fuel.toUpperCase();
	}
	
	public void setDrivetrain(String drivetrain) {
		if (drivetrain.equalsIgnoreCase("rwd") || drivetrain.equalsIgnoreCase("fwd") || drivetrain.equalsIgnoreCase("awd")) {
			this.drivetrain = drivetrain.toUpperCase();
		} else {
			this.drivetrain = "Unknown drivetrain";
		}
	}
	
	public void setMileage(double mileage) { // iako je MILEAGE, vrijednost je u KM!
		this.mileage = mileage;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	// add more stuff???
}
