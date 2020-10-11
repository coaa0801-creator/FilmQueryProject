package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.app.EmployeeSearch;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String user = "student";
	private static final String pass = "student";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

  @Override
  public Film findFilmById(int filmId) throws SQLException {
	  Film film = null;
	  Connection conn = DriverManager.getConnection(URL, user, pass);
	  String sql = "SELECT film.*, language.* FROM film"
	  		+ "JOIN language on film.language_id = language.id "
	  		+ " WHERE film.id = ?";
	  PreparedStatement stmt = conn.prepareStatement(sql);
	  stmt.setInt(1,filmId);
	  ResultSet filmResult = stmt.executeQuery();
	  if (filmResult.next()) {
	    film = new Film(); // Create the object
	    film.setId(filmResult.getInt("id"));
	    film.setTitle(filmResult.getString("title").toUpperCase());
    	film.setDecsription(filmResult.getString("description").toUpperCase());
    	film.setReleaseYear(filmResult.getShort("release_year"));
    	film.setLanguage(filmResult.getString("name").toUpperCase());
    	film.setRentalDuration(filmResult.getInt("rental_duration"));
    	film.setRentalRate(filmResult.getDouble("rental_rate"));
    	film.setFilmLength(filmResult.getInt("length"));
    	film.setReplacementCost(filmResult.getDouble("replacement_cost"));
    	film.setRating(filmResult.getString("rating").toUpperCase());
    	film.setSpecialFeatures(filmResult.getString("special_features").toUpperCase());
	  }
	 
	  return film;
  }

  public Actor findActorById(int actorId) throws SQLException {
	  Actor actor = null;
	  Connection conn = DriverManager.getConnection(URL, user, pass);
	  String sql = "SELECT id, first_name, last_name FROM actor WHERE actor.id = ?";
	  PreparedStatement stmt = conn.prepareStatement(sql);
	  stmt.setInt(1,actorId);
	  ResultSet actorResult = stmt.executeQuery();
	  if (actorResult.next()) {
	    actor = new Actor(); // Create the object
	    actor.setId(actorResult.getInt("id"));
	    actor.setFirstName(actorResult.getString("first_name").toUpperCase());
	    actor.setLastName(actorResult.getString("last_name").toUpperCase());
	    actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
	  }
	 
	  return actor;
	}

	public List<Film> findFilmsByActorId(int actorId) {
	  List<Film> films = new ArrayList<>();
	  try {
	    Connection conn = DriverManager.getConnection(URL, user, pass);
	    String sql = "SELECT film.*, language.* "
	               +  " FROM film "
	               + "JOIN language on film.language_id = language.id "  
	               + "JOIN film_actor ON film.id = film_actor.film_id "
	               + " WHERE actor_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, actorId);
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
	    	int filmId = rs.getInt("id");
	    	String title = rs.getString("title").toUpperCase();
	    	String desc = rs.getString("description").toUpperCase();
	    	short releaseYear = rs.getShort("release_year");
	    	String lang = rs.getString("name").toUpperCase();
	    	int rentDur = rs.getInt("rental_duration");
	    	double rate = rs.getDouble("rental_rate");
	    	int length = rs.getInt("length");
	    	double repCost = rs.getDouble("replacement_cost");
	    	String rating = rs.getString("rating").toUpperCase();
	    	String features = rs.getString("special_features").toUpperCase();

	    	Film film = new Film(filmId, title, desc, releaseYear, lang,
                    rentDur, rate, length, repCost, rating, features);
	      films.add(film);
	    }
	    rs.close();
	    stmt.close();
	    conn.close();
	  } catch (SQLException e) {
	    e.printStackTrace();
	  }
	  return films;
	}
	
	
	
@Override
public List<Actor> findActorsByFilmId(int filmId) {
	  List<Actor> actors = new ArrayList<>();
	  try {
	    Connection conn = DriverManager.getConnection(URL, user, pass);
	    String sql = "SELECT actor.* "
	               + "FROM actor "
	               + "JOIN film_actor ON actor.id = film_actor.actor_id "
	               + "JOIN film ON film.id = film_actor.film_id "
	               + "WHERE film.id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, filmId);
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
	    	int iD = rs.getInt("id");
	    	String title = rs.getString("first_name").toUpperCase();
	    	String desc = rs.getString("last_name").toUpperCase();

	      Actor actor= new Actor(iD, title, desc);
	      actors.add(actor);
	    }
	    rs.close();
	    stmt.close();
	    conn.close();
	  } catch (SQLException e) {
	    e.printStackTrace();
	  }
	  return actors;
	}





