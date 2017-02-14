package com.rest.fb;

import com.rest.fb.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringRestConsumerApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringRestConsumerApplication.class, args);
		ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
		config.dataAggregator().getData();
		config.postDao().insertData();
		
	}


}
