package com.app.service;

import com.app.businessException.BusinessException;

public interface CartService {
	
	public int addProductInCart(int productId) throws BusinessException;
}
