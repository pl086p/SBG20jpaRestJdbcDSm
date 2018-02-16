package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import com.dsAjpa.entity.Employee;
import com.dsAjpa.repository.EmployeeRepository;
import com.dsBjpa.entity.Parent;
import com.dsBjpa.repository.ParentRepository;
//import com.dsAjpa.entity.Library;
//import com.dsAjpa.repository.LibraryRepository;

@SpringBootApplication
public class Application {


	@Autowired
	@Qualifier("dsAjdbcBean")
	JdbcTemplate jdbcA;
	
	@Autowired
	@Qualifier("dsBjdbcBean")
	JdbcTemplate jdbcB;


	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    
    // get data from JPA 
    @Bean
    public CommandLineRunner getDataFromJpa(EmployeeRepository empRepo, ParentRepository parRepo) {
    	return (args) -> {

    		System.out.println("  ");
    		System.out.println("=== get data from JPA - with Java application to show repository");
    		
    		System.out.println("\n == Get dsA.Employee via Jdbc"); 
    		for (Employee employee : empRepo.findAll()) {
    	   		System.out.println("     - id = " + employee.getId() + ", name = " + employee.getName() );
    		}

    		System.out.println("\n == Get dsB.Parent via Jdbc"); 
    		for (Parent parent : parRepo.findAll()) {
    	   		System.out.println("     - id = " + parent.getId() + ", name = " + parent.getName() );
    		}    		
  		
    	};
    }
    
    
    // get data from JDBC 
    @Bean
    public CommandLineRunner getDataFromJdbc() {
    	return (args) -> {

    		System.out.println("  ");
    		System.out.println("=== get data from JDBC - with Java application to show recordset");

			String sql;
			SqlRowSet rs;
			
			System.out.println("\n == Get dsA.Custome via Jdbc"); 
			sql = "select * from customer";
			rs = jdbcA.queryForRowSet(sql);
	        while (rs.next()) {
	        	System.out.println("    - " + rs.getString("customer_name"));   
	        }
	        
			
			System.out.println("\n == Get dsB.Parent via Jdbc");  
			sql = "select * from parent";
			rs = jdbcB.queryForRowSet(sql);
	        while (rs.next()) {
	        	System.out.println("    - " + rs.getString("name"));   
	        }	        
    		  		
    	};
    }  

}
