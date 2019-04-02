package com.vedantu.requests;

import com.vedantu.enums.ProductType;

public class ProductsReq extends AbstractFrontEndReq{
	private String id;
	private String wherehouseid;
	private String name;
	private int price;
	private int quantity;
	private ProductType productType; 
   
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWherehouseid() {
		return wherehouseid;
	}
	public void setWherehouseid(String wherehouseid) {
		this.wherehouseid = wherehouseid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
  }
}