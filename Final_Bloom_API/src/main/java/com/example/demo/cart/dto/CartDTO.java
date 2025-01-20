package com.example.demo.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {
	
	int ctNo; //장바구니번호
	
	int pdNo; //상품번호
	
	String pdName; // 상품 이름
	
	int pdPrice; // 상품 가격
	
	String thumbnail; // 상품 썸네일
	
	String id; //아이디
	
	int ctCount; //주문수량
	
	String option; //옵션
}
