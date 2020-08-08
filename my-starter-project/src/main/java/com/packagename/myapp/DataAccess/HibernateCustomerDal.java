package com.packagename.myapp.DataAccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.valueextraction.Unwrapping.Unwrap;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packagename.myapp.entities.Customer;

@Repository
public class HibernateCustomerDal implements ICustomerDal{

	private EntityManager entityManager;
	
	
	@Autowired
	public HibernateCustomerDal (EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}
	
	
	@Override
	@Transactional
	public List<Customer> getAll() {
           Session session = entityManager.unwrap(Session.class);
           List<Customer> customerList = session.createQuery("from Customer",Customer.class).getResultList();
           return customerList;
	}

	@Override
	@Transactional
	public void add(Customer customer) {
		 Session session = entityManager.unwrap(Session.class);
		 session.saveOrUpdate(customer);
		
	}

	@Override
	@Transactional
	public void update(Customer customer) {
		    Session session = entityManager.unwrap(Session.class);
		    session.saveOrUpdate(customer);
		
	}

	@Override
	@Transactional
	public void delete(Customer customer) {
		    Session session = entityManager.unwrap(Session.class);
		    Customer customerToDelete = session.get(Customer.class, customer.getId());
		    session.delete(customerToDelete);
		
	}

	@Override
	@Transactional
	public Customer getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@Transactional
	public List<Customer> search(String stringFilter) {
		Session session = entityManager.unwrap(Session.class);
        List<Customer> customerList = session.createQuery("FROM Customer c where lower(c.firstName) like lower(concat('%',:firstName,'%'))"
        		+ "or lower(c.lastName) like lower(concat('%',:lastName,'%'))",Customer.class)
        		.setParameter("firstName", stringFilter)
        		.setParameter("lastName", stringFilter)
        		.getResultList();
        return customerList;
		
	}




}
