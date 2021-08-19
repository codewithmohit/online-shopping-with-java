package com.app.serviceImpl;

import java.util.List;

import com.app.businessException.BusinessException;
import com.app.dao.CustomerDAO;
import com.app.daoImpl.CustomerDAOImpl;
import com.app.model.Customer;
import com.app.service.CustomerService;

public class CustomerServiceImp implements CustomerService {

	CustomerDAO customerDAO = new CustomerDAOImpl();

	@Override
	public Customer checkValidCredentials(String username, String password) throws BusinessException {
		Customer customer = null;
		if (username.length() < 3 || password.length() < 6) {
			throw new BusinessException("Please enter valid Username and Password!");
		} else {
			customer = customerDAO.checkValidCredentials(username, password);
		}
		return customer;
	}					
				
	@Override
	public int createAccount(Customer customer) throws BusinessException {
		int c = 0;
		CustomerDAO customerDAO = new CustomerDAOImpl();
		c = customerDAO.createAccount(customer);

		return c;
	}

	@Override
	public Customer getCustomerByCustomerId(int customerId) throws BusinessException {
		Customer customer = null;
		customer = customerDAO.getCustomerByCustomerId(customerId);
		return customer;
	}

	@Override
	public List<Customer> getCustomerByName(String customerName) throws BusinessException {
		List<Customer> customerList = null;
		customerList = customerDAO.getCustomerByName(customerName);
		return customerList;
	}

	@Override
	public Customer getCustomerByEmail(String customerEmail) throws BusinessException {
		Customer customer = null;
		customer = customerDAO.getCustomerByEmail(customerEmail);
		return customer;
	}

	@Override
	public Customer getCustomerByOrderId(int orderId) throws BusinessException {
		Customer customer = null;
		customer = customerDAO.getCustomerByOrderId(orderId);
		return customer;
	}

}
