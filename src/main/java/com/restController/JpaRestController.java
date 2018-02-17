package com.restController;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsAjpa.entity.*;
import com.dsAjpa.repository.*;
import com.dsBjpa.entity.*;
import com.dsBjpa.repository.*;
import com.noDBjpa.Greeting;

@RestController
public class JpaRestController {
	
	private final EmployeeRepository employeeRepo;
	private final CustomerRepository customerRepo;
	private final ParentRepository parentRepo;

	@Autowired
	JpaRestController(EmployeeRepository empRepo, CustomerRepository custRepo, ParentRepository parRepo) {
		this.employeeRepo = empRepo;
		this.customerRepo = custRepo;
		this.parentRepo   = parRepo;
	}

	// --- get employee(s) with @RequestMapping, curl = localhost:8020/jpaRest/getAllEmp ---
	@RequestMapping("/jpaRest/getAllEmp")
	public @ResponseBody Iterable<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	// --- get employee(s) with @RequestMapping, curl = localhost:8020/jpaRest/getEmp/5 ---
	@RequestMapping("/jpaRest/getEmpById/{id}")
	public Employee getEmpById(@PathVariable Integer id) {
		return employeeRepo.findOne(id);  
	}
	
	// --- get Customer(s) with @GetMapping, curl = localhost:8020/jpaRest/getAllCust ---
	@GetMapping("/jpaRest/getAllCust")
	public @ResponseBody Iterable<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	// --- get employee(s) with @RequestMapping, curl = localhost:8020/jpaRest/getCust/2 ---	
	@GetMapping("/jpaRest/getCustById/{id}")
	public Customer getCustById(@PathVariable Integer id) {
		return customerRepo.findOne(id);  
	}

	// --- get Parent(s) ---
	@GetMapping("/jpaRest/getAllParent")
	public @ResponseBody Iterable<Parent> getAllParents() {
		return parentRepo.findAll();
	}	

	@GetMapping("/jpaRest/getParentById/{id}")
	public Parent getParentById(@PathVariable Integer id) {
		return parentRepo.findOne(id);  
	}

    private static final String messageString = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/jpaRest/greeting")
    public Greeting greeting(@RequestParam(value="rpName", defaultValue="World (the default value)") String personName) {
        return new Greeting(counter.incrementAndGet(), 
        					String.format(messageString, personName), "myEmail");
    }	
}

