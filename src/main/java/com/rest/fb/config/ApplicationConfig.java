package com.rest.fb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.rest.fb.dao.PostDao;
import com.rest.fb.service.DataAggregator;
import com.rest.fb.util.HttpUtil;

@Configuration
public class ApplicationConfig {


    @Value("${fb.group.id}")
    private String groupId;

    @Value("${fb.access_token}")
    private String accessToken;

    @Value("${fb.rest.endpoint}")
    private String restUrl;
    
    @Value("${spring.mongo.host}")
    private String mongoHost;
    
    @Value("${spring.mongo.database}")
    private String mongoDatabase;

    @Bean
    public HttpUtil httpUtil() {
        return new HttpUtil();
    }
    
    @Bean
    public DataAggregator dataAggregator() {
        return new DataAggregator(groupId, accessToken, restUrl);
    }
    
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(mongoHost);
    }
 
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), mongoDatabase);
    }
    
    @Bean
    public PostDao postDao() throws Exception{
    	return new PostDao(mongoTemplate());
    }
 }
