package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Employee;
import com.app.repo.EmployeeRepository;
import com.app.service.IEmployeeService;
@Service
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
	private EmployeeRepository repo;
	
	@Transactional
	public Integer saveEmployee(Employee e) {
		// TODO Auto-generated method stub
		return repo.save(e).getEid();
	}

	@Transactional(readOnly = true)
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Transactional(readOnly = true)
	public Employee getOneEmployee(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Transactional
	public void deleteEmployee(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Transactional
	public boolean updateEmployee(Integer id) {
		// TODO Auto-generated method stub
		return repo.existsById(id);
	}
	
	

}
