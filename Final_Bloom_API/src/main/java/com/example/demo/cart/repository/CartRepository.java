package com.example.demo.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	// 특정 회원과 특정 상품 검색
	Optional<Cart> findByMember_IdAndPd_PdNo(String memberId, int pdNo);
	
	// 특정 회원의 장바구니 목록 조회
	List<Cart> findByMemberId(String memberId);
}
