package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessorObject;



public class Employee {
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

	public void runEmployeeFunctions() {
		int employeeID = enterEmployeeId();
		Employee user = null;
		if (employeeID !=0) {
			DatabaseAccessorObject dAO = new DatabaseAccessorObject();
			try {
				user =	dAO.findEmployeeByID(employeeID);
			if (user == null) { 
				printNoIDFound();
			}else {
				printPromptForPassword(user);
				enterPassword(user);
			}
			} catch (SQLException e) {
				System.out.println("We have encountered an error. Please try again");
			}
		}
		
		
	}
	
	
	
	
	
	
	
	
	private void enterPassword(Employee user) {
		int passTryCount = 0;
		while (passTryCount < 3) {
			String enteredPassword = input.nextLine();
			if(!enteredPassword.equals(user.password)) {
				printInvalidPassword();
				passTryCount++;
		}else {
			searchMenu();
		}
		}
	}








	private void printInvalidPassword() {
		System.out.println("\n\n\n<================================>");
		System.out.println("|                                |");
		System.out.println("|                                |");
		System.out.println("|       PASSWORD INCORRECT       |");
		System.out.println("|                                |");
		System.out.println("|                                |");
		System.out.println("|      PLEASE ENTER A VALID      |");
		System.out.println("|             PASSWORD           |");
		System.out.println("|                                |");
		System.out.println("<================================>");		
	}



	  public static String padLeftSpaces(String str, int n) {
		    return String.format("%1$" + n + "s", str);
		  }

	 public static String rightPadding(String str, int num) {
		    return String.format("%1$-" + num + "s", str);
		  }


	private void printPromptForPassword(Employee user) {
		int rightPad = 15;
		int leftPad = 16;
//		String paddedName = name + new String(new char[width - name.length()]).replace((char) 32, fill);
		System.out.println("\n\n\n<================================>");
		System.out.println("|                                |");
		System.out.println("|              HELLO             |");
		System.out.println("|                                |");
		System.out.println("|" + padLeftSpaces(user.firstName, leftPad) + " " + rightPadding(user.lastName, rightPad) + "|");
		System.out.println("|                                |");
		System.out.println("|        PLEASE ENTER YOUR       |");
		System.out.println("|            PASSWORD            |");
		System.out.println("|                                |");
		System.out.println("<================================>");}
		
	








	private void printNoIDFound() {
		System.out.println("\n\n\n<================================>");
		System.out.println("|                                |");
		System.out.println("|                                |");
		System.out.println("|      NO EMPLOYEES WERE FOUND   |");
		System.out.println("|         MATCHING THAT ID #     |");
		System.out.println("|                                |");
		System.out.println("|      PLEASE ENTER A VALID      |");
		System.out.println("|           EMPLOYEE ID #        |");
		System.out.println("|                                |");
		System.out.println("<================================>");}

		
	








	private int enterEmployeeId() {
		int employeeInput = 0;
		boolean keepGoing = true;
		while (keepGoing) {
		try {
			employeeInput = Integer.parseInt(input.nextLine());
			keepGoing = false;
		} catch (NumberFormatException e) {
			printNoIDFound();
		}
		}
		return employeeInput;
	}
	private void searchMenu() {
		System.out.println("\n\n\n<================================>");
		System.out.println("|                                |");
		System.out.println("|           SEARCH MENU          |");
		System.out.println("|                                |");
		System.out.println("|   1: BY FILM ID                |");
		System.out.println("|   2: BY ACTOR ID               |");
		System.out.println("|   3: BY INVENTORY ID           |");
		System.out.println("|   4: BY FILM TITLE             |");
		System.out.println("|   5: BY ACTOR                  |");
		System.out.println("|   6: QUIT                      |");
		System.out.println("|                                |");
		System.out.println("<================================>");}








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
