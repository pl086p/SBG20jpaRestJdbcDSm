package com.dsAjpa.repository;

import org.springframework.data.repository.CrudRepository;
import com.dsAjpa.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
