package com.boot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.demo.dto.EmployeeDto;
import com.boot.demo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {
	
	@Autowired
	EmployeeService service;

	
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getEmployees(){
		return new ResponseEntity<>(service.viewAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<EmployeeDto> getEmployeesById(@PathVariable Long Id){
		return new ResponseEntity<>(service.employeeById(Id),HttpStatus.OK);
	}
	
	
	
	@PostMapping
	public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto employeeDto){
		return new ResponseEntity<>(service.save(employeeDto), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto employeeDto){
		return new ResponseEntity<>(service.update(employeeDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{Id}")
	public ResponseEntity<EmployeeDto> delete(@PathVariable Long Id){
		return new ResponseEntity<>(service.delete(Id), HttpStatus.OK);
	}
	
	
}
