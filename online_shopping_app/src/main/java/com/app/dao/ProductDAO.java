package com.app.dao;

import java.util.List;

import com.app.businessException.BusinessException;
import com.app.model.Product;

public interface ProductDAO {
	public List<Product> getAllProducts() throws BusinessException;
	
	public int addProduct(Product product) throws BusinessException;

	public int deleteProduct(Product product) throws BusinessException;
	
    public List<Product> getProductByName(String productName) throws BusinessException;
	
	public List<Product> getProductByCategory(String productCategory) throws BusinessException;
}
