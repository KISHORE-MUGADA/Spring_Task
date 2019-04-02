package com.vedantu.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.vedantu.utils.ReqLimitsErMsgs;
import com.vedantu.utils.ReqLimits;


public class RepositReq extends AbstractFrontEndReq{
	
	   private String id;
	   private String picUrl;
	   @NotNull(message = ReqLimitsErMsgs.RQD)
	   @Size(max = ReqLimits.NAME_TYPE_MAX, message = ReqLimitsErMsgs.MAX_NAME_TYPE )
	   private String name;
	   @Size(max = ReqLimits.NAME_TYPE_MAX, message = ReqLimitsErMsgs.MAX_NAME_TYPE )
	   private Integer starts;
	   @Size(max = ReqLimits.NAME_TYPE_MAX, message = ReqLimitsErMsgs.MAX_NAME_TYPE )
	   private Integer fork;
	   @Size(max = ReqLimits.NAME_TYPE_MAX, message = ReqLimitsErMsgs.MAX_NAME_TYPE )
	   private Integer openIssues;
	   private String proUrl;
	   @Size(max = ReqLimits.DES_TYPE_MAX, message = ReqLimitsErMsgs.MAX_DES_TYPE )
	   private String des;
	   
	   
	public RepositReq() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
