package com.app.service;

import java.util.List;

import com.app.businessException.BusinessException;
import com.app.model.Cart;
import com.app.model.Order;

public interface OrderService {
	public int createOrder(List<Cart> cartList) throws BusinessException;
	
	public List<Order> getOrderList(int customerId) throws BusinessException;
	
	public List<Order> markGetOrderList(int customerId) throws BusinessException;
	
	public List<Order> getOrderList() throws BusinessException;
	
	public int updateOrderStatus(int orderId,String status) throws BusinessException;
}
