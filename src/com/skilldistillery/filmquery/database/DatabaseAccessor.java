package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.app.EmployeeSearch;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
  public Film findFilmById(int filmId) throws SQLException;
  public Actor findActorById(int actorId) throws SQLException;
  public List<Actor> findActorsByFilmId(int filmId);
  public List<Film> findFilmsByActorId(int actorId);
  public EmployeeSearch findEmployeeByID(int employeeId) throws SQLException;
  public Film findFilmByTitle(String filmName) throws SQLException;
  public List<Film> findFilmsByActorName(String actorName) throws SQLException;
  public List<Film> findFilmsByRating(String searchRating);
}
