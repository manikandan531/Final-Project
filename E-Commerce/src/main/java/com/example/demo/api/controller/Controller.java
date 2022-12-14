package com.example.demo.api.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.dto.OrderRequest;
import com.example.demo.api.entity.Customer;
import com.example.demo.api.model.JwtRequest;
import com.example.demo.api.model.JwtResponse;
import com.example.demo.api.repository.CustomerRepo;
import com.example.demo.api.service.CustomUserDetailService;

import com.example.demo.api.utility.JWTUtility;

@RestController
public class Controller 
{
	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired(required=true)
	private CustomerRepo customerRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	
	
	@PostMapping("/AddtoCart")
	public Customer placeorder(@RequestBody OrderRequest request)
	{
		Customer customer=new Customer();
		customer.setUsername(request.getUsername());
		customer.setEmail(request.getEmail());
		customer.setPassword(request.getPassword());
		customer.setPhone(request.getPhone());
		
		customer.setProduct(request.getProduct());
		customerRepo.save(customer);
		return customer;
	}
	
	@GetMapping("/ShowAll")
	public List<Customer> showAllProducts()
	{
		return customerRepo.findAll();
	}
	
	
	  @PutMapping("/Update/{id}") 
	  public Customer updatecustomer(@PathVariable   ("id") Integer id,@RequestBody OrderRequest request) 
	  { 
		  Customer updatedvalue=customerRepo.findById(id).get();
	  
	  if(Objects.nonNull(request.getUsername()) &&!"".equalsIgnoreCase(request.getUsername()))
	  {
		  updatedvalue.setUsername(request.getUsername()); 
	  }
	  
	  if(Objects.nonNull(request.getEmail()) &&!"".equalsIgnoreCase(request.getEmail()))
	  {
		 updatedvalue.setEmail(request.getEmail()); 
	  }
	 
	  if(Objects.nonNull(request.getPassword()) &&!"".equalsIgnoreCase(request.getPassword()))
	  {
		 updatedvalue.setPassword(request.getPassword()) ;
	  }
	  
	  if(Objects.nonNull(request.getPhone()) && !"".equalsIgnoreCase(request.getPhone())) 
	  {
		  updatedvalue.setPhone(request.getPhone()); 
	  } 
	  return customerRepo.save(updatedvalue);
	  }
	 
	 
	
	 @DeleteMapping("/{id}") public String DeleteCustomer(@PathVariable("id")Integer id) 
	 {
		 customerRepo.deleteById(id);
		 return "Selected Rows Deleted Successfully"+id;
	 }
	 

	    @GetMapping("/")
	    public String home() 
	    {
	        return "Welcome to hubino technologies!!";
	    }

	    @PostMapping("/authenticate")
	    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception
	    {
	    	try
	    	{
	    	 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
	    	}
	    	catch (Exception e) 
	    	{
				throw new Exception("invalid username");
			}
	    	 
	    	UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getUsername());
	    	
	    	String token = jwtUtility.generateToken(userDetails);
	    	
	    	return new JwtResponse(token);
	    	
	    }
}

