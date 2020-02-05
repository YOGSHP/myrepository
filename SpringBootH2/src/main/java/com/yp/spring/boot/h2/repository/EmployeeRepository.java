package com.yp.spring.boot.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yp.spring.boot.h2.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}
