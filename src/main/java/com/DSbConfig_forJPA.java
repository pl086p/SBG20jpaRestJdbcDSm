//https://www.infoq.com/articles/Multiple-Databases-with-Spring-Boot

package com;

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
@EnableJpaRepositories(
		  entityManagerFactoryRef = "dsB_EentityManagerFactory",
		  transactionManagerRef   = "dsB_TransactionManager",
		  basePackages            = { "com.dsBjpa.repository" }
		)
public class DSbConfig_forJPA {
  
  @Bean(name = "dsB")
  @ConfigurationProperties(prefix = "spring.dsB")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }
  

  @Bean(name = "dsB_EentityManagerFactory")
  public LocalContainerEntityManagerFactoryBean dsB_EentityManagerFactory(
    EntityManagerFactoryBuilder builder, @Qualifier("dsB") DataSource ds ) {
	  
    return builder.dataSource(ds).packages("com.dsBjpa.entity").persistenceUnit("bbb")
      .build();
  }
    

  @Bean(name = "dsB_TransactionManager")
  public PlatformTransactionManager dsB_TransactionManager(
    @Qualifier("dsB_EentityManagerFactory") EntityManagerFactory dsB_EMF) {
    return new JpaTransactionManager(dsB_EMF);
  }
}