package com.example.demo.orders.service;

import java.util.List;

import com.example.demo.member.entity.Member;
import com.example.demo.orders.dto.OrdersDTO;
import com.example.demo.orders.dto.PaymentRequest;
import com.example.demo.orders.entity.Orders;

public interface OrdersService {
		// 주문 등록 메소드
		int register(PaymentRequest request);
		
		// 주문 목록조회 메소드
		List<OrdersDTO> getList(String memberId);
		
		//특정 사용자의 주문 목록 조회
		List<OrdersDTO> getListByMemberId(String memberId);
		
		// 주문 상세 메소드
		OrdersDTO read(int odNo);
		
		// 주문 수정 메소드
		void modify(OrdersDTO dto);

		// 주문 삭제 메소드
		void remove(int odNo);
		
		//엔티티를 DTO로 변환하는 메소드
		default OrdersDTO entityToDto(Orders entity) {
			
			OrdersDTO dto = OrdersDTO.builder()
							.odNo(entity.getOdNo())
							.id(entity.getMember().getId())
							.totalPrice(entity.getTotalPrice())
							.odAddress(entity.getOdAddress())
							.odName(entity.getOdName())
							.odPhone(entity.getOdPhone())
							.payMethod(entity.getPayMethod())
							.regDate(entity.getRegDate())
							.modDate(entity.getModDate())
							.build();

			return dto;
		}

		//DTO를 엔티티로 변환하는 메소드
		default Orders dtoToEntity(OrdersDTO dto) {
			Member member = Member.builder().id(dto.getId()).build();
			
			Orders entity = Orders.builder()
								.odNo(dto.getOdNo())
								.member(member)
								.totalPrice(dto.getTotalPrice())
								.odAddress(dto.getOdAddress())
								.odName(dto.getOdName())
								.odPhone(dto.getOdPhone())
								.payMethod(dto.getPayMethod())
								.build();
			return entity;
		}
}
