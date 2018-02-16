package com.dsAjpa.repository;

import org.springframework.data.repository.CrudRepository;
import com.dsAjpa.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
