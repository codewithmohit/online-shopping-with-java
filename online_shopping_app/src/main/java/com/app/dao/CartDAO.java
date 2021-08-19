package com.app.dao;

import java.util.List;

import com.app.businessException.BusinessException;
import com.app.model.Cart;


public interface CartDAO {
	
	public int addProductInCart(int productId,int customerId) throws BusinessException;
	
	public List<Cart> getProductFromCart(int customerId) throws BusinessException;
	
	public int deleteProductInCart(int customerId) throws BusinessException;
}
