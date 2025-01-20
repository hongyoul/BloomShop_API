package com.example.demo.ordersDetail.dto;

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
public class OrdersDetailDTO {
	int dtNo; //주문상세 번호
	
	int pdNo; //상품번호
	
	String pdName; // 상품 이름
	
	int pdPrice; // 상품 가격
	
	String thumbnail; // 상품 썸네일
	
	int odNo; //주문번호
	
	int odCount; //주문수량
	
	String odOption; //옵션
}
