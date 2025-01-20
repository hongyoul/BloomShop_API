package com.example.demo.cart.service;

import java.util.List;

import com.example.demo.cart.dto.CartDTO;
import com.example.demo.cart.entity.Cart;
import com.example.demo.member.entity.Member;
import com.example.demo.product.entity.Product;
import com.example.demo.product.entity.ProductOption;

public interface CartService {
	// 장바구니 등록 메소드
	int register(CartDTO dto);
	
	// 장바구니 목록조회 메소드
	List<CartDTO> getList(String memberId);
	
	// 장바구니 상세 메소드
	CartDTO read(int ctNo);
	
	// 장바구니 수정 메소드
	void modify(CartDTO dto);

	// 장바구니 삭제 메소드
	void remove(int ctNo);
	
	//엔티티를 DTO로 변환하는 메소드
	default CartDTO entityToDto(Cart entity) {
	    CartDTO dto = CartDTO.builder()
	            .ctNo(entity.getCtNo()) // 장바구니 번호
	            .pdNo(entity.getPd().getPdNo()) // 상품 번호
	            .pdName(entity.getPd().getPdName()) // 상품 이름
	            .pdPrice(entity.getPd().getPrice()) // 상품 가격
	            .thumbnail(entity.getPd().getThumnail()) // 상품 썸네일
	            .id(entity.getMember().getId()) // 사용자 ID
	            .ctCount(entity.getCtCount()) // 장바구니 상품 수량
	            .option(entity.getPo() != null ? entity.getPo().getPoOption() : null) // ProductOption이 null인지 체크
	            .build();

	    return dto;
	}

	//DTO를 엔티티로 변환하는 메소드
	default Cart dtoToEntity(CartDTO dto) {
	    Product pd = Product.builder().pdNo(dto.getPdNo()).build();
	    Member member = Member.builder().id(dto.getId()).build();

	    Cart entity = Cart.builder()
	            .ctNo(dto.getCtNo())
	            .pd(pd)
	            .member(member)
	            .ctCount(dto.getCtCount())
	            .build();
	    return entity;
	}
}
