package com.app.model;

public class Order {
	
	private int productId;
	private String productName;
	private double price;
	private String orderStatus;
	public Order() {
		super();
	}
	public Order(int productId, String productName, double price, String orderStatus) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.orderStatus = orderStatus;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
}
