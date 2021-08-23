package com.app;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.businessException.BusinessException;
import com.app.model.Customer;
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

public class MarkOrderStatus {
	
	static Scanner scanner = new Scanner(System.in);
	static Logger log = Logger.getLogger(Main.class);
	static ProductService productService = new ProductServiceImp();
	static CartService cartService = new CartServiceImpl();
	static OrderService orderService = new OrderServiceImpl();
	static CustomerService customerService = new CustomerServiceImp();
	static Customer customer = new Customer();
	static EmployeeService employeeLogin = new EmployeeServiceImp();
	
	// ********************* Mark Order Status ***************************

		public static void markOrderStatus(int id, String status) {
			int choice = 0;
			do {
				if (id == 1) {
					Employee.markViewOrderForEmployees();
				}
				if (id == 2) {
					Customers.markViewOrderForCustomer();
				}
				log.info("********************************");
				log.info("1)Mark Status");
				log.info("2)Go to previous menu");
				try {
					choice = Integer.parseInt(scanner.nextLine());

					switch (choice) {
					case 1:
						log.info("Enter Order id to mark status");
						int orderId = Integer.parseInt(scanner.nextLine());

						if (orderService.updateOrderStatus(orderId, status) == 1)
							log.info("Mark Status is Successfully Done!!");
						break;
					case 2:
						log.info(" ****** going to previous menu *****");
						break;
					default:
						log.warn("Please enter valid choice (1-2)\n");
					}
				} catch (NumberFormatException e) {
					log.info("Entry is not appropriate. Please Enter Valid Choice\n");
				} catch (BusinessException e) {
					log.info(e.getMessage());
				}
			} while (choice != 2);
		}
		
}
