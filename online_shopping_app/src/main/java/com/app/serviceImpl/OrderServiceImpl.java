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
		int c = orderDAO.createOrder(customerId, productId, price);
		return c;
	}

	@Override
	public List<Order> getOrderList(int customerId) throws BusinessException {
		List<Order> orderList = new ArrayList<>();
		orderList = orderDAO.getOrderList(customerId);
		return orderList;
	}

	@Override
	public List<Order> getOrderList() throws BusinessException {
		List<Order> orderList = new ArrayList<>();
		orderList = orderDAO.getOrderList();
		return orderList;
	}

	@Override
	public int updateOrderStatus(int orderId,String status) throws BusinessException {
		int c = orderDAO.updateOrderStatus(orderId,status);
		return c;
	}

	@Override
	public List<Order> markGetOrderList(int customerId) throws BusinessException {
		List<Order> orderList = new ArrayList<>();
		orderList = orderDAO.markGetOrderList(customerId);
		return orderList;
	}
	
}
