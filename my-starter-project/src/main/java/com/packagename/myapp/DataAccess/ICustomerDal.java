package com.packagename.myapp.DataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.packagename.myapp.entities.Customer;


public interface ICustomerDal {

    List<Customer> getAll();
	
	void add(Customer customer);
	
	void update(Customer customer);
	
	void delete (Customer customer);
	
	Customer getById(int id);

	List<Customer> search(String stringFilter);
	

}
