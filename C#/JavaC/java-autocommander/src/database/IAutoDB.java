package database;

import objects.Auto;

import java.util.List;

public interface IAutoDB {
	// CRUD:
	public Auto get(int id); // read
	public void add(Auto auto); // create
	public boolean update(Auto bu, Auto au); // bu = before update; au = after update
	public void remove(Auto auto); // delete
	
	// Lista:
	public List<Auto> getAllAutos();

}
