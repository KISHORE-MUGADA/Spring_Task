package com.vedantu.managers;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.vedantu.daos.RepositMongoDAO;
import com.vedantu.models.RepositMongo;
import com.vedantu.requests.RepositReq;
import com.vedantu.utils.LogFactory;

@Service
public class RepositManager {
	
	@Autowired
	private RepositMongoDAO repositMongoDAO;
	
	@Autowired
	private LogFactory logFactory;
   
	@SuppressWarnings("static-access")
	private final Logger logger = logFactory.getLogger(RepositManager.class);
	
  public String create(@RequestBody RepositReq param) throws Exception {
		RepositMongo e2 = new RepositMongo();
		
	    if (StringUtils.isNotEmpty(param.getPicUrl())) {
	    	e2.setPicUrl(param.getPicUrl());
	    }else {
	    	return "url required";
	    }
	    
	    if(StringUtils.isNotEmpty(param.getName())) {
	    	e2.setName(param.getName());
	    }else {
	    	return "name required";
	    }
	    
	    if(param.getStarts() != null && param.getStarts() > 0 ) {
		e2.setStarts(param.getStarts());
	    }else {
	    	return "Starts Count Required";
	    }
	    
	    if (param.getFork() != null && param.getFork() > 0) {
	    		e2.setFork(param.getFork());
	    }
	    else {
	    	return "fork is empty";
	    }
	    if (param.getOpenIssues() != null && param.getOpenIssues() > 0) {
	    		e2.setOpenIssues(param.getOpenIssues());
	    }else {
	    	return "OpenIssues required";
	    }
	    
	    if (StringUtils.isNotEmpty(param.getProUrl())) {
	    		e2.setProUrl(param.getProUrl());
	    }else {
	    	return "Profile url required";
	    }
	    
	    if (param.getDes() != null && StringUtils.isNotEmpty(param.getDes())) {
	    		e2.setDes(param.getDes());
	    }else {
	    	return "Description is empty";
	    }
		
		repositMongoDAO.create(e2);
		
		return "Added Repository successfully";
		
	}
	
  public String update(@RequestBody RepositReq param) throws Exception {

		RepositMongo e2 = repositMongoDAO.getById(param.getId());
		if(e2 != null){
			
		e2.setPicUrl(param.getPicUrl());
		e2.setName(param.getName());
		e2.setStarts(param.getStarts());
		e2.setFork(param.getFork());
		e2.setOpenIssues(param.getOpenIssues());
		e2.setProUrl(param.getProUrl());
		e2.setDes(param.getDes());

		repositMongoDAO.update(e2, null);
		return "updated Repository successfully";
		
		}else {
			return "Repository not exist in the DB";
		  }
}
	
  public String delete(@RequestBody RepositReq param) throws Exception {

		RepositMongo e2 = repositMongoDAO.getById(param.getId());
		if (e2 != null) {
		repositMongoDAO.delete(e2, null);
		}
		return "Deleted repository successfully";
   }
   
  public Collection<RepositMongo> getAll() throws Exception {
	   Collection<RepositMongo>  reposit_info = repositMongoDAO.getAll();
       return  reposit_info;
}
}