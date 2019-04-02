package com.vedantu.requests;

import java.util.List;

import com.vedantu.models.CartItem;

public class CartReq extends AbstractFrontEndReq {
	
	private String id;
	private String customerid;
	private CartItem  cartitems;
	
	
	public CartItem getCartitems() {
		return cartitems;
	}
	public void setCartitems(CartItem cartitems) {
		this.cartitems = cartitems;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}


}
