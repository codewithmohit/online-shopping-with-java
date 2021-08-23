package com.app;

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

public class ViewAllProduct {
	static Scanner scanner = new Scanner(System.in);
	static Logger log = Logger.getLogger(Main.class);
	static ProductService productService = new ProductServiceImp();
	static CartService cartService = new CartServiceImpl();
	static OrderService orderService = new OrderServiceImpl();
	static CustomerService customerService = new CustomerServiceImp();
	static Customer customer = new Customer();
	static EmployeeService employeeLogin = new EmployeeServiceImp();
	
	// ************** View All Products ********************************
		public static void viewAllProduct() {

			log.info("Products Details are Below----->");
			List<Product> productList;
			try {
				productList = productService.getAllProducts();
				for (Product product : productList) {
					log.info(product);
				}
				log.info("\n");
			} catch (BusinessException e) {
				log.info(e.getMessage());
			}
		}
}
