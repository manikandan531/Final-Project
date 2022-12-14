package com.example.demo.api.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.entity.Customer;
import com.example.demo.api.repository.CustomerRepo;
import com.example.demo.api.repository.ProductRepo;

@Service
public abstract class CustomerServiceImple  implements CustomerService
{
	@Autowired
	private CustomerRepo cusrepo;
	
	@Autowired
	private ProductRepo prorepo;

	@Override
	public Customer saveCustomer(Customer customer)
	{
		return cusrepo.save(customer);
	}

	@Override
	public List<Customer> showCustomer(Customer customer) 
	{
		
		return cusrepo.findAll();
	}

	@Override
	public void deleteCustomer(Integer id) 
	{
		
		cusrepo.deleteById(id);
	}

	@Override
	public Customer updateCustomer(Integer id, Customer customer)
	{
		
		        Customer updatedvalue=cusrepo.findById(id).get();
		        System.out.println("username"+customer.getUsername());
		        System.out.println("email"+customer.getEmail());
		        System.out.println("password"+customer.getPassword());
		        System.out.println("phone"+customer.getPhone());
		        
		        if(Objects.nonNull(customer.getUsername()) && !"".equalsIgnoreCase(customer.getUsername()))
		        {
		            updatedvalue.setUsername(customer.getUsername());
		            
		        }
		        
		        if(Objects.nonNull(customer.getEmail()) && !"".equalsIgnoreCase(customer.getEmail()))
		        {
		            updatedvalue.setEmail(customer.getEmail());
		        }
		        

		        if(Objects.nonNull(customer.getPassword()) && !"".equalsIgnoreCase(customer.getPassword()))
		        {
		            updatedvalue.setPassword(customer.getPassword());
		        }
		        
		        
		        if(Objects.nonNull(customer.getPhone()) && !"".equalsIgnoreCase(customer.getPhone()))
		        {
		            updatedvalue.setPhone(customer.getPhone());
		        }
		        return cusrepo.save(updatedvalue);
		    
	}
	
	
	
}
