package com.rest.fb.config;

import com.rest.fb.service.DataAggregator;
import com.rest.fb.util.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {


    @Value("${fb.group.id}")
    private String groupId;

    @Value("${fb.access_token}")
    private String accessToken;

    @Value("${fb.rest.endpoint}")
    private String restUrl;

    @Bean
    public HttpUtil httpUtil() {
        return new HttpUtil();
    }

    @Bean
    public DataAggregator dataAggregator() {
        return new DataAggregator(groupId, accessToken, restUrl);
    }

}
