package com.app.service;

import com.app.businessException.BusinessException;

public interface EmployeeService {

	public Boolean checkValidCredentials(String username, String password) throws BusinessException;
}
