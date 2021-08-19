package com.app.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.businessException.BusinessException;
import com.app.dao.OrderDAO;
import com.app.dao.dbutils.MyDbConnection;
import com.app.model.Order;

public class OrderDAOImpl implements OrderDAO{

	@Override
	public int createOrder(int customerId, int productId, double price) throws BusinessException {
		
		int c =0;
		try (Connection connection = MyDbConnection.getConnection()) {
			
			String sql = "insert into orders(or_cu_id,or_pr_id,or_price,or_status) values(?,?,?,'Shipped')";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1,customerId);
			preparedStatement.setInt(2,productId);
			preparedStatement.setDouble(3, price);
			c = preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException(e.getMessage()+" Internal Problem Occured. Contact sysAdmin!");
		}
		return c;
		
	}

	@Override
	public List<Order> getOrderList(int customerId) throws BusinessException {
		List<Order> orderList = new ArrayList<>();
		
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select pro_id,pro_name,pro_price,or_status from orders join product on or_pr_id=pro_id join customer on or_cu_id= cu_id where cu_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet = preparedStatement.executeQuery();			
			while(resultSet.next()) {
				Order order = new Order();
				order.setProductId(resultSet.getInt("pro_id"));
				order.setProductName(resultSet.getString("pro_name"));
				order.setPrice(resultSet.getDouble("pro_price"));
				order.setOrderStatus(resultSet.getString("or_status"));
				orderList.add(order);
			}
			if(orderList.size()<1) {
				throw new BusinessException("You have no orders");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage()+" Internal Problem Occured. Contact sysAdmin!");
		}
		return orderList;
	}

}
