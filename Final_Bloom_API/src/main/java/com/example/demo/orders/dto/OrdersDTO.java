package com.example.demo.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersDTO {
	int odNo; //주문번호
	
	String id; //아이디

	int totalPrice; //총금액

	String odAddress; //배송지

	String odName; //수취인

	String odPhone; //연락처

	String payMethod; //결제방법

	LocalDateTime regDate; //등록일

	LocalDateTime modDate; //수정일

}
