package com.example.demo.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.api.entity.Customer;

@Service
public interface CustomerService 
{

	List<Customer> showCustomer(Customer customer);

	void deleteCustomer(Integer id);

	Customer saveCustomer(Customer customer);

	Customer updateCustomer(Integer id, Customer customer);

}
