package com.app;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.businessException.BusinessException;
import com.app.service.Customer;
import com.app.service.Employee;
import com.app.serviceImpl.CustomerImp;
import com.app.serviceImpl.EmployeeImp;

public class Main {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		Logger log = Logger.getLogger(Main.class);

		log.info("Welcome To Online Shopping App!");
		log.info("Who you are");
		log.info("**********************************");
		int ch = 0;
		do {
			log.info("1) Employee");
			log.info("2) Customer");
			log.info("3) Exit");

			log.info("Enter your choice between 1-3");
			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				log.info("Entry is not appropriate. Please Enter Valid Choice\n");
				continue;
			}
			switch (ch) {
			case 1:
				Employee employeeLogin = new EmployeeImp();
				int emploChance = 0;
				do {

					log.info("\n**** Welcome to Employee Login Portal****");
					log.info("Enter your username");
					String username = scanner.nextLine();
					log.info("Enter your password");
					String password = scanner.nextLine();

					try {
						boolean valid = employeeLogin.checkValidCredentials(username, password);
						if (valid) {
							log.info("Work under construction!!!");
						}
					} catch (BusinessException e) {
						log.info(e.getMessage());
						emploChance++;
						if (emploChance > 0)
							log.info("\nRemain chance to try login again is " + (5 - emploChance) + "\n");
					}
				} while (emploChance < 5);
				break;
			case 2:
				log.info("\n**** Welcome to Customer Portal****");
				log.info("1)Login");
				log.info("2)Create Accoount");
				log.info("3)Back to Main Menu");
				log.info("Enter your choice between 1-3");

				int option = Integer.parseInt(scanner.nextLine());

				switch (option) {
				case 1:
					Customer customerLogin = new CustomerImp();
					int custChance = 0;
					do {
						log.info("\n**** Welcome to Login Portal****");
						log.info("Enter your username");
						String username = scanner.nextLine();
						log.info("Enter your password");
						String password = scanner.nextLine();

						try {
							boolean valid = customerLogin.checkValidCredentials(username, password);
							if (valid) {
								log.info("Work under construction!!!");
							}
						} catch (BusinessException e) {
							log.info(e.getMessage());
							custChance++;
							if (custChance > 0)
								log.info("\nRemain chance to try login again is " + (5 - custChance) + "\n");

						}
					} while (custChance < 5);
					break;
				case 2:
					break;
				case 3:
					log.info("***Going to main menu***");
					break;
				}

				break;
			case 3:
				log.info("Thanks for using our Shopping app. see you again!");
				break;
			default:
				log.warn("Please enter valid choice (1-3)\n");
			}

		} while (ch != 3);

	}

}
