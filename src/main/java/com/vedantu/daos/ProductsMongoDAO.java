/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vedantu.daos;

import com.vedantu.models.CustomerMongo;
import com.vedantu.models.Employee;
import com.vedantu.models.EmployeeMongo;
import com.vedantu.models.ProductsMongo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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
public class ProductsMongoDAO extends AbstractMongoDAO {

    @Autowired
    private LogFactory logFactory;

    @SuppressWarnings("static-access")
    private Logger logger = logFactory.getLogger(ProductsMongoDAO.class);

    @Autowired
    private MongoClientFactory mongoClientFactory;

    @Override
    protected MongoOperations getMongoOperations() {
        return mongoClientFactory.getMongoOperations();
    }

    public ProductsMongoDAO() {
        super();
    }

    public void create(ProductsMongo productsMongo) {
        try {
            saveEntity(productsMongo);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "ContentInfoUpdateError : Error updating the content info " + productsMongo, ex);
        }
    }
    
    public ProductsMongo getById(String id) {
        ProductsMongo challenge = null;
        try {
            challenge = (ProductsMongo) getEntityById(id,ProductsMongo.class);
        } catch (Exception ex) {
            throw new RuntimeException("ContentInfoFetchError : Error fetch the content info with id " + id, ex);
        }
        return challenge;
    }
    
    
    public void update(ProductsMongo p, String callingUserId) {
        if (p != null) {
            logger.info("update: " + p.toString());
            saveEntity(p,callingUserId);
        }
    }
    
    public void delete(ProductsMongo p, String callingUserId) {
        if (p != null) {
            logger.info("delete: " + p.toString());
            
            deleteEntityById(p.getId(),ProductsMongo.class);
        }
    }
    
    public List<ProductsMongo> getProductsFromIds(Set<String> ids){
    	Query query;
        query = new Query();
        query.addCriteria(Criteria.where("id").in(ids));
        return runQuery(query, ProductsMongo.class);
    }
    
  /*  public List<ProductMongo> getProductsFromIds(Set<String> ids) {
        Query query;
        query = new Query();
        query.addCriteria(Criteria.where("id").in(ids));
        return runQuery(query, ProductMongo.class); 
    } */
    
  //custom
    public Collection<ProductsMongo> getAll() {
    	return getAllProducts(ProductsMongo.class);
    }
     
    
}
