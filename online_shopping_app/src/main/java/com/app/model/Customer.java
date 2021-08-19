package com.app.model;

public class Customer {

	private int customerId;
	private String customerName;
	private String customerUsername;
	private String customerPassword;
	private String customerEmail;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}
	
	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerUsername="
				+ customerUsername + ", customerEmail=" + customerEmail
				+ "]";
	}

	
	public Customer(String customerName, String customerUsername, String customerEmail) {
		super();
		this.customerName = customerName;
		this.customerUsername = customerUsername;
		this.customerEmail = customerEmail;
	}
	
	public Customer(String customerName, String customerUsername, String customerPassword,
			String customerEmail) {
		this(customerName, customerUsername, customerEmail);
		this.customerEmail = customerEmail;
	}

	public Customer() {
		
	}

}
