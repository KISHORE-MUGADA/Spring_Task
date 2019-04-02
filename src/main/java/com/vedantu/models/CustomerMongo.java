package com.vedantu.models;

import com.vedantu.enums.CustomerType;

public class CustomerMongo extends AbstractMongoStringIdEntity {
	
    private String name;
	private String address;
	private int amount;
	private String contactnumber;
	private CustomerType customerType;
	
	
	public CustomerType getCustomerType() {
		return customerType;
	}
	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	public CustomerMongo() {
		
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getContactnumber() {
		return contactnumber;
	}


	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	

}
