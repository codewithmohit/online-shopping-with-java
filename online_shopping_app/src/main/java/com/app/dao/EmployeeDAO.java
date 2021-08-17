package com.app.dao;

import com.app.businessException.BusinessException;

public interface EmployeeDAO {

	public Boolean checkValidCredentials(String username, String password) throws BusinessException;

}
