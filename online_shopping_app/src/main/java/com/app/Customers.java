package com.app;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.businessException.BusinessException;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.Order;

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

public class Customers {
	static Scanner scanner = new Scanner(System.in);
	static Logger log = Logger.getLogger(Main.class);
	static ProductService productService = new ProductServiceImp();
	static CartService cartService = new CartServiceImpl();
	static OrderService orderService = new OrderServiceImpl();
	static CustomerService customerService = new CustomerServiceImp();
	static Customer customer = new Customer();
	static EmployeeService employeeLogin = new EmployeeServiceImp();
	
	
	// ***************************Customer Portal ****************************
		public static void customersPortal() {

			log.info("\n**** Welcome to Customer Portal****");
			int choice = 0;
			do {
				log.info("**********************************");
				log.info("1)Login");
				log.info("2)Create Accoount");
				log.info("3)Back to Main Menu");
				log.info("Enter your choice between 1-3");
				try {
					choice = Integer.parseInt(scanner.nextLine());
					switch (choice) {
					case 1:
						customerLogin();
						break;
					case 2:
						createAccountForCustomer();
						break;
					case 3:
						log.info("***Going to main menu***");
						break;
					default:
						log.warn("Please enter valid choice (1-3)\n");
					}

				} catch (NumberFormatException e) {
					log.info("Entry is not appropriate. Please Enter Valid Choice\n");
				}
			} while (choice != 3);
		}
		
		// ************************* Customer Login *****************************
		public static void customerLogin() {

			int choice = 0;
			int custChance = 0;
			do {
				log.info("\n**** Welcome to Login Portal****");
				log.info("**********************************");
				log.info("Enter your username");
				String username = scanner.nextLine();
				log.info("Enter your password");
				String password = scanner.nextLine();

				try {
					customer = customerService.checkValidCredentials(username, password);
					if (customer != null) {

						log.info("Login Successfully!!!!!!!");
						log.info("Welcome " + customer.getCustomerName() + ", What you wanna do today?");

						do {
							log.info("**********************************");
							log.info("1)View Products");
							log.info("2)Search Products");
							log.info("3)View Orders");
							log.info("4)View Cart");
							log.info("5)Mark Order as Received!");
							log.info("6)LogOut");

							log.info("Enter your choice-->");
							try {
								choice = Integer.parseInt(scanner.nextLine());
								switch (choice) {
								case 1:
									ViewAllProduct.viewAllProduct();
									break;
								case 2:
									SearchProduct.searchProductByFilter();
									break;
								case 3:
									viewOrderForCustomer();
									break;
								case 4:
									viewCartAndOrder();
									break;
								case 5:
									MarkOrderStatus.markOrderStatus(2, "Received");
									break;
								case 6:
									log.info("Logout Successfully");
									custChance = 5;
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
					custChance++;
					if (custChance > 0)
						log.info("\nRemain chance to try login again is " + (5 - custChance) + "\n");
				}
			} while (custChance < 5);
		}
		
		
		// ********************* View Order for Customer*************************
		public static void viewOrderForCustomer() {

			List<Order> orderList;
			try {
				orderList = orderService.getOrderList(customer.getCustomerId());
				log.info("There are " + orderList.size() + " Orders and details are below --> \n");
				for (Order order : orderList)
					log.info(order);
			} catch (BusinessException e) {
				log.info(e.getMessage());
			}

		}
		
		// ********************* Mark View Order for Customer*************************
		public static void markViewOrderForCustomer() {

			List<Order> orderList;
			try {
				orderList = orderService.markGetOrderList(customer.getCustomerId());
				log.info("There are " + orderList.size() + " Orders and details are below --> \n");
				for (Order order : orderList)
					log.info(order);
			} catch (BusinessException e) {
				log.info(e.getMessage());
			}

		}
		
		
		// ******************** View cart and Order ***************************

		public static void viewCartAndOrder() {

			try {
				List<Cart> cartList = cartService.getProductFromCart(customer.getCustomerId());
				log.info("There are " + cartList.size() + " product in Cart and details are below --> \n");

				double totalPrice = 0.0d;
				for (Cart cart : cartList) {
					log.info(cart);
					totalPrice += cart.getPrice();
				}
				log.info("\nTotal Price is " + totalPrice);
				int chance = 0;

				do {
					log.info("**********************************");
					log.info("Do you wish to place order for above products?");
					log.info("1)Yes");
					log.info("2)No");

					try {
						chance = Integer.parseInt(scanner.nextLine());
						switch (chance) {
						case 1:
							int customerId = customer.getCustomerId();
							int result = 0;
							for (Cart cart : cartList) {
								int productId = cart.getProductId();
								double productPrice = cart.getPrice();
								result = orderService.createOrder(customerId, productId, productPrice);
							}
							if (result == 1) {
								if (cartService.deleteProductInCart(customer.getCustomerId()) == 1) {
									log.info("Ordered Placed successfully. You can view the status of order"
											+ " in Main menu. Thanks for Shopping!!!!!");
								}
							}
							break;
						case 2:
							log.info("***Going to Previous menu***");
							break;
						default:
							log.warn("Please enter valid choice (1-2)\n");
						}
					} catch (NumberFormatException e) {
						log.info("Entry is not appropriate. Please Enter Valid Choice\n");
						continue;
					} catch (BusinessException e) {
						log.info(e.getMessage());
						continue;
					}
				} while (chance != 1 && chance != 2);

			} catch (BusinessException e) {
				log.info(e.getMessage());
			}
		}
		
		// ********************** Create Account for customer********************
		public static void createAccountForCustomer() {
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

			Customer customer = new Customer(customerName, customerUsername, customerPassword, customerEmail);
			try {
				int c = accountCreate.createAccount(customer);
				if (c == 1) {
					log.info("Account create Successfully!!!!!!!!!!");
				}
			} catch (BusinessException e) {
				log.warn(e.getMessage());
			}

		}


}
