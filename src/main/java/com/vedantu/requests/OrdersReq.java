package com.vedantu.requests;

import java.util.List;

import com.vedantu.enums.Status;
import com.vedantu.models.OrderItem;

public class OrdersReq extends AbstractFrontEndReq{
	 private String id;
	 private String customerid;
	 private List<OrderItem> orderitems;
	 private int totalprice;
	 private Status status;
	 
	 
	 
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
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
	public List<OrderItem> getOrderitems() {
		return orderitems;
	}
	public void setOrderitems(List<OrderItem> orderitems) {
		this.orderitems = orderitems;
	}
	 
}
