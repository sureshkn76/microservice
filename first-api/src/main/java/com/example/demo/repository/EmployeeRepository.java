package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.entity.EmployeeEntity;


public interface EmployeeRepository extends MongoRepository<EmployeeEntity, String> {
}
