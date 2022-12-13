package com.example.demo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.api.entity.Customer;

@Repository
public interface CustomerRepo  extends JpaRepository<Customer,Integer>
{

	Customer findByName(String name);

}
