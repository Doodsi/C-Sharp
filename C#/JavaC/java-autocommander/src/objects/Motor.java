package objects;

// import java.util.Objects;

public class Motor extends Vehicle {
	// motor-specific:
	protected String type; // naked, supersports, cross, quad, scooter, etc.  
	protected int kubikaza; // engine
	protected int weight; // in KG
	protected double mileage;
	protected int price; // eur
	
	// def constructor:
	public Motor(int id, String make, String model, int year, String type, int kubikaza, int weight, double mileage, int price) {
		super(id, make, model, year);
		this.type = type;
		this.kubikaza = kubikaza;
		this.weight = weight;
		this.mileage = mileage;
		this.price = price;
	}
	
	// copy:
	public Motor(Motor og) {
		super(og.id, og.make, og.model, og.year);
		this.type = og.type;
		this.kubikaza = og.kubikaza;
		this.weight = og.weight;
		this.mileage = og.mileage;
		this.price = og.price;
	}
	
	// getteri:
	public String getType() {
		return type;
	}
	
	public int getKubikaza() {
		return kubikaza;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public double getMileage() {
		return mileage;
	}
	
	public int getPrice() {
		return price;
	}	
	
	// setteri:
	public void setType(String type) {
		this.type = type.toUpperCase();
	}
	
	public void setKubikaza(int kubikaza) {
		if (kubikaza > 0) {
			this.kubikaza = kubikaza;
		} else {
			this.kubikaza = 0;
		}
	}
	
	public void setWeight(int weight) {
		if (weight > 0) {
			this.weight = weight;
		} else {
			this.weight = 0;
		}
	}
	
	public void setMileage(double mileage) {
		this.mileage = mileage;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
}
