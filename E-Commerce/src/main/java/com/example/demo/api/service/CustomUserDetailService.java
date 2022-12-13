package com.example.demo.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.api.entity.Customer;
import com.example.demo.api.repository.CustomerRepo;

@Service
public class CustomUserDetailService  implements UserDetailsService
{
	@Autowired
	CustomerRepo customerRepo;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException 
	{
		Customer customer = customerRepo.findByName(name);
		if(customer==null)
		{
			throw new UsernameNotFoundException("invalidusername");
		}
		return new User(customer.getName(),customer.getPassword(),new ArrayList<>());
	}

	
	
	

}
