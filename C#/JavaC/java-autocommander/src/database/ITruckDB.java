package database;

import objects.Truck;

import java.util.List;

public interface ITruckDB {
	// CRUD:
	public Truck get(int id);
	public void add(Truck truck);
	public boolean update(Truck truck);
	public void remove(Truck truck);
	
	// list:
	public List<Truck> getAllTrucks();

}
