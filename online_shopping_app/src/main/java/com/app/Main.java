package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.businessException.BusinessException;
import com.app.daoImpl.OrderDAOImpl;
import com.app.model.Cart;
import com.app.model.Customer;
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
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

public class Main {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		Logger log = Logger.getLogger(Main.class);
		
		ProductService productService = new ProductServiceImp();
		CartService cartService = new CartServiceImpl();
		OrderService orderService = new OrderServiceImpl();
		
		
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
							log.info("Welcome " + username + ",What you wanna do today?\n");
							int choice = 0;
							do {
								log.info("1)add Product");
								log.info("2)View Products");
								log.info("3)Search Products By filter");
								log.info("4)Mark the Status of Order");
								log.info("5)Search Customer By filter");
								log.info("6)LogOut");
								log.info("Enter your choice between 1-6");
								try {
									choice = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
									log.info("Entry is not appropriate. Please Enter Valid Choice\n");
									continue;
								}
								
								switch (choice) {
								case 1:
									int productCategoryId;
									double productPrice;
									double productRating;
									
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
										continue;
									}
									Product product = new Product(productName, productPrice, productRating,
											productCategoryId);

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
									log.info("Products Details are Below----->");
									List<Product> productList = productService.getAllProducts();
									for (Product prod : productList) {
										log.info(prod);
									}
									break;
								case 3:
									break;
								case 4:
									break;
								case 5:
									break;
								case 6:
									log.info("Logout Successfully");
									break;
								default:
									log.warn("Please enter valid choice (1-5)\n");
								}

							} while (choice != 5);
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
				int choice = 0;
				do {
					log.info("1)Login");
					log.info("2)Create Accoount");
					log.info("3)Back to Main Menu");
					log.info("Enter your choice between 1-3");
					try {
						choice = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
						log.info("Entry is not appropriate. Please Enter Valid Choice\n");
						continue;
					}
					switch (choice) {
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
								Customer customer = customerLogin.checkValidCredentials(username, password);
								if (customer!= null) {
									
									log.info("Login Successfully!!!!!!!");
									log.info("Welcome " + customer.getCustomerName() + ", What you wanna do today?");
									
									do {
										
										log.info("1)View Products");
										log.info("2)Search Products");
										log.info("3)View Orders");
										log.info("4)View Cart");
										log.info("5)LogOut");

										log.info("Enter your choice-->");
										try {
											choice = Integer.parseInt(scanner.nextLine());
										} catch (NumberFormatException e) {
											log.info("Entry is not appropriate. Please Enter Valid Choice\n");
											continue;
										}
										switch (choice) {
//    ********************** Products Details  **********************
										case 1:
											log.info("Products Details are Below----->");											
											List<Product> productList = productService.getAllProducts();
											for (Product product : productList) {
												log.info(product);
											}
//    **********************  Add product in Cart **********************
											do {
											log.info("*******************************************");
											log.info("1)Add any product to Cart");
											log.info("2)Previous Menu");
											try {
												choice = Integer.parseInt(scanner.nextLine());
											} catch (NumberFormatException e) {
												log.info("Entry is not appropriate. Please Enter Valid Choice\n");
												continue;
											}

											 switch(choice) {
											 case 1: 
												 log.info("Enter Product Id to add it to cart-->");
												 int productId;
												 try {
													 productId = Integer.parseInt(scanner.nextLine());
													} catch (NumberFormatException e) {
														log.info("Entry is not appropriate. Please Enter Valid Product id\n");
														continue;
													}
												 
												 int customerId = customer.getCustomerId();
												 if(cartService.addProductInCart(productId,customerId)==1) {
													 log.info("Product "+productId+" added successfully to cart!!!\n");
												 }												 
												 break;
											 case 2:
												 log.info("***Going to Previous Menu***");
												 break;
												default:
													log.warn("Please enter valid choice (1-4)\n");
											 }
											
											}while(choice!=2);
											break;
										case 2:
											
//   ********************** Search a product from various criteria   *************************		
											
											do {
											log.info("Welcome to Product Search(You can search a product from various criteria from below menu--->)");
											log.info("1)By Name");
											log.info("2)By Category");
											log.info("3)View Cart");
											log.info("4)Previous Menu");
											log.info("Enter your choice-->");
											try {
												choice = Integer.parseInt(scanner.nextLine());
											} catch (NumberFormatException e) {
												log.info("Entry is not appropriate. Please Enter Valid Choice\n");
												continue;
											}
											switch(choice) {
											case 1:
												log.info("Enter Product Name to find your Product-->");
												String productName = scanner.nextLine();
												List<Product> productListByName = new ArrayList<>();
												
												productListByName = productService.getProductByName(productName);
												for (Product product : productListByName) {
													log.info(product);
												}
												
												break;
											case 2:
												log.info("Enter Product Category to find your Product-->");
												String productCategory = scanner.nextLine();
												List<Product> productListByCategory = new ArrayList<>();
												
												productListByName = productService.getProductByCategory(productCategory);
												for (Product product : productListByName) {
													log.info(product);
												}
												break;
											case 3:
												break;
											case 4:
												log.info("***Going to Previous Menu***");
												break;
											default:
												log.warn("Please enter valid choice (1-3)\n");
											}
											}while(choice!=4);
											break;
// ************************* View Orders ******************************************
										case 3:
											List<Order> orderList = orderService.getOrderList(customer.getCustomerId());
											log.info("There are "+ orderList.size() +" Orders and details are below --> \n");
											for (Order order : orderList) {
												log.info(order);
											}
											break;
										case 4:
											
											List<Cart> cartList = cartService.getProductFromCart(customer.getCustomerId());
											log.info("There are "+ cartList.size() +" product in Cart and details are below --> \n");
											for (Cart cart : cartList) {
												log.info(cart);
											}
											int option = 0;
											do {
											log.info("\n");
											log.info("Do you wish to place order for above products?");
											log.info("1)Yes");
											log.info("2)No");
											
											try {
												option = Integer.parseInt(scanner.nextLine());
											} catch (NumberFormatException e) {
												log.info("Entry is not appropriate. Please Enter Valid Choice\n");
												continue;
											}
											switch(option) {
											case 1:
												int customerId = customer.getCustomerId();
												int result = 0;
												for (Cart cart : cartList) {
													int productId = cart.getProductId();
													double productPrice = cart.getPrice();
													result = orderService.createOrder(customerId, productId, productPrice);
												}
												if(result ==1) {
													log.info("Ordered Placed successfully. You can view the status of order"
														+ " in Main menu. Thanks for Shopping!!!!!");
												}
												break;
											case 2:
												log.info("***Going to Previous menu***");
												break;
											default:
												log.warn("Please enter valid choice (1-2)\n");
											}
											}while(option!=1 || option!=2);
											break;
										case 5:
											log.info("Logout Successfully");
											break;
										default:
											log.warn("Please enter valid choice (1-4)\n");
										}
									} while (choice != 5);
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

				} while (choice != 3);
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
