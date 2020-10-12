package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.sysoutformatting.Align;

public class EmployeeSearch {
	private int id;
	private String firstName;
	private String lastName;
	private int addressID;
	private String email;
	private int storeID;
	private int supervisorID;
	private boolean active;
	private String userName;
	private String password;
	Scanner input = new Scanner(System.in);

	public boolean runEmployeeFunctions() {
		boolean keepGoing = true;
		int employeeID = enterEmployeeId();
		EmployeeSearch user = null;
		if (employeeID != 0) {
			DatabaseAccessorObject dAO = new DatabaseAccessorObject();
			user = dAO.findEmployeeByID(employeeID);
			if (user == null) {
				printNoIDFound();
			} else {
				while (keepGoing) {
					printPromptForPassword(user);
					keepGoing = enterPassword(user);
					searchSwitch();
					keepGoing = false;
				}
			}
		} else {
			keepGoing = false;
			return keepGoing;
		}
		return keepGoing;
	}

	private boolean searchSwitch() {
			boolean keepGoing = true;
			DatabaseAccessorObject run = new DatabaseAccessorObject();
			String userSearch = "";
			boolean tryAgain = true;
			
			  while(keepGoing) {
				  searchMenu();
				  String userInput = input.nextLine().toLowerCase();
			  switch (userInput) {
			  case "1": case "film": case "film id": case "id":
				  int filmIDSearch;
				  while(tryAgain) {
				try {
					userSearch = promptForSearchParameter(1);
					filmIDSearch = Integer.parseInt(userSearch);
					Film searchFilmByIDReturn = run.findFilmById(filmIDSearch);
					run.printIndividualFilm(searchFilmByIDReturn);
					tryAgain = false;
				} catch (NumberFormatException e) {
					System.out.println("Invalid please enter valid ID");
//					e.printStackTrace();
				}
				  }
				  break;
			  case "2": case "actor id": case "aid":
				  while (tryAgain) {
				  try {
					int actorId = Integer.parseInt(userSearch);
					  userSearch = promptForSearchParameter(2);
					  List<Film> filmsByRating = run.findFilmsByActorId(actorId);
					  run.printFilmsList(filmsByRating);
					  tryAgain = false;
				} catch (NumberFormatException e) {
					System.out.println("Invalid please enter valid ID");
//					e.printStackTrace();
				}
				  }
				  break;
			  case "3": case "inventory": case "inv": case "inventory id": case "iid": case "i":
				  while (tryAgain) {
				  try {
					userSearch = promptForSearchParameter(3);
					   int inventoryIDSearchNumber = Integer.parseInt(userSearch);
					   Film filmByInventoryID = run.findFilmByInventoryID(inventoryIDSearchNumber);
					run.printIndividualFilm(filmByInventoryID);
					tryAgain = false;
				} catch (NumberFormatException e) {
					System.out.println("Invalid please enter valid ID");
//					e.printStackTrace()
				}}
				  break;
			  case "4": case "title": case "film title": case "t":
				   userSearch = promptForSearchParameter(4);
				   List<Film> filmsByTitle = run.findFilmByTitle(userSearch);
				  run.printFilmsList(filmsByTitle);
				  break;
			  case "5": case "actor": case "a":
				  userSearch = promptForSearchParameter(5);
				  List<Film> filmsByActor = run.findFilmsByActorName(userSearch);
				  run.printFilmsList(filmsByActor);
				  break;
			  case "6": case "general": case "search": case "general search":
				   userSearch = promptForSearchParameter(6);
				   List<Film> filmsBasedOnSearch = run.findFilmsFromSearch(userSearch);
				   run.printFilmsList(filmsBasedOnSearch);
				  break;
			  case "7": case "menu": case "main":
				  boolean mainMenu = true;
				  return mainMenu;
			  case "8": case "quit": case "q":
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
			searchHeader = "FILM ID";
			
		}else if(searchType == 2) {
			searchHeader = "ACTOR ID";
			
		}else if(searchType == 3) {
			searchHeader = "INVENTORY ID";
			
		}else if(searchType == 4) {
			searchHeader = "FILM TITLE";
			
		}else if(searchType == 5) {
			searchHeader = "ACTOR";
		}else if(searchType == 6) {
			searchHeader = "KEYWORD";
		}
printSearchParameterPrompt(searchHeader);
String userSearch = input.nextLine();
return userSearch;

	}
		

	

	private void printSearchParameterPrompt(String searchHeader) {
		// TODO Auto-generated method stub
		System.out.println("\n\n\n<================================>");
		System.out.println("|                                |");
		System.out.println("|"+ Align.center("PLEASE ENTER YOUR", 32, (char)32) + "|");
		System.out.println("|                                |");
		System.out.println("|"+ Align.center(searchHeader, 32, (char)32) + "|");
		System.out.println("|                                |");
		System.out.println("|"+ Align.center("TO SEARCH", 32, (char)32) + "|");
		System.out.println("|                                |");
		System.out.println("<================================>");}
	

	private boolean enterPassword(EmployeeSearch user) {
		int passTryCount = 0;
		while (passTryCount < 3) {
			String enteredPassword = input.nextLine();
			if (!enteredPassword.equals(user.password)) {
				printInvalidPassword();
				passTryCount++;
			} else {
				passTryCount = 3;
				return true;
			}
		}
		return false;
	}

	private void printInvalidPassword() {
		System.out.print("  ______                 _                       \n"
				+ " |  ____|               | |                      \n"
				+ " | |__   _ __ ___  _ __ | | ___  _   _  ___  ___ \n"
				+ " |  __| | '_ ` _ \\| '_ \\| |/ _ \\| | | |/ _ \\/ _ \\\n"
				+ " | |____| | | | | | |_) | | (_) | |_| |  __/  __/\n"
				+ " |______|_| |_| |_| .__/|_|\\___/ \\__, |\\___|\\___|\n"
				+ "                  | |             __/ |          \n"
				+ "                  |_|            |___/           ");
		System.out.println("\n\n\n<================================>");
		System.out.println("|                                |");
		System.out.println("|                                |");
		System.out.println("|       PASSWORD INCORRECT       |");
		System.out.println("|                                |");
		System.out.println("|                                |");
		System.out.println("|      PLEASE ENTER A VALID      |");
		System.out.println("|            PASSWORD            |");
		System.out.println("|                                |");
		System.out.println("<================================>");
	}



	private void printPromptForPassword(EmployeeSearch user) {
		System.out.print("  ______                 _                       \n"
				+ " |  ____|               | |                      \n"
				+ " | |__   _ __ ___  _ __ | | ___  _   _  ___  ___ \n"
				+ " |  __| | '_ ` _ \\| '_ \\| |/ _ \\| | | |/ _ \\/ _ \\\n"
				+ " | |____| | | | | | |_) | | (_) | |_| |  __/  __/\n"
				+ " |______|_| |_| |_| .__/|_|\\___/ \\__, |\\___|\\___|\n"
				+ "                  | |             __/ |          \n"
				+ "                  |_|            |___/           ");
		System.out.println("\n\n\n<================================>");
		System.out.println("|                                |");
		System.out.println("|" + Align.center("HELLO", 32, (char)32) + "|");
		System.out.println("|                                |");
		System.out.println(
				"|" + Align.center(user.firstName + " " + user.lastName, 32, (char)32) + "|");
		System.out.println("|                                |");
		System.out.println("|" + Align.center("PLEASE ENTER YOUR", 32, (char)32) + "|");
		System.out.println("|" + Align.center("PASSWORD", 32, (char)32) + "|");
		System.out.println("|                                |");
		System.out.println("<================================>");
	}

	private void printNoIDFound() {
		System.out.print("  ______                 _                       \n"
				+ " |  ____|               | |                      \n"
				+ " | |__   _ __ ___  _ __ | | ___  _   _  ___  ___ \n"
				+ " |  __| | '_ ` _ \\| '_ \\| |/ _ \\| | | |/ _ \\/ _ \\\n"
				+ " | |____| | | | | | |_) | | (_) | |_| |  __/  __/\n"
				+ " |______|_| |_| |_| .__/|_|\\___/ \\__, |\\___|\\___|\n"
				+ "                  | |             __/ |          \n"
				+ "                  |_|            |___/           ");
		System.out.println("\n\n\n<================================>");
		System.out.println("|                                |");
		System.out.println("|                                |");
		System.out.println("|      NO EMPLOYEES WERE FOUND   |");
		System.out.println("|         MATCHING THAT ID #     |");
		System.out.println("|                                |");
		System.out.println("|      PLEASE ENTER A VALID      |");
		System.out.println("|           EMPLOYEE ID #        |");
		System.out.println("|                                |");
		System.out.println("<================================>");
	}

	private int enterEmployeeId() {
		printPromptForEmployeeID();
		int employeeInput = 0;
		boolean keepGoing = true;
		while (keepGoing) {
			try {
				String userInput = input.nextLine();
				if (userInput.equals("quit")) {
					break;
				}
				employeeInput = Integer.parseInt(userInput);
				keepGoing = false;
			} catch (NumberFormatException e) {
				printNoIDFound();
			}
		}
		return employeeInput;
	}

	private void printPromptForEmployeeID() {
		System.out.print("  ______                 _                       \n"
				+ " |  ____|               | |                      \n"
				+ " | |__   _ __ ___  _ __ | | ___  _   _  ___  ___ \n"
				+ " |  __| | '_ ` _ \\| '_ \\| |/ _ \\| | | |/ _ \\/ _ \\\n"
				+ " | |____| | | | | | |_) | | (_) | |_| |  __/  __/\n"
				+ " |______|_| |_| |_| .__/|_|\\___/ \\__, |\\___|\\___|\n"
				+ "                  | |             __/ |          \n"
				+ "                  |_|            |___/           ");
		System.out.println("\n\n\n<================================>");
		System.out.println("|                                |");
		System.out.println("|                                |");
		System.out.println("|                                |");
		System.out.println("|       PLEASE ENTER YOUR        |");
		System.out.println("|         EMPLOYEE ID #          |");
		System.out.println("|                                |");
		System.out.println("|                                |");
		System.out.println("|                                |");
		System.out.println("<================================>");

	}

	private void searchMenu() {
		System.out.print("  ______                 _                       \n"
				+ " |  ____|               | |                      \n"
				+ " | |__   _ __ ___  _ __ | | ___  _   _  ___  ___ \n"
				+ " |  __| | '_ ` _ \\| '_ \\| |/ _ \\| | | |/ _ \\/ _ \\\n"
				+ " | |____| | | | | | |_) | | (_) | |_| |  __/  __/\n"
				+ " |______|_| |_| |_| .__/|_|\\___/ \\__, |\\___|\\___|\n"
				+ "                  | |             __/ |          \n"
				+ "                  |_|            |___/           ");
		System.out.println("\n\n\n<================================>");
		System.out.println("|                                |");
		System.out.println("|           SEARCH MENU          |");
		System.out.println("|                                |");
		System.out.println("|    1: BY FILM ID               |");
		System.out.println("|    2: BY ACTOR ID              |");
		System.out.println("|    3: BY INVENTORY ID          |");
		System.out.println("|    4: BY FILM TITLE            |");
		System.out.println("|    5: BY ACTOR                 |");
		System.out.println("|    6: GENERAL SEARCH           |");
		System.out.println("|    7: MAIN MENU                |");
		System.out.println("| QUIT: QUIT                     |");
		System.out.println("|                                |");
		System.out.println("<================================>");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	public int getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(int supervisorID) {
		this.supervisorID = supervisorID;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Scanner getInput() {
		return input;
	}

	public void setInput(Scanner input) {
		this.input = input;
	}

}
