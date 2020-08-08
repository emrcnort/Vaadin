package com.packagename.myapp.Business;

import java.util.List;

import com.packagename.myapp.entities.Customer;

public interface ICustomerService {

    List<Customer> getAll();
	
	void add(Customer customer);
	
	void update(Customer customer);
	
	void delete (Customer customer);
	
	Customer getById(int id);
	
	public List<Customer> findAll(String stringFilter);
}
