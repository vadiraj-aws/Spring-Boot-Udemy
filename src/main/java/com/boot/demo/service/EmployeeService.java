package com.boot.demo.service;

import java.util.List;

import com.boot.demo.dto.EmployeeDto;


public interface EmployeeService {
	
	EmployeeDto save(EmployeeDto employee);
	
	EmployeeDto update(EmployeeDto employee);
	
	EmployeeDto delete(Long employeeId);
	
	List<EmployeeDto> viewAll();
	
	EmployeeDto employeeById(Long employeeId);
	
	

}
