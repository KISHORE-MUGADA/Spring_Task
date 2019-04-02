package com.vedantu.controllers;

import com.vedantu.daos.AbstractMongoClientFactory;
import com.vedantu.daos.EmployeeDAO;
import com.vedantu.daos.EmployeeMongoDAO;
import com.vedantu.models.Employee;
import com.vedantu.models.EmployeeMongo;
import com.vedantu.requests.EmployeeReq;
import com.vedantu.utils.LogFactory;

import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.tags.Param;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private EmployeeMongoDAO employeeMongoDAO;
    
    @Autowired
    private LogFactory logFactory;

    private final Logger logger = logFactory.getLogger(TestController.class);

    

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addParam(@RequestBody EmployeeReq param) throws Exception {
        Employee e = new Employee();
        e.setName(param.getName());
        e.setUserId(param.getValue());
        e.setSalary(100);
        employeeDAO.create(e);

       /* EmployeeMongo e2 = new EmployeeMongo();
        e2.setName("ajith");
        e2.setUserId("1");
        e2.setSalary(100);
        employeeMongoDAO.create(e2);*/

        return "Success";
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public String getParamById() throws Exception {
        return "RESPONSE";
    }
    
    @RequestMapping(value="/update", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateParam(@RequestBody EmployeeReq param) throws Exception {
    	Employee e =employeeDAO.getEntityById(param.getId(), null, Employee.class);
        e.setName(param.getName());
        e.setUserId(param.getValue());
        employeeDAO.update(e,null);
       return "hai";
    }
}
