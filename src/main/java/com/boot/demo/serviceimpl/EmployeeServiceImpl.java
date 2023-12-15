package com.boot.demo.serviceimpl;

import static com.boot.demo.constants.EmployeeConstants.EMPLOYEE_NOT_FOUND;
import static com.boot.demo.constants.EmployeeConstants.WITH_ID;
import static com.boot.demo.logger.Logger.LOG;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.demo.dto.EmployeeDto;
import com.boot.demo.entity.EmployeeEntity;
import com.boot.demo.exception.EmployeeNotFoundException;
import com.boot.demo.repository.EmployeeRepo;
import com.boot.demo.service.EmployeeService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired
	EmployeeRepo repo;

	@Override
	public EmployeeDto save(EmployeeDto employee) {
		EmployeeEntity entity = repo.save(convertDtoToEntity(employee));
		return convertEntityToDto(entity);
	}

	@Override
	public EmployeeDto update(EmployeeDto employee) {
		Optional<EmployeeEntity> entity = checkIfEmployeePresent(employee.getId());
		if(entity.isPresent()) {
			return convertEntityToDto(repo.save(convertDtoToEntity(employee)));
		}
		LOG.error(EMPLOYEE_NOT_FOUND  + WITH_ID + "{}" , employee.getId());
		throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND  + WITH_ID + employee.getId());
	}
	
	Optional<EmployeeEntity> checkIfEmployeePresent(Long id) {
		return repo.findById(id);
	}

	@Override
	public EmployeeDto delete(Long employeeId) {
		Optional<EmployeeEntity> entity = checkIfEmployeePresent(employeeId);
		if(entity.isPresent())
			repo.delete(entity.get());
		else {
			LOG.error(EMPLOYEE_NOT_FOUND  + WITH_ID + "{}" , employeeId);
			throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND  + WITH_ID + employeeId);
		}
		return convertEntityToDto(entity.get());
	}

	@Override
	public List<EmployeeDto> viewAll() {
		List<EmployeeDto> resultList = repo.findAll().stream().map(entity -> {
			EmployeeDto dto = new EmployeeDto();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}).toList();
		if (resultList.isEmpty()) {
			LOG.error(EMPLOYEE_NOT_FOUND);
			throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND);
		}
		return resultList;

	}

	@Override
	public EmployeeDto employeeById(Long employeeId) {
		Optional<EmployeeEntity> entity = checkIfEmployeePresent(employeeId);
		if (entity.isPresent())
			return convertEntityToDto(entity.get());
		LOG.error(EMPLOYEE_NOT_FOUND  + WITH_ID + "{}" , employeeId);	
		throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND + WITH_ID + employeeId);
	}
	
	private EmployeeEntity convertDtoToEntity(EmployeeDto dto) {
		EmployeeEntity entity = new EmployeeEntity();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}
	
	private EmployeeDto convertEntityToDto(EmployeeEntity entity) {
		EmployeeDto dto = new EmployeeDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

}
