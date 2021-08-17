package com.app;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.businessException.BusinessException;
import com.app.model.Customer;
import com.app.model.Product;
import com.app.service.CustomerService;
import com.app.service.EmployeeService;
import com.app.service.ProductService;
import com.app.serviceImpl.CustomerServiceImp;
import com.app.serviceImpl.EmployeeServiceImp;
import com.app.serviceImpl.ProductServiceImp;

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
				EmployeeService employeeLogin = new EmployeeServiceImp();
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
							log.info("Login Successfully!!!!!!!");
							log.info("Welcome " + username + ",What you wanna do today?");
							int op = 0;
							do {
								log.info("1)add Product");
								log.info("2)Search Products By filter");
								log.info("3)Mark the Status of Order");
								log.info("4)Search Customer By filter");
								log.info("5)LogOut");
								log.info("Enter your choice between 1-5");
								try {
									op = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
									log.info("Entry is not appropriate. Please Enter Valid Choice\n");
									continue;
								}
								switch (op) {
								case 1:
									log.info("Enter Product Name ::");
									String productName = scanner.nextLine();
									log.info("Enter Product Price ::");
									double productPrice = Double.parseDouble(scanner.nextLine());
									log.info("Enter Product rating ::");
									double productRating = Double.parseDouble(scanner.nextLine());
									log.info("Enter Product Category Id ::");
									int productCategoryId = Integer.parseInt(scanner.nextLine());
									Product product = new Product(productName, productPrice, productRating,
											productCategoryId);

									ProductService productService = new ProductServiceImp();

									try {
										int c = productService.addProduct(product);
										if (c == 1) {
											log.info("Product add Successfully!!!!!!!!!!");
										}
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									}
									break;
								case 2:
									break;
								case 3:
									break;
								case 4:
									break;
								case 5:
									log.info("Logout Successfully");
									break;
								default:
									log.warn("Please enter valid choice (1-5)\n");
								}

							} while (op != 5);
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
				int option = 0;
				do {
					log.info("1)Login");
					log.info("2)Create Accoount");
					log.info("3)Back to Main Menu");
					log.info("Enter your choice between 1-3");
					try {
						option = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
						log.info("Entry is not appropriate. Please Enter Valid Choice\n");
						continue;
					}
					switch (option) {
					case 1:
						CustomerService customerLogin = new CustomerServiceImp();
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
									log.info("Login Successfully!!!!!!!");
									log.info("Welcome " + username + ",What you wanna do today?");
									int op = 0;
									do {
										log.info("1)View Products");
										log.info("2)Search Products");
										log.info("3)View Orders");
										log.info("4)LogOut");

										log.info("Enter your choice between 1-");
										try {
											op = Integer.parseInt(scanner.nextLine());
										} catch (NumberFormatException e) {
											log.info("Entry is not appropriate. Please Enter Valid Choice\n");
											continue;
										}
										switch (op) {
										case 1:
											break;
										case 2:
											break;
										case 3:
											break;
										case 4:
											log.info("Logout Successfully");
											break;
										default:
											log.warn("Please enter valid choice (1-4)\n");
										}
									} while (op != 4);
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
						CustomerService accountCreate = new CustomerServiceImp();

						log.info("\n**** Welcome to Signup Portal****");
						log.info("Enter Name-->");
						String customerName = scanner.nextLine();
						log.info("Enter Username-->");
						String customerUsername = scanner.nextLine();
						log.info("Enter Password-->");
						String customerPassword = scanner.nextLine();
						log.info("Enter Email-Id-->");
						String customerEmail = scanner.nextLine();

						Customer customer = new Customer(customerName, customerUsername, customerPassword,
								customerEmail);
						try {
							int c = accountCreate.createAccount(customer);
							if (c == 1) {
								log.info("Account create Successfully!!!!!!!!!!");
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						break;
					case 3:
						log.info("***Going to main menu***");
						break;
					default:
						log.warn("Please enter valid choice (1-3)\n");
					}

				} while (option != 3);
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
