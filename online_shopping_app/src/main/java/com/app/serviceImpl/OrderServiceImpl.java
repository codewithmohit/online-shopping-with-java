package com.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.app.businessException.BusinessException;
import com.app.dao.OrderDAO;
import com.app.daoImpl.OrderDAOImpl;
import com.app.model.Order;
import com.app.service.OrderService;

public class OrderServiceImpl implements OrderService{

	OrderDAO orderDAO = new OrderDAOImpl();
	
	@Override
	public int createOrder(int customerId, int productId, double price) throws BusinessException {
		int c;
		c = orderDAO.createOrder(customerId, productId, price);
		return c;
	}

	@Override
	public List<Order> getOrderList(int customerId) throws BusinessException {
		List<Order> orderList = new ArrayList<>();
		orderList = orderDAO.getOrderList(customerId);
		return orderList;
	}
	
}
