package objects;

public abstract class Vehicle {
	// univerzalne vrijednosti za vozila:
	protected int id;
	protected String make;
	protected String model;
	protected int year;
	
	// konstruktor:
	public Vehicle(int id, String make, String model, int year) {
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
	}
	
	// getteri:
	public int getID() {
		return id;
	}
	
	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}
	
	public int getYear() {
		return year;
	}

	// setteri:
	public void setMake(String make) {
		this.make = make;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public void setYear(int year) {
		if (year >= 1900) {
			this.year = year;
		} else {
			this.year = 1900;
		}
	}
}
