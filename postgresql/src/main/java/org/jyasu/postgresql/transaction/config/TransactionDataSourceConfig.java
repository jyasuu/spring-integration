package org.jyasu.postgresql.transaction.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@DependsOn({"db2DataSource"})
@EnableJpaRepositories(
	basePackages = "org.jyasu.postgresql.transaction.repository", 
	entityManagerFactoryRef = "transactionEntityManagerFactory",
	transactionManagerRef = "transactionTransactionManager")
public class TransactionDataSourceConfig {
    
  @Bean
  public LocalContainerEntityManagerFactoryBean transactionEntityManagerFactory(
      EntityManagerFactoryBuilder builder,
      @Qualifier("db2DataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("org.jyasu.postgresql.transaction.entity");
        
        // HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        // em.setJpaVendorAdapter(vendorAdapter);
        
        // HashMap<String, Object> properties = new HashMap<>();
        // properties.put("hibernate.hbm2ddl.auto", "create-drop");
        // properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        // properties.put("hibernate.transaction.jta.platform", 
        //               "org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform");
        // em.setJpaPropertyMap(properties);
        
        return builder
            .dataSource(dataSource)
            .packages("org.jyasu.postgresql.transaction.entity")
            .persistenceUnit("db2")
            .build();
  }

  
    @Bean
    public PlatformTransactionManager transactionTransactionManager(
            @Qualifier("transactionEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
