package com.app.model;

public class Cart {
	private int customerId;
	private int productId;
	private String productName;
	private double price;
	
	public Cart() {
		super();
	}

	public Cart(int customerId, int productId, String productName, double price) {
		super();
		this.customerId = customerId;
		this.productId = productId;
		this.productName = productName;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Cart [productName=" + productName + ", price=" + price  + "]";
	}

	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	

}
