package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.businessException.BusinessException;
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

public class SearchProduct {
	
	static Scanner scanner = new Scanner(System.in);
	static Logger log = Logger.getLogger(Main.class);
	static ProductService productService = new ProductServiceImp();
	static CartService cartService = new CartServiceImpl();
	static OrderService orderService = new OrderServiceImpl();
	static CustomerService customerService = new CustomerServiceImp();
	static Customer customer = new Customer();
	static EmployeeService employeeLogin = new EmployeeServiceImp();
	// ***************Search Product By Various Filter *****************
	public static void searchProductByFilter() {
		int choice = 0;
		do {
			log.info("**********************************");
			log.info("Welcome to Product Search(You can search a product from various criteria from below menu--->)");
			log.info("1)By Name");
			log.info("2)By Category");
			log.info("3)Previous Menu");
			log.info("Enter your choice-->");
			try {
				choice = Integer.parseInt(scanner.nextLine());
				switch (choice) {
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

					productListByCategory = productService.getProductByCategory(productCategory);
					for (Product product : productListByCategory) {
						log.info(product);
					}
					break;
				case 3:
					log.info("***Going to Previous Menu***");
					break;
				default:
					log.warn("Please enter valid choice (1-3)\n");
				}
			} catch (NumberFormatException e) {
				log.info("Entry is not appropriate. Please Enter Valid Choice\n");
				continue;
			} catch (BusinessException e) {
				log.info(e.getMessage());
				continue;
			}
		} while (choice != 3);
	}

}
