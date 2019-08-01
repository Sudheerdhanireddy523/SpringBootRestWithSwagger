package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Customer;
import com.app.repo.CustomerRepository;
import com.app.service.ICustomerService;
@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private CustomerRepository repo;
    
	@Transactional
	public Integer saveCustomer(Customer c) {
		// TODO Auto-generated method stub
		return repo.save(c).getCid();
	}

	@Transactional(readOnly = true)
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Transactional(readOnly = true)
	public Customer getOneCustomer(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Transactional
	public void deleteCustomer(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id); 
		
	}

	@Transactional
	public boolean updateCustomer(Integer id) {
		// TODO Auto-generated method stub
		return repo.existsById(id);
	}

}
