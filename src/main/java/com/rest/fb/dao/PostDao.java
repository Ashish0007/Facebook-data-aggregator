package com.rest.fb.dao;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.rest.fb.service.DataAggregator;

public class PostDao {
	
	MongoTemplate mongoOperations;
    
    DataAggregator dataAggregator;

    public PostDao(DataAggregator dataAggregator, MongoTemplate mongoTemplate) {
    	this.dataAggregator = dataAggregator;
    	this.mongoOperations=mongoTemplate;
	}

	public void insertData(){
    	
    	mongoOperations.insertAll(dataAggregator.getData());
    	
    }

}
