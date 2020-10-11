package com.skilldistillery.filmquery.app;

import java.util.Scanner;


public class Employee {
	Scanner input = new Scanner(System.in);

	public void runEmployeeFunctions() {
		enterEmployeeId();
		
	}
	private int enterEmployeeId() {
		int employeeInput = 0;
		boolean keepGoing = true;
		while (keepGoing) {
		try {
			employeeInput = Integer.parseInt(input.nextLine());
			keepGoing = false;
		} catch (NumberFormatException e) {
			System.out.println("Please Enter a valid Employee ID");
		}
		}
		return employeeInput;
	}
	private void searchMenu() {
		System.out.println("\n\n\n<================================>");
		System.out.println("|           SEARCH MENU          |");
		System.out.println("|                                |");
		System.out.println("|   1: By Name                   |");
		System.out.println("|   2: By Rating                 |");
		System.out.println("|   3: By Category               |");
		System.out.println("|   4: Main Menu                 |");
		System.out.println("|   5: Quit                      |");
		System.out.println("|                                |");
		System.out.println("<================================>");}
	
}
