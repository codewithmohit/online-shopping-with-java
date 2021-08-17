package com.app.service;

import com.app.businessException.BusinessException;

public interface Employee {

	public Boolean checkValidCredentials(String username, String password) throws BusinessException;
}
