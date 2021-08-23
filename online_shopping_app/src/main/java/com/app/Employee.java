package com.app;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.businessException.BusinessException;
import com.app.model.Customer;
import com.app.model.Order;
import com.app.model.Product;
import com.app.service.CartService;
import com.app.service.CustomerService;
import com.app.service.EmployeeService;
import com.app.service.OrderService;
import com.app.service.ProductService;
import com.app.serviceImpl.CartServiceImpl;
import com.app.serviceImpl.CustomerServiceImp;
import com.app.serviceImpl.EmployeeServiceImp;
import com.app.serviceImpl.OrderServiceImpl;
import com.app.serviceImpl.ProductServiceImp;

public class Employee {
	
	static Scanner scanner = new Scanner(System.in);
	static Logger log = Logger.getLogger(Main.class);
	static ProductService productService = new ProductServiceImp();
	static CartService cartService = new CartServiceImpl();
	static OrderService orderService = new OrderServiceImpl();
	static CustomerService customerService = new CustomerServiceImp();
	static Customer customer = new Customer();
	static EmployeeService employeeLogin = new EmployeeServiceImp();
	
	// ********************* Employee Portal ****************************

		public static void employeesPortal() {
			int choice = 0;
			do {
				log.info("**********************************");
				log.info("\n**** Welcome to Employee Login Portal****");
				log.info("1) login");
				log.info("2) Go to main menu");
				try {
					choice = Integer.parseInt(scanner.nextLine());
					switch (choice) {
					case 1:
						employeeLogin();
						break;
					case 2:
						log.info("*****Going to main menu******");
						break;
					default:
						log.warn("Please enter valid choice (1-2)\n");
					}

				} catch (NumberFormatException e) {
					log.info("Entry is not appropriate. Please Enter Valid Choice\n");
					continue;
				}
			} while (choice != 2);
		}
		
		
		// ******************** Employee Login **************************

		public static void employeeLogin() {
			int emploChance = 0;
			do {
				log.info("Enter your username");
				String username = scanner.nextLine();
				log.info("Enter your password");
				String password = scanner.nextLine();

				try {
					if (employeeLogin.checkValidCredentials(username, password)) {

						log.info("Login Successfully!!!!!!!");
						log.info("Welcome " + username + ",What you wanna do today?\n");
						int choice = 0;
						do {
							log.info("**********************************");
							log.info("1)add Product");
							log.info("2)View Products");
							log.info("3)Search Products By filter");
							log.info("4)Search Customer By filter");
							log.info("5)Mark Order as Shipped!");
							log.info("6)LogOut");
							log.info("Enter your choice between 1-6");
							try {
								choice = Integer.parseInt(scanner.nextLine());

								switch (choice) {
								case 1:
									addProduct();
									break;
								case 2:
									ViewAllProduct.viewAllProduct();
									break;
								case 3:
									SearchProduct.searchProductByFilter();
									break;
								case 4:
									searchCustomerByFilter();
									break;
								case 5:
									MarkOrderStatus.markOrderStatus(1, "Shipped");
									break;
								case 6:
									log.info("Logout Successfully");
									emploChance = 5;
									break;
								default:
									log.warn("Please enter valid choice (1-5)\n");
								}
							} catch (NumberFormatException e) {
								log.info("Entry is not appropriate. Please Enter Valid Choice\n");
								continue;
							} 
						} while (choice != 6);
					}
				} catch (BusinessException e) {
					log.info(e.getMessage());
					emploChance++;
					if (emploChance > 0)
						log.info("\nRemain chance to try login again is " + (5 - emploChance) + "\n");
				}
			} while (emploChance < 5);

		}
		
		// ********************* View Order for Employees*********************
		public static void markViewOrderForEmployees() {

			List<Order> orderList;
			try {
				orderList = orderService.getOrderList();
				log.info("There are " + orderList.size() + " Orders and details are below --> \n");
				for (Order order : orderList)
					log.info(order);
			} catch (BusinessException e) {
				log.info(e.getMessage());
			}

		}
		
		
		// ************ Search customer By Various Filter *******************

		public static void searchCustomerByFilter() {

			int choice = 0;
			do {
				log.info("**********************************");
				log.info("\nWelcome to Search Customer(You can search Customer from various criteria from below menu--->)");
				log.info("1)By Customer Id");
				log.info("2)By Name");
				log.info("3)By Email Id");
				log.info("4)By Order Id");
				log.info("5)Previous Menu");
				log.info("Enter your choice-->");
				try {
					choice = Integer.parseInt(scanner.nextLine());
					switch (choice) {
					case 1:
						log.info("Enter Customer Id to find Customer-->");
						int customerId = Integer.parseInt(scanner.nextLine());

						Customer result1 = customerService.getCustomerByCustomerId(customerId);

						if (result1 != null) {
							log.info(result1);
						}
						break;
					case 2:
						log.info("Enter Customer Name to find Customer-->");
						String name = scanner.nextLine();

						List<Customer> customerList = customerService.getCustomerByName(name);

						if (customerList.size() > 0) {
							for (Customer customer : customerList) {
								log.info(customer);
							}
						}
						break;
					case 3:
						log.info("Enter Customer Email-Id to find Customer-->");
						String email = scanner.nextLine();

						Customer result3 = customerService.getCustomerByEmail(email);

						if (result3 != null) {
							log.info(result3);
						}
						break;
					case 4:
						log.info("Enter Order-Id to find Customer-->");
						int orderId = Integer.parseInt(scanner.nextLine());

						Customer result4 = customerService.getCustomerByOrderId(orderId);

						if (result4 != null) {
							log.info(result4);
						}
						break;
					case 5:
						log.info("*********** Going to previous menu *******************");
						break;
					default:
						log.warn("Please enter valid choice (1-5)\n");

					}
				} catch (NumberFormatException e) {
					log.info("Entry is not appropriate. Please Enter Valid Choice\n");
				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}
			} while (choice != 5);
		}
		
		
		// **************** Add Product in Product Table By Employee***************

		public static void addProduct() {

			int productCategoryId = 0;
			double productPrice = 0.0d;
			double productRating = 0.0d;

			log.info("Enter Product Name ::");
			String productName = scanner.nextLine();
			log.info("Enter Product Price ::");
			try {
				productPrice = Double.parseDouble(scanner.nextLine());
				log.info("Enter Product rating ::");
				productRating = Double.parseDouble(scanner.nextLine());
				log.info("Enter Product Category Id ::");
				productCategoryId = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				log.info("Entry is not appropriate. Please Enter Valid Input\n");
			}
			Product product = new Product(productName, productPrice, productRating, productCategoryId);

			try {
				int c = productService.addProduct(product);
				if (c == 1) {
					log.info("Product add Successfully!!!!!!!!!!");
				}
			} catch (BusinessException e) {
				log.warn(e.getMessage());
			}
		}
}
