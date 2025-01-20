package com.example.demo.orders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.orders.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	 // 특정 사용자의 주문 목록 조회
    List<Orders> findByMember_Id(String memberId);
}
