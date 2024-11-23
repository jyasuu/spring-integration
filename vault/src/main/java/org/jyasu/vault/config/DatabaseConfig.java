package org.jyasu.vault.config;

import javax.sql.DataSource;

import org.jyasu.vault.VaultApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    
	
	@Bean
	@RefreshScope
	DataSource dataSource(DataSourceProperties properties) {
		Logger logger = LoggerFactory.getLogger(VaultApplication.class);
		var db = DataSourceBuilder
			.create()
			.url(properties.getUrl())
			.username(properties.getUsername())
			.password(properties.getPassword())
			.build();
			logger.info(
				"rebuild data source: " +
				properties.getUsername() +
				',' + properties.getPassword()
	  );
	  return db;
	}
	  
}
