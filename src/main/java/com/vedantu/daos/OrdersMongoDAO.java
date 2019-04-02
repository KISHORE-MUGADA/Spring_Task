/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedantu.daos;

import com.vedantu.models.CartMongo;
import com.vedantu.models.CustomerMongo;
import com.vedantu.models.Employee;
import com.vedantu.models.EmployeeMongo;
import com.vedantu.models.OrdersMongo;
import com.vedantu.models.ProductsMongo;

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
public class OrdersMongoDAO extends AbstractMongoDAO {

    @Autowired
    private LogFactory logFactory;

    @SuppressWarnings("static-access")
    private Logger logger = logFactory.getLogger(OrdersMongoDAO.class);

    @Autowired
    private MongoClientFactory mongoClientFactory;

    @Override
    protected MongoOperations getMongoOperations() {
        return mongoClientFactory.getMongoOperations();
    }

    public OrdersMongoDAO() {
        super();
    }

    public void create(OrdersMongo ordersMongo) {
        try {
            saveEntity(ordersMongo);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "ContentInfoUpdateError : Error updating the content info " + ordersMongo, ex);
        }
    }
    
    public OrdersMongo getById(String id) {
        OrdersMongo challenge = null;
        try {
            challenge = (OrdersMongo) getEntityById(id,OrdersMongo.class);
        } catch (Exception ex) {
            throw new RuntimeException("ContentInfoFetchError : Error fetch the content info with id " + id, ex);
        }
        return challenge;
    }
    
    
    public void update(OrdersMongo p, String callingUserId) {
        if (p != null) {
            logger.info("update: " + p.toString());
            saveEntity(p,callingUserId);
        }
    }
    
    public void delete(OrdersMongo p, String callingUserId) {
        if (p != null) {
            logger.info("delete: " + p.toString());
            
            deleteEntityById(p.getId(),OrdersMongo.class);
        }
    }
     
}