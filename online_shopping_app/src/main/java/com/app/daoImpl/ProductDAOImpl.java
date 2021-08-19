package com.app.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.businessException.BusinessException;
import com.app.dao.ProductDAO;
import com.app.dao.dbutils.MyDbConnection;
import com.app.model.Product;

public class ProductDAOImpl implements ProductDAO {
	
	@Override
	public List<Product> getAllProducts() throws BusinessException {
		List<Product> productList = new ArrayList<>();
		
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select pro_id,pro_name,pro_price,pro_rating,pro_cat_id from product order by pro_price";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();			
			while(resultSet.next()) {
				Product product = new Product();
				product.setProductId(resultSet.getInt("pro_id"));
				product.setProductName(resultSet.getString("pro_name"));
				product.setProductCategoryId(resultSet.getInt("pro_cat_id"));
				product.setProductPrice(resultSet.getDouble("pro_price"));
				product.setProductRating(resultSet.getDouble("pro_rating"));
				productList.add(product);
			}
			if(productList.size()<1) {
				throw new BusinessException("Sorry Product is not available now!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage()+ " Internal Problem Occured. Contact sysAdmin!");
		}
		return productList;
	}
	
	@Override
	public int addProduct(Product product) throws BusinessException {
		int c;
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "insert into product(pro_name,pro_price,pro_rating,pro_cat_id) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setDouble(2, product.getProductPrice());
			preparedStatement.setDouble(3, product.getProductRating());
			preparedStatement.setInt(4, product.getProductCategoryId());

			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException(e.getMessage()+" Internal Problem Occured. Contact sysAdmin!");
		}
		return c;
	}

	@Override
	public int deleteProduct(Product product) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Product> getProductByName(String productName) throws BusinessException {
		List<Product> productList = new ArrayList<>();
		
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select pro_id,pro_name,pro_price,pro_rating,pro_cat_id from product where pro_name=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productName);
			ResultSet resultSet = preparedStatement.executeQuery();			
			while(resultSet.next()) {
				Product product = new Product();
				product.setProductId(resultSet.getInt("pro_id"));
				product.setProductName(resultSet.getString("pro_name"));
				product.setProductCategoryId(resultSet.getInt("pro_cat_id"));
				product.setProductPrice(resultSet.getDouble("pro_price"));
				product.setProductRating(resultSet.getDouble("pro_rating"));
				productList.add(product);
			}
			if(productList.size()<1) {
				throw new BusinessException("Sorry Product is not available now!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage()+ " Internal Problem Occured. Contact sysAdmin!");
		}
		return productList;
	}

	@Override
	public List<Product> getProductByCategory(String productCategory) throws BusinessException {
		List<Product> productList = new ArrayList<>();
		
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select pro_id,pro_name,pro_price,pro_rating,p.pro_cat_id from product p join productCategory pc on p.pro_cat_id=pc.pro_cat_id "
					+ "where pro_cat_name =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productCategory);
			ResultSet resultSet = preparedStatement.executeQuery();			
			while(resultSet.next()) {
				Product product = new Product();
				product.setProductId(resultSet.getInt("pro_id"));
				product.setProductName(resultSet.getString("pro_name"));
				product.setProductCategoryId(resultSet.getInt("pro_cat_id"));
				product.setProductPrice(resultSet.getDouble("pro_price"));
				product.setProductRating(resultSet.getDouble("pro_rating"));
				productList.add(product);
			}
			if(productList.size()<1) {
				throw new BusinessException("Sorry Product is not available now!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage()+ " Internal Problem Occured. Contact sysAdmin!");
		}
		return productList;
	}

	

}
