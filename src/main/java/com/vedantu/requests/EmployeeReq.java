package com.vedantu.requests;

public class EmployeeReq extends AbstractFrontEndReq{
	private String name;
	private String value;
	private Long id;
public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		
}
