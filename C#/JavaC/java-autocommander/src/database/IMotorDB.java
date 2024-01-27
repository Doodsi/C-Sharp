package database;

import objects.Motor;

import java.util.List;

public interface IMotorDB {
	// CRUD:
	public Motor get(int id);
	public void add(Motor motor);
	public boolean update(Motor motor);
	public void remove(Motor motor);
	
	// List:
	public List<Motor> getAllMotors();

}
