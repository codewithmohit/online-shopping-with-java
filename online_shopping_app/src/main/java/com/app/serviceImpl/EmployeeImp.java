package com.app.serviceImpl;

import com.app.businessException.BusinessException;
import com.app.dao.EmployeeDAO;
import com.app.daoImpl.EmployeeDAOImpl;
import com.app.service.Employee;

public class EmployeeImp implements Employee {
	EmployeeDAO employeeDAO = new EmployeeDAOImpl();

	@Override
	public Boolean checkValidCredentials(String username, String password) throws BusinessException {
		boolean login = false;
		if (username.length() < 3 || password.length() < 6) {
			throw new BusinessException("Please enter valid Username and Password!");
		} else {
			login = employeeDAO.checkValidCredentials(username, password);
		}
		return login;
	}

}
