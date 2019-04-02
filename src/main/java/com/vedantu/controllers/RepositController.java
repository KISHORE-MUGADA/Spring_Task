package com.vedantu.controllers;

import com.vedantu.managers.RepositManager;
import com.vedantu.models.RepositMongo;
import com.vedantu.requests.RepositReq;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test2")
public class RepositController {

	@Autowired
	private RepositManager repositManager;
	
	// createRepository
	@RequestMapping(value = "/createRepo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String create(@RequestBody RepositReq param) throws Exception {
		return repositManager.create(param);
	}
	
	//updateRepository
	@RequestMapping(value = "/updateRepo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody RepositReq param) throws Exception {
		return repositManager.update(param);
	}
	
	//deleteRepository
	@RequestMapping(value = "/deleteRepo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@RequestBody RepositReq param) throws Exception {
        return  repositManager.delete(param);
	}
	
	//getAllRepositories
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<RepositMongo> getParam() throws Exception {
	       return  repositManager.getAll();
	}
}
