package com.example.demo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.api.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer>
{

}
