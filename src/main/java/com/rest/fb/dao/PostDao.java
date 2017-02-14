package com.rest.fb.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

public class PostDao {

	private final static Logger logger = LoggerFactory.getLogger(PostDao.class);

	MongoTemplate mongoTemplate;

	public PostDao(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public <T> void insertData(List<T> list) {
		logger.info("Persisting list");
		mongoTemplate.insertAll(list);
		logger.info("persist process finished");

	}

}
