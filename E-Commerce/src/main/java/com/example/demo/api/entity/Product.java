package com.example.demo.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product
{
	@Id
	@GeneratedValue
	@Column(name="pid")
	private Integer pid;
	@Column(name="productName")
	private String productName;
	@Column(name="quantity")
	private Integer quantity;
	@Column(name="Price")
	private  Double price;
	
}
