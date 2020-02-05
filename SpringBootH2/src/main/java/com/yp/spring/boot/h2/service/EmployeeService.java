package com.yp.spring.boot.h2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yp.spring.boot.h2.exception.RecordNotFoundException;
import com.yp.spring.boot.h2.model.Employee;
import com.yp.spring.boot.h2.repository.EmployeeRepository;

@Service
	public class EmployeeService{
	     
	    @Autowired
	    EmployeeRepository repository;
	    
	    public List<Employee> getAllEmployees()
	    {
	        List<Employee> employeeList = repository.findAll();
	         
	        if(employeeList.size() > 0) {
	            return employeeList;
	        } else {
	            return new ArrayList<Employee>();
	        }
	    }
	    
	    
	    public Employee getEmployeeById(Long id) throws RecordNotFoundException 
	    {
	        Optional<Employee> employee = repository.findById(id);
	         
	        if(employee.isPresent()) {
	            return employee.get();
	        } else {
	            throw new RecordNotFoundException("No employee record exist for given id");
	        }
	    }
	    
	    
	    public Employee createOrUpdateEmployee(Employee entity) throws RecordNotFoundException 
	    {
	    	if(entity.getId() != null) {
	        Optional<Employee> employee = repository.findById(entity.getId());
	         
	        if(employee.isPresent()) 
	        {
	            Employee newEntity = employee.get();
	            newEntity.setEmail(entity.getEmail());
	            newEntity.setFirstName(entity.getFirstName());
	            newEntity.setLastName(entity.getLastName());
	 
	            newEntity = repository.save(newEntity);
	             
	            return newEntity;
	        } else {
	            entity = repository.save(entity);
	             
	            return entity;
	        }
	    	}
	    	else {
	    		entity = repository.save(entity);
	        	  return entity;
	    	}
	    } 
	    
	    
	    public void deleteEmployeeById(Long id) throws RecordNotFoundException 
	    {
	        Optional<Employee> employee = repository.findById(id);
	         
	        if(employee.isPresent()) 
	        {
	            repository.deleteById(id);
	        } else {
	            throw new RecordNotFoundException("No employee record exist for given id");
	        }
	    }

	
	}

