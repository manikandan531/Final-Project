package com.example.demo.api.dto;

import java.util.List;

import com.example.demo.api.entity.Customer;
import com.example.demo.api.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest 
{
	
	private List<Product> product;

	private String name;
	private String email;
	private String password;
	private String phone;
	
	

	
}
