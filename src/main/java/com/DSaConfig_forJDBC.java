package com;

import org.springframework.context.annotation.Primary;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
public class DSaConfig_forJDBC {
  
	@Bean(name = "dsAjdbc")
	@ConfigurationProperties(prefix = "spring.dsA")
	public DataSource dsA_forJdbc() {
	  return DataSourceBuilder.create().build();
	}
  
  	@Bean(name = "dsAjdbcBean")
	public JdbcTemplate dsAjdbcTemplate(@Qualifier("dsAjdbc") DataSource ds) {		// accept dsAbean as qualifier
		return new JdbcTemplate(ds);												// return jdbcTemplate object of dsAbean 
	}

}