package com.app;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class Main {

	static Scanner scanner = new Scanner(System.in);
	static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		log.info("Welcome To Online Shopping App!");
		log.info("**********************************");
		int ch = 0;
		do {
			log.info("1) Employee");
			log.info("2) Customer");
			log.info("3) Exit");

			log.info("Enter your choice between 1-3");
			try {
				ch = Integer.parseInt(scanner.nextLine());
				switch (ch) {
				case 1:
					Employee.employeesPortal();
					break;
				case 2:
					Customers.customersPortal();
					break;
				case 3:
					log.info("Thanks for using our Shopping app. see you again!");
					break;
				default:
					log.warn("Please enter valid choice (1-3)\n");
				}
			} catch (NumberFormatException e) {
				log.info("Entry is not appropriate. Please Enter Valid Choice\n");
			}
		} while (ch != 3);

	}

}
