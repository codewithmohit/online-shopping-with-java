package com.app.service;

import com.app.businessException.BusinessException;
import com.app.model.Customer;

public interface CustomerService {
	public Boolean checkValidCredentials(String username, String password) throws BusinessException;

	public int createAccount(Customer customer) throws BusinessException;

}
