package com.dsBjpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dsBjpa.entity.Parent;

@Repository
public interface ParentRepository extends CrudRepository<Parent, Integer>{
	
}