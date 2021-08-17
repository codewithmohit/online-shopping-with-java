package com.app.loginImpl;

import com.app.businessException.BusinessException;
import com.app.dao.EmployeeDAO;
import com.app.daoImpl.EmployeeDAOImpl;
import com.app.service.Login;

public class loginImpl implements Login {
	EmployeeDAO dao = new EmployeeDAOImpl();

	@Override
	public Boolean checkValidCredentials(String username, String password) throws BusinessException {
		boolean login = false;
		if (username.length() < 4 || password.length() < 6) {
			throw new BusinessException("Please enter valid Username and Password!");
		} else {
			login = dao.checkValidCredentials(username, password);
		}
		return login;
	}

}
