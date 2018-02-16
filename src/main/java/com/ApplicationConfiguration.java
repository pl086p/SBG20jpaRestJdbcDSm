package com;


//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Configuration
public class ApplicationConfiguration {
    
    //@Autowired
    //private ApplicationContext applicationContext;

    public ApplicationConfiguration() {
    	System.out.println("=== ApplicationConfiguration");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
    	
    	System.out.println("=== corsConfigurer");
    	
    	return new WebMvcConfigurerAdapter() {
    		@Override
    		public void addCorsMappings(CorsRegistry reg) {
    			String origin4020 = "http://localhost:4020";
    			String origin5020 = "http://localhost:5020";
    			reg.addMapping("/jpaRest/getAllEmp").allowedOrigins(origin4020, origin5020);
    			reg.addMapping("/jpaRest/getAllCust").allowedOrigins(origin4020, origin5020);
    			reg.addMapping("/jpaRest/parents").allowedOrigins(origin4020, origin5020);
    			reg.addMapping("/greeting").allowedOrigins(origin4020, origin5020);
    		}
    	};
    }
    
	
}
