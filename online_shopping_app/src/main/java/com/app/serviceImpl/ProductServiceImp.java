package com.app.serviceImpl;

import com.app.businessException.BusinessException;
import com.app.dao.ProductDAO;
import com.app.daoImpl.ProductDAOImpl;
import com.app.model.Product;
import com.app.service.ProductService;

public class ProductServiceImp implements ProductService {

	@Override
	public int addProduct(Product product) throws BusinessException {
		int c = 0;
		ProductDAO productDAO = new ProductDAOImpl();
		c = productDAO.addProduct(product);
		return c;
	}

	@Override
	public int deleteProduct(Product product) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
