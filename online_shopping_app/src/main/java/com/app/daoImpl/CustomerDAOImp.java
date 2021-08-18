package com.app.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.app.Main;
import com.app.businessException.BusinessException;
import com.app.dao.CustomerDAO;
import com.app.dao.dbutils.MyDbConnection;
import com.app.model.Customer;

public class CustomerDAOImp implements CustomerDAO {
	Logger log = Logger.getLogger(Main.class);

	@Override
	public Boolean checkValidCredentials(String username, String password) throws BusinessException {
		boolean login = false;
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select cu_username,cu_password from customer where cu_username=? and cu_password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				login = true;
			} else {
				throw new BusinessException("Credentials is not matched with our Database!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal Problem Occured. Contact sysAdmin!");
		}
		return login;
	}

	@Override
	public int createAccount(Customer customer) throws BusinessException {
		int c = 0;
		try (Connection connection = MyDbConnection.getConnection()) {

			String sql = "insert into customer(cu_name,cu_username,cu_password,cu_email) values(?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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

}
