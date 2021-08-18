package com.app.model;

public class Product {

	private int productId;
	private String productName;
	private double productPrice;
	private double productRating;
	private int productCategoryId;

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

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getProductRating() {
		return productRating;
	}

	public void setProductRating(double productRating) {
		this.productRating = productRating;
	}

	public int getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productRating=" + productRating + ", productCategoryId=" + productCategoryId + "]";
	}

	public Product(String productName, double productPrice, double productRating, int productCategoryId) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.productRating = productRating;
		this.productCategoryId = productCategoryId;
	}

	public Product(int productId, String productName, double productPrice, double productRating,
			int productCategoryId) {
		this(productName, productPrice, productRating, productCategoryId);

		this.productId = productId;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

}
