package com.app.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.Main;
import com.app.businessException.BusinessException;
import com.app.dao.CustomerDAO;
import com.app.dao.dbutils.MyDbConnection;
import com.app.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {
	Logger log = Logger.getLogger(Main.class);

	@Override
	public Customer checkValidCredentials(String username, String password) throws BusinessException {
		Customer customer = null;
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select cu_id,cu_name,cu_username,cu_password,cu_email from customer where cu_username=? and cu_password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer = new Customer();
				customer.setCustomerId(resultSet.getInt("cu_id"));
				customer.setCustomerName(resultSet.getString("cu_name"));
				customer.setCustomerUsername(resultSet.getString("cu_username"));
				customer.setCustomerPassword(resultSet.getString("cu_password"));
				customer.setCustomerEmail(resultSet.getString("cu_email"));
			} else {
				throw new BusinessException("Credentials is not matched with our Database!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal Problem Occured. Contact sysAdmin!");
		}
		return customer;
	}

	@Override
	public int createAccount(Customer customer) throws BusinessException {
		int c = 0;
		try (Connection connection = MyDbConnection.getConnection()) {

			String sql = "insert into customer(cu_name,cu_username,cu_password,cu_email) values(?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getCustomerName());
			preparedStatement.setString(2, customer.getCustomerUsername());
			preparedStatement.setString(3, customer.getCustomerPassword());
			preparedStatement.setString(4, customer.getCustomerEmail());

			c = preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal Problem Occured. Contact sysAdmin!");
		}
		return c;
	}

	@Override
	public Customer getCustomerByCustomerId(int customerId) throws BusinessException {
		Customer customer = null;
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select cu_id,cu_name,cu_username,cu_email from customer where cu_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
	
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer = new Customer();
				customer.setCustomerId(resultSet.getInt("cu_id"));
				customer.setCustomerName(resultSet.getString("cu_name"));
				customer.setCustomerUsername(resultSet.getString("cu_username"));
				customer.setCustomerEmail(resultSet.getString("cu_email"));
			} else {
				throw new BusinessException("Customer Id is not matched in our Database!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal Problem Occured. Contact sysAdmin!");
		}
		return customer;
	}

	@Override
	public List<Customer> getCustomerByName(String customerName) throws BusinessException {
		List<Customer> customerList = new ArrayList<>();
		
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select cu_id,cu_name,cu_username,cu_email from customer where cu_name=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customerName);
	
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Customer customer = new Customer();
				
				customer.setCustomerId(resultSet.getInt("cu_id"));
				customer.setCustomerName(resultSet.getString("cu_name"));
				customer.setCustomerUsername(resultSet.getString("cu_username"));
				customer.setCustomerEmail(resultSet.getString("cu_email"));
				
				customerList.add(customer);
			} else {
				throw new BusinessException("Customer Name is not matched in our Database!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal Problem Occured. Contact sysAdmin!");
		}
		return customerList;
	}

	@Override
	public Customer getCustomerByEmail(String customerEmail) throws BusinessException {
		Customer customer = null;
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select cu_id,cu_name,cu_username,cu_email from customer where cu_email=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customerEmail);
	
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer = new Customer();
				customer.setCustomerId(resultSet.getInt("cu_id"));
				customer.setCustomerName(resultSet.getString("cu_name"));
				customer.setCustomerUsername(resultSet.getString("cu_username"));
				customer.setCustomerEmail(resultSet.getString("cu_email"));
			} else {
				throw new BusinessException("Customer Email-Id is not matched in our Database!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal Problem Occured. Contact sysAdmin!");
		}
		return customer;
	}

	@Override
	public Customer getCustomerByOrderId(int orderId) throws BusinessException {
		Customer customer = null;
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select cu_id,cu_name,cu_username,cu_email from orders join customer on or_cu_id = cu_id where or_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, orderId);
	
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer = new Customer();
				customer.setCustomerId(resultSet.getInt("cu_id"));
				customer.setCustomerName(resultSet.getString("cu_name"));
				customer.setCustomerUsername(resultSet.getString("cu_username"));
				customer.setCustomerEmail(resultSet.getString("cu_email"));
			} else {
				throw new BusinessException("Order-Id is not matched in our Database!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal Problem Occured. Contact sysAdmin!");
		}
		return customer;
	}

}
