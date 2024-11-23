package org.jyasu.vault;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.jyasu.vault.config.MyConfiguration;

@RestController
@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties(MyConfiguration.class)
public class VaultApplication {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;

	@RequestMapping("/")
	String home() {

		Logger logger = LoggerFactory.getLogger(VaultApplication.class);

		logger.info("----------------------------------------");
		logger.info("Configuration properties");
		logger.info("   example.username is {}", configuration.getUsername());
		logger.info("   example.password is {}", configuration.getPassword());
		logger.info("----------------------------------------");

		
        // Query to get the current user from PostgreSQL
        String sql = "SELECT current_user";

        // Execute the query and retrieve the current user
        String currentUser = jdbcTemplate.queryForObject(sql, String.class);

		return String.format("Hello World %s!", currentUser);
	}
	
	@Autowired
  	private MyConfiguration configuration;

	public static void main(String[] args) {
		SpringApplication.run(VaultApplication.class, args);
	}

}
