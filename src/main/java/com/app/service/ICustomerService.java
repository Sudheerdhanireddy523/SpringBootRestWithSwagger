package com.app.service;

import java.util.List;

import com.app.model.Customer;

public interface ICustomerService {
	public Integer saveCustomer(Customer c);
	public List<Customer> getAllCustomers();
	public Customer getOneCustomer(Integer id );
	public void deleteCustomer(Integer id);
	public boolean updateCustomer(Integer id);


}
