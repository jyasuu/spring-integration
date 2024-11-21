package org.jyasu.vault;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.jyasu.vault.config.MyConfiguration;

@RestController
@SpringBootApplication
@EnableConfigurationProperties(MyConfiguration.class)
public class VaultApplication {

	@RequestMapping("/")
	String home() {

		Logger logger = LoggerFactory.getLogger(VaultApplication.class);

		logger.info("----------------------------------------");
		logger.info("Configuration properties");
		logger.info("   example.username is {}", configuration.getUsername());
		logger.info("   example.password is {}", configuration.getPassword());
		logger.info("----------------------------------------");

		return "Hello World!";
	}
	
	@Autowired
  	private MyConfiguration configuration;

	public static void main(String[] args) {
		SpringApplication.run(VaultApplication.class, args);
	}
	

}
