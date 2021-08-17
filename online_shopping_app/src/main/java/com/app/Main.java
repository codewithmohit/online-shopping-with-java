package com.app;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.businessException.BusinessException;
import com.app.loginImpl.loginImpl;
import com.app.service.Login;

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
				Login login = new loginImpl();
				log.info("\n**** Welcome to Login Portal****");
				log.info("Enter your username");
				String username = scanner.nextLine();
				log.info("Enter your password");
				String password = scanner.nextLine();

				try {
					boolean valid = login.checkValidCredentials(username, password);
					if (valid) {
						log.info("Work under construction!!!");
					} else {
						log.info("not valid");
					}
				} catch (BusinessException e) {
					log.info(e.getMessage());
				}
				break;
			case 2:
				log.info("Work Under Construction");
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
