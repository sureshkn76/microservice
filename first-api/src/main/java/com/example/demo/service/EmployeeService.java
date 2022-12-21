package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.entity.GroceryItem;
import com.example.demo.repository.EmployeeRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepo;

	public List<EmployeeEntity> getEmployeeDetails() {
		return employeeRepo.findAll();
	}

	public void addItem(EmployeeEntity employee) {
		employeeRepo.save(employee);
	}
}
