package com.app.dao;

import java.util.List;

import com.app.businessException.BusinessException;
import com.app.model.Order;

public interface OrderDAO {
	
	public int createOrder(int customerId,int productId,double price) throws BusinessException;
	
	public List<Order> getOrderList(int customerId) throws BusinessException;
	
	public List<Order> markGetOrderList(int customerId) throws BusinessException;
	
	public List<Order> getOrderList() throws BusinessException;
	
	public int updateOrderStatus(int orderId,String status) throws BusinessException;
}
