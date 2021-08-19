package com.app.service;

import java.util.List;

import com.app.businessException.BusinessException;
import com.app.model.Customer;

public interface CustomerService {
	public Customer checkValidCredentials(String username, String password) throws BusinessException;

	public int createAccount(Customer customer) throws BusinessException;
	
	public Customer getCustomerByCustomerId(int customerId) throws BusinessException;
	
	public List<Customer> getCustomerByName(String customerName) throws BusinessException;
	
	public Customer getCustomerByEmail(String customerEmail) throws BusinessException;
	
	public Customer getCustomerByOrderId(int orderId) throws BusinessException;
	
	
	

}
