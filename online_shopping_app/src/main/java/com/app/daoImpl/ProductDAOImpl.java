package com.app.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.app.Main;
import com.app.businessException.BusinessException;
import com.app.dao.ProductDAO;
import com.app.dao.dbutils.MyDbConnection;
import com.app.model.Product;

public class ProductDAOImpl implements ProductDAO {
	Logger log = Logger.getLogger(Main.class);

	@Override
	public int addProduct(Product product) throws BusinessException {
		int c = 0;
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "insert into product(productName,productPrice,productRating,productCategoryId) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setDouble(2, product.getProductPrice());
			preparedStatement.setDouble(3, product.getProductRating());
			preparedStatement.setInt(4, product.getProductCategoryId());

			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal Problem Occured. Contact sysAdmin!");
		}
		return c;
	}

	@Override
	public int deleteProduct(Product product) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
