//https://www.infoq.com/articles/Multiple-Databases-with-Spring-Boot

package com;

import org.springframework.context.annotation.Primary;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories( entityManagerFactoryRef = "dsA_EntityManagerFactory",
						transactionManagerRef   = "dsA_TransactionManager",
						basePackages            = { "com.dsAjpa.repository" })
public class DSaConfig_forJPA {
  
  @Primary
  @Bean(name = "dsA")
  @ConfigurationProperties(prefix = "spring.dsA")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }
  
  @Primary
  @Bean(name = "dsA_EntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean dsA_EntityManagerFactory(
    EntityManagerFactoryBuilder builder, @Qualifier("dsA") DataSource ds) {
	  
    return builder.dataSource(ds).packages("com.dsAjpa.entity").persistenceUnit("aaa")
      .build();
  }
    
  @Primary
  @Bean(name = "dsA_TransactionManager")
  public PlatformTransactionManager dsA_TransactionManager(
    @Qualifier("dsA_EntityManagerFactory") EntityManagerFactory dsA_EMF) {
    return new JpaTransactionManager(dsA_EMF);
  }
}