@Override
public EmployeeSearch findEmployeeByID(int employeeID) throws SQLException {
	EmployeeSearch employee = null;
	  Connection conn = DriverManager.getConnection(URL, user, pass);
	  String sql = "SELECT staff.* FROM staff WHERE staff.id = ?";
	  PreparedStatement stmt = conn.prepareStatement(sql);
	  stmt.setInt(1,employeeID);
	  ResultSet empResult = stmt.executeQuery();
	  if (empResult.next()) {
	    employee = new EmployeeSearch(); // Create the object
	    employee.setId(empResult.getInt("id"));
	    employee.setFirstName(empResult.getString("first_name").toUpperCase());
	    employee.setLastName(empResult.getString("last_name").toUpperCase());
	    if (empResult.getObject("password") == null) {
	    	employee.setPassword("");
	    }else {
	    employee.setPassword(empResult.getString("password"));}
//	    System.out.println(employee.getFirstName());
	  }
	
	return employee;
	
}


@Override
public Film findFilmByTitle(String filmName) throws SQLException {
	  Film film = null;
	  Connection conn = DriverManager.getConnection(URL, user, pass);
	  String sql = "SELECT film.*, language.* FROM film"
	  			+ " JOIN language on film.language_id = language.id "
	  			+ "WHERE film.title LIKE '%?%' ";
	  PreparedStatement stmt = conn.prepareStatement(sql);
	  stmt.setString(0, filmName);
	  ResultSet filmResult = stmt.executeQuery();
	  if (filmResult.next()) {
	    film = new Film(); // Create the object
	    film.setId(filmResult.getInt("id"));
	    film.setTitle(filmResult.getString("title").toUpperCase());
  	film.setDecsription(filmResult.getString("description").toUpperCase());
  	film.setReleaseYear(filmResult.getShort("release_year"));
	film.setLanguage(filmResult.getString("name").toUpperCase());
  	film.setRentalDuration(filmResult.getInt("rental_duration"));
  	film.setRentalRate(filmResult.getDouble("rental_rate"));
  	film.setFilmLength(filmResult.getInt("length"));
  	film.setReplacementCost(filmResult.getDouble("replacement_cost"));
  	film.setRating(filmResult.getString("rating").toUpperCase());
  	film.setSpecialFeatures(filmResult.getString("special_features").toUpperCase());
	  }
	 
	  return film;
}

@Override
public List<Film> findFilmsByActorName(String actorName) throws SQLException {
	  List<Film> films = new ArrayList<>();
	  try {
	    Connection conn = DriverManager.getConnection(URL, user, pass);
	    String sql = "SELECT film.*, language.* FROM film "
	    					+ "JOIN language on film.language_id = language.id "
	    					+ "JOIN film_actor ON film.id = film_actor.film_id "
	    					+ "JOIN actor ON actor.id = film_actor.actor_id "
	    					+ "WHERE concat(actor.first_name,' ', actor.last_name) LIKE '%?%'";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, actorName);
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
	    	int filmId = rs.getInt("id");
	    	String title = rs.getString("title").toUpperCase();
	    	String desc = rs.getString("description").toUpperCase();
	    	short releaseYear = rs.getShort("release_year");
	    	String lang = rs.getString("name").toUpperCase();
	    	int rentDur = rs.getInt("rental_duration");
	    	double rate = rs.getDouble("rental_rate");
	    	int length = rs.getInt("length");
	    	double repCost = rs.getDouble("replacement_cost");
	    	String rating = rs.getString("rating").toUpperCase();
	    	String features = rs.getString("special_features").toUpperCase();

	      Film film = new Film(filmId, title, desc, releaseYear, lang,
	                           rentDur, rate, length, repCost, rating, features);
	      films.add(film);
	    }
	    rs.close();
	    stmt.close();
	    conn.close();
	  } catch (SQLException e) {
	    e.printStackTrace();
	  }
	  return films;
	}

public List<Film> findFilmsByRating(String searchRating) {
	  List<Film> films = new ArrayList<>();
	  try {
	    Connection conn = DriverManager.getConnection(URL, user, pass);
	    String sql = "SELECT film.*, language.* FROM film "
	    		+ "JOIN language on film.language_id = language.id "  
	    		+ "WHERE film.rating = '?'";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, searchRating);
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
	    	int filmId = rs.getInt("id");
	    	String title = rs.getString("title").toUpperCase();
	    	String desc = rs.getString("description").toUpperCase();
	    	short releaseYear = rs.getShort("release_year");
	    	String lang = rs.getString("name").toUpperCase();
	    	int rentDur = rs.getInt("rental_duration");
	    	double rate = rs.getDouble("rental_rate");
	    	int length = rs.getInt("length");
	    	double repCost = rs.getDouble("replacement_cost");
	    	String rating = rs.getString("rating").toUpperCase();
	    	String features = rs.getString("special_features").toUpperCase();

	    	Film film = new Film(filmId, title, desc, releaseYear, lang,
                    rentDur, rate, length, repCost, rating, features);
	      films.add(film);
	    }
	    rs.close();
	    stmt.close();
	    conn.close();
	  } catch (SQLException e) {
	    e.printStackTrace();
	  }
	  return films;
	}

}
