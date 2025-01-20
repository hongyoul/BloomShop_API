package com.example.demo.ordersDetail.service;

import java.util.List;

import com.example.demo.orders.entity.Orders;
import com.example.demo.ordersDetail.dto.OrdersDetailDTO;
import com.example.demo.ordersDetail.entity.OrdersDetail;
import com.example.demo.product.entity.Product;

public interface OrdersDetailService {
	// 주문내역 등록 메소드
	int register(OrdersDetailDTO dto);
	
	// 주문내역 목록조회 메소드
	List<OrdersDetailDTO> getList();

	// 주문내역 상세조회 메소드
	OrdersDetailDTO read(int dtNo);

	// 주문내역 수정 메소드
	void modify(OrdersDetailDTO dto);

	// 주문내역 삭제 메소드
	void remove(int dtNo);
	
	//엔티티를 DTO로 변환하는 메소드
	default OrdersDetailDTO entityToDto(OrdersDetail entity) {
		OrdersDetailDTO dto = OrdersDetailDTO.builder()
						.dtNo(entity.getDtNo())
						.odNo(entity.getOd().getOdNo())
						.pdNo(entity.getPd().getPdNo())
						.pdName(entity.getPd().getPdName())
	                    .pdPrice(entity.getPd().getPrice()) 
	                    .thumbnail(entity.getPd().getThumnail()) 
						.odCount(entity.getOdCount())
						.odOption(entity.getOdOption() != null ? entity.getOdOption() : null)
						.build();

		return dto;
	}

	//DTO를 엔티티로 변환하는 메소드
	default OrdersDetail dtoToEntity(OrdersDetailDTO dto) {
		Orders order = Orders.builder().odNo(dto.getOdNo()).build();
		Product product = Product.builder().pdNo(dto.getPdNo()).build();
		
		OrdersDetail entity = OrdersDetail.builder()
						.dtNo(dto.getDtNo())
						.od(order)
						.pd(product)
						.odCount(dto.getOdCount())
						.odOption(dto.getOdOption() != null ? dto.getOdOption() : null)
						.build();
		return entity;
	}

}
