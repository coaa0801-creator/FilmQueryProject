package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
//    app.test();
    app.launch();
  }

//  private void test() throws SQLException {
//    Film film = db.findFilmById(1);
//    System.out.println(film);
//  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input){
	  boolean keepGoing = true;
	 printWelcome();
	  while(keepGoing) {
		  printMainMenu();
		  String userInput = input.nextLine().toLowerCase();
	  switch (userInput) {
	  case "1": case "employee": case "e":
		  EmployeeSearch runE = new EmployeeSearch();
		  keepGoing = runE.runEmployeeFunctions();
		  break;
	  case "2": case "guest": case "g": case "customer": case "c":
		  CustomerSearch runC = new CustomerSearch();
		  keepGoing = runC.runCustomerFunctions();
		  break;
	  case "3": case "quit": case "q":
		  keepGoing = false;
		  break;
	default: System.out.println("Invalid entry\n");
	  }
	  
	  }
	  printLogOut();
	  
	  
	  
	  
	  }
		private void printLogOut() {
	
}

		private void printWelcome(){System.out.println(" __          __  _                          \n" + 
				" \\ \\        / / | |                         \n" + 
				"  \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___ \n" + 
				"   \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\\n" + 
				"    \\  /\\  /  __/ | (_| (_) | | | | | |  __/\n" + 
				"     \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|");
			
		}
		private void printMainMenu() {
			System.out.println("\n<==========================================>");
			System.out.println("|                                          |");
		System.out.println("|         ___________________              |\n"
		+"|       )=|                 |     /        |\n"
		+"|         | Movie Franchise |====||        |\n"
		+"|         | Rental Info App |====||        |\n"
		+"|         |                 |+    \\        |\n"
		+"|         -------------------              |\n"
		+"|	          (--)                     |\n"
		+"|                *    *                    |\n"
		+"|               *      *                   |\n"
		+"|              *        *                  |\n"
		+"|             *          *                 |\n"
		+"|            *            *                |");
			System.out.println("|                                          |");
			System.out.println("|                                          |");
			System.out.println("|      ARE YOU AN EMPLOYEE OR GUEST?       |");
			System.out.println("|                                          |");
			System.out.println("|              1: EMPLOYEE                 |");
			System.out.println("|              2: GUEST                    |");
			System.out.println("|           QUIT: QUIT                     |");
			System.out.println("|                                          |");
			System.out.println("|                                          |");
			System.out.println("<==========================================>");

		}		

}
