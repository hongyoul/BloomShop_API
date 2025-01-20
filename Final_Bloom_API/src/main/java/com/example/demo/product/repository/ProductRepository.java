package com.example.demo.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.product.entity.Product;

import jakarta.transaction.Transactional;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

// QuerydslPredicateExecutor? 동적 검색을 위한 기능
public interface ProductRepository extends JpaRepository<Product, Integer>, QuerydslPredicateExecutor<Product> {

}
