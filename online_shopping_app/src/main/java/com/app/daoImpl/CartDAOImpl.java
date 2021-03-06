package com.app.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.businessException.BusinessException;
import com.app.dao.CartDAO;
import com.app.dao.dbutils.MyDbConnection;
import com.app.model.Cart;


public class CartDAOImpl implements CartDAO{
	
	
	// ********************  Add Product In Cart **********************************
	@Override
	public int addProductInCart(int productId,int customerId) throws BusinessException {
		
		int c;
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "insert into cart(ca_cu_id,ca_pr_id) values (?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,customerId);
			preparedStatement.setInt(2,productId);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage()+ " Internal Problem Occured. Contact sysAdmin!");
		}
		return c;
	}
	
	// ********************  Get Product from Cart **********************************
	@Override
	public List<Cart> getProductFromCart(int customerId) throws BusinessException {
		List<Cart> cartList = new ArrayList<>();
		
		try (Connection connection = MyDbConnection.getConnection()) {
			
			String sql = "select cu_id,pro_id,pro_name,pro_price from cart join product on ca_pr_id = pro_id join customer on ca_cu_id = cu_id where cu_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet = preparedStatement.executeQuery();			
			while(resultSet.next()) {
				Cart cart = new Cart();
				cart.setCustomerId(customerId);
				cart.setProductId(resultSet.getInt("pro_id"));
				cart.setProductName(resultSet.getString("pro_name"));
				cart.setPrice(resultSet.getDouble("pro_price"));
				cartList.add(cart);
			}
			if(cartList.size()<1) {
				throw new BusinessException("Sorry Cart is Empty!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage()+ " Internal Problem Occured. Contact sysAdmin!");
		}
		return cartList;
	}
	
	// ********************  Delete Product from Cart **********************************
	@Override
	public int deleteProductInCart(int customerId) throws BusinessException {
		int c = 0;
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "delete from cart where ca_cu_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,customerId);
			c = preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage()+ " Internal Problem Occured. Contact sysAdmin!");
		}
		return c;
	}

}
