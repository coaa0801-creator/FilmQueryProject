package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.sysoutformatting.Align;

public class CustomerSearch {

	Scanner input = new Scanner(System.in);

	public boolean runCustomerFunctions(){
		boolean keepGoing = menuSwitch();
		return keepGoing;
	}

	private boolean menuSwitch() {
		boolean keepGoing = true;
		DatabaseAccessorObject run = new DatabaseAccessorObject();
		String userSearch = "";
		
		  while(keepGoing) {
			  searchMenu();
			  String userInput = input.nextLine().toLowerCase();
		  switch (userInput) {
		  case "1": case "film": case "film name": case "name":
			  userSearch = promptForSearchParameter(1);
			  List<Film> searchFilmTitle = run.findFilmByTitle(userSearch);
			run.printFilmsList(searchFilmTitle);
			  break;
		  case "2": case "rating": case "r":
			  userSearch = promptForSearchParameter(2);
			  List<Film> filmsByRating = run.findFilmsByRating(userSearch);
			  run.printFilmsList(filmsByRating);
			  break;
		  case "3": case "actor": case "a": 
			   userSearch = promptForSearchParameter(3);
			   List<Film> filmsByActorName = run.findFilmsByActorName(userSearch);
			run.printFilmsList(filmsByActorName);
			  break;
		  case "4": case "genre":
			   userSearch = promptForSearchParameter(4);
			   List<Film> filmsByGenre = run.findFilmsByCategory(userSearch);
			   run.printFilmsList(filmsByGenre);
			  break;
		  case "5": case "general": case "search": case "general search":
			   userSearch = promptForSearchParameter(5);
			   List<Film> filmsBasedOnSearch = run.findFilmsFromSearch(userSearch);
			   run.printFilmsList(filmsBasedOnSearch);
			  break;
		  case "6": case "main": case "menu":
			 boolean mainMenu = true;
			 return mainMenu;
		  case "7": case "quit": case "q":
			  keepGoing = false;
			  break;
		default: System.out.println("Invalid entry\n");
		  }
		  }
		  return keepGoing;
		
	}

	private String promptForSearchParameter(int searchType) {
		String searchHeader = "";
				if (searchType == 1) {
					searchHeader = "FILM NAME";
					
				}else if(searchType == 2) {
					searchHeader = "RATING";
					
				}else if(searchType == 3) {
					searchHeader = "ACTOR/ACTRESS";
					
				}else if(searchType == 4) {
					searchHeader = "GENRE";
					
				}else if(searchType == 5) {
					searchHeader = "KEYWORD";
				}
		printSearchParameterPrompt(searchHeader);
		String userSearch = input.nextLine();
		return userSearch;
		
	}

	private void printSearchParameterPrompt(String searchHeader) {
	
		System.out.print("   _____                 _   \n" + 
				"  / ____|               | |  \n" + 
				" | |  __ _   _  ___  ___| |_ \n" + 
				" | | |_ | | | |/ _ \\/ __| __|\n" + 
				" | |__| | |_| |  __/\\__ \\ |_ \n" + 
				"  \\_____|\\__,_|\\___||___/\\__|\n" + 
				"                            ");
		System.out.println("\n\n\n<================================>");
		System.out.println("|                                |");
		System.out.println("|"+ Align.center("PLEASE ENTER YOUR", 32, (char)32) + "|");
		System.out.println("|                                |");
		System.out.println("|"+ Align.center(searchHeader, 32, (char)32) + "|");
		System.out.println("|                                |");
		System.out.println("|"+ Align.center("TO SEARCH", 32, (char)32) + "|");
		System.out.println("|                                |");
		System.out.println("<================================>");}
		
	

	private void searchMenu() {
System.out.print("   _____                 _   \n" + 
		"  / ____|               | |  \n" + 
		" | |  __ _   _  ___  ___| |_ \n" + 
		" | | |_ | | | |/ _ \\/ __| __|\n" + 
		" | |__| | |_| |  __/\\__ \\ |_ \n" + 
		"  \\_____|\\__,_|\\___||___/\\__|\n" + 
		"                            ");
		System.out.println("\n\n\n<================================>");
		System.out.println("|           SEARCH MENU          |");
		System.out.println("|                                |");
		System.out.println("|    1: BY FILM NAME             |");
		System.out.println("|    2: BY RATING                |");
		System.out.println("|    3: BY ACTOR                 |");
		System.out.println("|    4: BY GENRE                 |");
		System.out.println("|    5: GENERAL SEARCH           |");
		System.out.println("|    6: MAIN MENU                |");
		System.out.println("| QUIT: QUIT                     |");
		System.out.println("|                                |");
		System.out.println("<================================>");}
}
