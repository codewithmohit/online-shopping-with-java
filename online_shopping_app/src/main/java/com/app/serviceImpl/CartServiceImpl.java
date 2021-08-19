package com.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.app.businessException.BusinessException;
import com.app.dao.CartDAO;
import com.app.daoImpl.CartDAOImpl;
import com.app.model.Cart;
import com.app.service.CartService;

public class CartServiceImpl implements CartService{
	CartDAO cartDAO = new CartDAOImpl();
	
	@Override
	public int addProductInCart(int productId,int customerId) throws BusinessException {
		int c = cartDAO.addProductInCart(productId,customerId);
		return c;
	}

	@Override
	public List<Cart> getProductFromCart(int customerId) throws BusinessException {
		List<Cart> cart = new ArrayList<>();
		cart = cartDAO.getProductFromCart(customerId);
		return cart;
	}

	@Override
	public int deleteProductInCart(int customerId) throws BusinessException {
		int c = cartDAO.deleteProductInCart(customerId);
		return c;
	}
	

}
