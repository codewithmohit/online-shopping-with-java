package com.app.dao;

import com.app.businessException.BusinessException;

public interface CartDAO {
	
	public int addProductInCart(int productId) throws BusinessException;
}
