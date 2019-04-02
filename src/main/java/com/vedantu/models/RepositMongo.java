package com.vedantu.models;

public class RepositMongo extends AbstractMongoStringIdEntity{

	   private String picUrl;
	   private String name;
	   private Integer starts;
	   private Integer fork;
	   private Integer openIssues;
	   private String proUrl;
	   private String des;
	  
	public RepositMongo() {
		super();
	}
	
	
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStarts() {
		return starts;
	}
	public void setStarts(Integer starts) {
		this.starts = starts;
	}
	public Integer getFork() {
		return fork;
	}
	public void setFork(Integer fork) {
		this.fork = fork;
	}
	public Integer getOpenIssues() {
		return openIssues;
	}
	public void setOpenIssues(Integer openIssues) {
		this.openIssues = openIssues;
	}
	public String getProUrl() {
		return proUrl;
	}
	public void setProUrl(String proUrl) {
		this.proUrl = proUrl;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
}
