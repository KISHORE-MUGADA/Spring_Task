package com.vedantu.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vedantu.enums.Status;
import com.vedantu.models.OrderItem;

public class OrdersMongo extends AbstractMongoStringIdEntity{
	 
	private String customerid;
	private int totalprice;
	private Status status;
	private List<OrderItem> orderitems;
	
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
		int _totalPrice=0;
		if(orderitems != null && !orderitems.isEmpty()) {
		for(OrderItem orderItem:orderitems) {
		_totalPrice += orderItem.getPrice()*orderItem.getQuantity();
		}
		}
		this.totalprice = _totalPrice;
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
	public Set<String> getProductIds(){
		if (orderitems == null || orderitems.isEmpty()) {
			return new HashSet<>();
		}
		Set<String> prdIds=new HashSet<>();
		for (OrderItem ordersItem:orderitems) {
			prdIds.add(ordersItem.getPid());
		}
		return prdIds;
	}
	

}
