package com.xyy.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = "com.xyy.cache.*")
@EnableScheduling
@EnableCaching
@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class})
public class CachedemoApplication {


	public static void main(String[] args) {
	SpringApplication.run(CachedemoApplication.class, args);
	}
}
