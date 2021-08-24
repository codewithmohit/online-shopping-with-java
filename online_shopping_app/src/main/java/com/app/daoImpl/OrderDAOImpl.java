package com.app.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.businessException.BusinessException;
import com.app.dao.OrderDAO;
import com.app.dao.dbutils.MyDbConnection;
import com.app.model.Cart;
import com.app.model.Order;

public class OrderDAOImpl implements OrderDAO {

	// ********************* Create Order ********************
	@Override
	public int createOrder(List<Cart> cartList) throws BusinessException {

		int c = 0;
		Connection connection = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/online-shopping";
			String username = "root";
			String password = "root";
			connection = DriverManager.getConnection(url, username, password);
			
			connection.setAutoCommit(false);
			
			PreparedStatement preparedStatement;
			String insertSql = "insert into orders(or_cu_id,or_pr_id,or_price,or_status) values(?,?,?,'Ordered')";
			preparedStatement = connection.prepareStatement(insertSql);

			for (Cart cart : cartList) {
				preparedStatement.setInt(1, cart.getCustomerId());
				preparedStatement.setInt(2, cart.getProductId());
				preparedStatement.setDouble(3, cart.getPrice());
//				c = preparedStatement.executeUpdate();
				preparedStatement.addBatch();
			}
//			c = preparedStatement.executeBatch().length;
			preparedStatement.executeBatch();

			String deleteSql = "delete from cart where ca_cu_id=?";
			preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setInt(1, cartList.get(0).getCustomerId());
			c = preparedStatement.executeUpdate();
			
			connection.commit();

		} catch (ClassNotFoundException | SQLException e) {
			try {
				connection.rollback();
				throw new BusinessException(e.getMessage() + " Internal Problem Occured. Contact sysAdmin!");
			} catch (SQLException e1) {
				throw new BusinessException(e1.getMessage() + " Transaction failed!!!!");
			}
			
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new BusinessException(e.getMessage() + " Internal Problem Occured. Contact sysAdmin!");
			}
		}
		return c;

	}

	// ********************* Get Order List by using Customer ID
	// ********************
	@Override
	public List<Order> getOrderList(int customerId) throws BusinessException {
		List<Order> orderList = new ArrayList<>();

		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select or_id,pro_id,pro_name,pro_price,or_status from orders join product on or_pr_id=pro_id join customer on or_cu_id= cu_id where cu_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("or_id"));
				order.setProductId(resultSet.getInt("pro_id"));
				order.setProductName(resultSet.getString("pro_name"));
				order.setPrice(resultSet.getDouble("pro_price"));
				order.setOrderStatus(resultSet.getString("or_status"));
				orderList.add(order);
			}
			if (orderList.size() < 1) {
				throw new BusinessException("You have no orders");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage() + " Internal Problem Occured. Contact sysAdmin!");
		}
		return orderList;
	}

	// ********************* Get Order List ********************
	@Override
	public List<Order> getOrderList() throws BusinessException {
		List<Order> orderList = new ArrayList<>();

		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select or_id,pro_id,pro_name,pro_price,or_status from orders join product on or_pr_id=pro_id join customer on or_cu_id= cu_id where or_status=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "ordered");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("or_id"));
				order.setProductId(resultSet.getInt("pro_id"));
				order.setProductName(resultSet.getString("pro_name"));
				order.setPrice(resultSet.getDouble("pro_price"));
				order.setOrderStatus(resultSet.getString("or_status"));
				orderList.add(order);
			}
			if (orderList.size() < 1) {
				throw new BusinessException("Customers have no orders Yet!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage() + " Internal Problem Occured. Contact sysAdmin!");
		}
		return orderList;
	}

	// ********************* Update Order Status ********************
	@Override
	public int updateOrderStatus(int orderId, String status) throws BusinessException {
		int c = 0;
		try (Connection connection = MyDbConnection.getConnection()) {

			String sql = "update orders set or_status = ? where or_id =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, orderId);

			c = preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {

			throw new BusinessException(e.getMessage() + " Internal Problem Occured. Contact sysAdmin!");
		}
		return c;

	}

	// ******************* Get Shipped Order details **********************
	@Override
	public List<Order> markGetOrderList(int customerId) throws BusinessException {
		List<Order> orderList = new ArrayList<>();

		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select or_id,pro_id,pro_name,pro_price,or_status from orders join product on or_pr_id=pro_id join customer on or_cu_id= cu_id where cu_id=? and or_status=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			preparedStatement.setString(2, "shipped");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("or_id"));
				order.setProductId(resultSet.getInt("pro_id"));
				order.setProductName(resultSet.getString("pro_name"));
				order.setPrice(resultSet.getDouble("pro_price"));
				order.setOrderStatus(resultSet.getString("or_status"));
				orderList.add(order);
			}
			if (orderList.size() < 1) {
				throw new BusinessException("No updates for orders Yet!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage() + " Internal Problem Occured. Contact sysAdmin!");
		}
		return orderList;
	}

}
