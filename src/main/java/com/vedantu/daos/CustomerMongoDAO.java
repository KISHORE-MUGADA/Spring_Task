/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedantu.daos;

import com.vedantu.models.CustomerMongo;
import com.vedantu.models.Employee;
import com.vedantu.models.EmployeeMongo;

import java.util.Collection;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.vedantu.utils.LogFactory;

/**
 *
 * @author ajith
 */
@Service
public class CustomerMongoDAO extends AbstractMongoDAO {

    @Autowired
    private LogFactory logFactory;

    @SuppressWarnings("static-access")
    private Logger logger = logFactory.getLogger(CustomerMongoDAO.class);

    @Autowired
    private MongoClientFactory mongoClientFactory;

    @Override
    protected MongoOperations getMongoOperations() {
        return mongoClientFactory.getMongoOperations();
    }

    public CustomerMongoDAO() {
        super();
    }

    public void create(CustomerMongo customerMongo) {
        try {
            saveEntity(customerMongo);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "ContentInfoUpdateError : Error updating the content info " + customerMongo, ex);
        }
    }
    
    public CustomerMongo getById(String id) {
        CustomerMongo challenge = null;
        try {
            challenge = (CustomerMongo) getEntityById(id, CustomerMongo.class);
        } catch (Exception ex) {
            throw new RuntimeException("ContentInfoFetchError : Error fetch the content info with id " + id, ex);
        }
        return challenge;
    }
    
    public void update(CustomerMongo p, String callingUserId) {
        if (p != null) {
            logger.info("update: " + p.toString());
            saveEntity(p,callingUserId);
        }
    }
    
    public void delete(CustomerMongo p, String callingUserId) {
        if (p != null) {
            logger.info("delete: " + p.toString());
            
            deleteEntityById(p.getId(),CustomerMongo.class);
        }
    }
    
    //custom
    public Collection<CustomerMongo> getAll() {
    	return getAllCustomers(CustomerMongo.class);
    }
    
}
