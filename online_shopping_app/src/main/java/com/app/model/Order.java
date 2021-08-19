package com.app.model;

public class Order {
	private int orderId;
	private int productId;
	private String productName;
	private double price;
	private String orderStatus;
	public Order() {
		super();
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	@Override
	public String toString() {
		return "The Order id "+orderId + " is a " + productName + " of price " + price + " The Current Order Status is " + orderStatus;
	}
	
	
	
}
