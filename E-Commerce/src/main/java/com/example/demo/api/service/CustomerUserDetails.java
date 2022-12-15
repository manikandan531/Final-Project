package com.example.demo.api.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.api.entity.Customer;

public class CustomerUserDetails implements UserDetails
{
	
	
	private Customer customer;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
	
		return new ArrayList<>();
	}

	@Override
	public String getPassword() 
	{
		
		return customer.getPassword();
	}

	@Override
	public String getUsername() 
	{
	
		return customer.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() 
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() 
	{
		
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		
		return true;
	}

}
