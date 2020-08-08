package com.packagename.myapp.Business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packagename.myapp.DataAccess.ICustomerDal;
import com.packagename.myapp.entities.Customer;

@Service
public class CustomerManager implements ICustomerService {

	private ICustomerDal customerDal;
	
	@Autowired
	public CustomerManager(ICustomerDal customerDal)
	{
		this.customerDal = customerDal;
	}
	
	
	@Override
	@Transactional
	public List<Customer> getAll() {
		
		return customerDal.getAll();
	}

	@Override
	@Transactional

	public void add(Customer customer) {
		 customerDal.add(customer);
		
	}

	@Override
	@Transactional

	public void update(Customer customer) {
		customerDal.update(customer);
		
	}

	@Override
	@Transactional

	public void delete(Customer customer) {
		customerDal.delete(customer);
		
	}
	

	@Override
	@Transactional
	public Customer getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	public List<Customer> findAll(String stringFilter)
	{
		if (stringFilter == null || stringFilter.isEmpty()) { 
			return customerDal.getAll();
		} else {
			return customerDal.search(stringFilter); 
		}
	}


	public String count() {
		// TODO Auto-generated method stub
		return "---";
	}


	public Map<String, Integer> getStats() {
		  HashMap<String, Integer> stats = new HashMap<>();
		  getAll().forEach(customer -> stats.put(customer.getFirstName(), 
				  5 + Double.valueOf(Math.random()*(15)).intValue())); 
		  return stats;
	}
	


	

}
