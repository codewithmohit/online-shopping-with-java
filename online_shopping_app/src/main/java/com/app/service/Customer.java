package com.app.service;

import com.app.businessException.BusinessException;

public interface Customer {
	public Boolean checkValidCredentials(String username, String password) throws BusinessException;
}
