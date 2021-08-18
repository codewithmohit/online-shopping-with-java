package com.app.model;

public class Cart {
	private String productName;
	private double price;
	private int quantity;
	
	
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Cart(String productName, double price, int quantity) {
		super();
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Cart [productName=" + productName + ", price=" + price + ", quantity=" + quantity + "]";
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
