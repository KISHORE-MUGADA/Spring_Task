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
import com.vedantu.models.ProductsMongo;

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
public class CartMongoDAO extends AbstractMongoDAO {

    @Autowired
    private LogFactory logFactory;

    @SuppressWarnings("static-access")
    private Logger logger = logFactory.getLogger(CartMongoDAO.class);

    @Autowired
    private MongoClientFactory mongoClientFactory;

    @Override
    protected MongoOperations getMongoOperations() {
        return mongoClientFactory.getMongoOperations();
    }

    public CartMongoDAO() {
        super();
    }

    public void create(CartMongo cartMongo) {
        try {
            saveEntity(cartMongo);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "ContentInfoUpdateError : Error updating the content info " + cartMongo, ex);
        }
    }
    
    public CartMongo getById(String id) {
        CartMongo challenge = null;
        try {
            challenge = (CartMongo) getEntityById(id,CartMongo.class);
        } catch (Exception ex) {
            throw new RuntimeException("ContentInfoFetchError : Error fetch the content info with id " + id, ex);
        }
        return challenge;
    }
    

    public CartMongo getByCustomerId(String id) {
        CartMongo challenge = null;
        try {
            challenge = (CartMongo) getEntityByCId(id,CartMongo.class);
        } catch (Exception ex) {
            throw new RuntimeException("ContentInfoFetchError : Error fetch the content info with id " + id, ex);
        }
        return challenge;
    }
    
    
    public void update(CartMongo p, String callingUserId) {
        if (p != null) {
            logger.info("update: " + p.toString());
            saveEntity(p,callingUserId);
        }
    }
    
    public void delete(CartMongo p, String callingUserId) {
        if (p != null) {
            logger.info("delete: " + p.toString());
            
            deleteEntityById(p.getId(),CartMongo.class);
        }
    }
   //cart 
    public Collection<CartMongo> getAll() {
    	return getAllCarts(CartMongo.class);
    }
    
     
}