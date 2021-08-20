package online_shopping_app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.Main;
import com.app.businessException.BusinessException;
import com.app.dao.CartDAO;
import com.app.dao.CustomerDAO;
import com.app.dao.EmployeeDAO;
import com.app.dao.ProductDAO;
import com.app.daoImpl.CartDAOImpl;
import com.app.daoImpl.CustomerDAOImpl;
import com.app.daoImpl.EmployeeDAOImpl;
import com.app.daoImpl.ProductDAOImpl;
import com.app.model.Customer;
import com.app.model.Product;

class test {

	// Test choice is in range
	@Test
	void checkChoice() {
		assertEquals(true, Main.checkChoice(2) ,"Not Valid Choice");
		assertEquals(false, Main.checkChoice(7) ,"Not Valid Choice");
	}
	
	// Test add product
	@Test
	void testAddProduct() throws BusinessException {
		ProductDAO dao = new ProductDAOImpl();
		
		Product product = new Product("AC", 50000.5, 4.2, 1);
		assertEquals(1,dao.addProduct(product), "Not Valid Product Details");
	}
	
	// Test add Customer 
	@Test
	void testAddCustomer() throws BusinessException {
		CustomerDAO dao = new CustomerDAOImpl();
		
		Customer customer = new Customer(1,"Mohit", "jindalmohit","20182021" ,"jindalmohit2018@gmail.com");
		assertEquals(1,dao.createAccount(customer),"Not Valid Customer Details");
		
	}	
	
	// Test product add in cart
	@Test
	void testAddProductInCart() throws BusinessException {
		CartDAO dao = new CartDAOImpl();
		assertEquals(1,dao.addProductInCart(1, 2));
		
	}
	
	// Test product delete in cart
	@Test
	void testDeleteProductInCart() throws BusinessException {
		CartDAO dao = new CartDAOImpl();
		assertEquals(0,dao.deleteProductInCart(1));
		
	}
	
	// Test 
	@Test
	void testEmployeeLogin() throws BusinessException {
		
		EmployeeDAO dao = new EmployeeDAOImpl();
		assertEquals(true,dao.checkValidCredentials("jindalmohit2018", "123456"),"Not valid Entry");
		
	}
	

}
