package com.app.dao;

import com.app.businessException.BusinessException;
import com.app.model.Product;

public interface ProductDAO {
	public int addProduct(Product product) throws BusinessException;

	public int deleteProduct(Product product) throws BusinessException;
}
