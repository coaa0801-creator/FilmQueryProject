package com.skilldistillery.filmquery.entities;

import java.time.Duration;
import java.util.List;

public class Film {
	private int id;
	private String title;
	private String decsription;
	private short releaseYear;
	private int languageId;
	private int rentalDuration;
	private double rentalRate;
	private int filmLength;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> actors;
	private String language;
	
	

	public Film() {
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "FILM ID:\t\t" + id + "\nTITLE:\t\t\t" + title + "\nDESCRIPTION:\t\t" + decsription + "\nRATING:\t\t\t" + rating + "\nRELEASE YEAR:\t\t" + releaseYear
				+ "\nLANGUAGE:\t\t" + language + "\nRENTAL DURATION:\t" + rentalDuration + " DAYS" + "\nRENTAL RATE:\t\t$" + rentalRate
				+ "\nFILM LENGTH:\t\t" + filmLength + " MINUTES" + "\nCOST IF NOT RETURNED:\t$" + replacementCost 
				+ "\nFEATURES INCLUDED:\t" + specialFeatures;
	}
	public void printCast(List<Actor> actors2) {
		System.out.println("CAST: ");
			for (Actor actor : actors2) {
		System.out.println("\t\t\t" + actor.getFirstName() + " " + actor.getLastName());}
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actors == null) ? 0 : actors.hashCode());
		result = prime * result + ((decsription == null) ? 0 : decsription.hashCode());
		result = prime * result + filmLength;
		result = prime * result + id;
		result = prime * result + languageId;
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + releaseYear;
		result = prime * result + rentalDuration;
		long temp;
		temp = Double.doubleToLongBits(rentalRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(replacementCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((specialFeatures == null) ? 0 : specialFeatures.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (actors == null) {
			if (other.actors != null)
				return false;
		} else if (!actors.equals(other.actors))
			return false;
		if (decsription == null) {
			if (other.decsription != null)
				return false;
		} else if (!decsription.equals(other.decsription))
			return false;
		if (filmLength != other.filmLength)
			return false;
		if (id != other.id)
			return false;
		if (languageId != other.languageId)
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (releaseYear != other.releaseYear)
			return false;
		if (rentalDuration != other.rentalDuration)
			return false;
		if (Double.doubleToLongBits(rentalRate) != Double.doubleToLongBits(other.rentalRate))
			return false;
		if (Double.doubleToLongBits(replacementCost) != Double.doubleToLongBits(other.replacementCost))
			return false;
		if (specialFeatures == null) {
			if (other.specialFeatures != null)
				return false;
		} else if (!specialFeatures.equals(other.specialFeatures))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	public short getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(short releaseYear) {
		this.releaseYear = releaseYear;
	}
	public int getRentalDuration() {
		return rentalDuration;
	}
	
	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
	public int getFilmLength() {
		return filmLength;
	}

	public void setFilmLength(int filmLength) {
		this.filmLength = filmLength;
	}

	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
	public List<Actor> getActors() {
		return actors;
	}
	
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDecsription() {
		return decsription;
	}
	public void setDecsription(String decsription) {
		this.decsription = decsription;
	}
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public Film(int id, String title, String decsription, short releaseYear, String language, int rentalDuration,
			double rentalRate, int filmLength, double replacementCost, String rating, String specialFeatures, List<Actor> actors) {
		this.id = id;
		this.title = title;
		this.decsription = decsription;
		this.releaseYear = releaseYear;
		this.language = language;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.filmLength = filmLength;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.actors = actors;
		
	}

	public double getRentalRate() {
		return rentalRate;
	}
	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public double getReplacementCost() {
		return replacementCost;
	}
	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getSpecialFeatures() {
		return specialFeatures;
	}
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
	
}
