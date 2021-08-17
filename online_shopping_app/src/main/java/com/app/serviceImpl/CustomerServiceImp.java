package com.app.serviceImpl;

import com.app.businessException.BusinessException;
import com.app.dao.CustomerDAO;
import com.app.daoImpl.CustomerDAOImp;
import com.app.model.Customer;
import com.app.service.CustomerService;

public class CustomerServiceImp implements CustomerService {

	CustomerDAO customerDAO = new CustomerDAOImp();

	@Override
	public Boolean checkValidCredentials(String username, String password) throws BusinessException {
		boolean login = false;
		if (username.length() < 3 || password.length() < 6) {
			throw new BusinessException("Please enter valid Username and Password!");
		} else {
			login = customerDAO.checkValidCredentials(username, password);
		}
		return login;
	}

	@Override
	public int createAccount(Customer customer) throws BusinessException {
		int c = 0;
		CustomerDAO customerDAO = new CustomerDAOImp();
		c = customerDAO.createAccount(customer);

		return c;
	}

}
