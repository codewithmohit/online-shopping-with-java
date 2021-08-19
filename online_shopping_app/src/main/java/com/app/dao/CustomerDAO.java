package com.app.dao;

import com.app.businessException.BusinessException;
import com.app.model.Customer;

public interface CustomerDAO {
	public Customer checkValidCredentials(String username, String password) throws BusinessException;

	public int createAccount(Customer customer) throws BusinessException;

}
