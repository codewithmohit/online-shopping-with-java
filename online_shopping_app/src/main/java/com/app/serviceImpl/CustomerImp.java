package com.app.serviceImpl;

import com.app.businessException.BusinessException;
import com.app.dao.CustomerDAO;
import com.app.daoImpl.CustomerDAOImp;
import com.app.service.Customer;

public class CustomerImp implements Customer {

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

}
