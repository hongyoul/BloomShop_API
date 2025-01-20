package com.example.demo.orders.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.orders.dto.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.orders.dto.OrdersDTO;
import com.example.demo.orders.entity.Orders;
import com.example.demo.orders.repository.OrdersRepository;
import com.example.demo.ordersDetail.entity.OrdersDetail;
import com.example.demo.ordersDetail.repository.OrdersDetailRepository;

@Service
public class OrdersServiceImpl implements OrdersService{
	@Autowired
	OrdersRepository ordersRepository;
	
    @Autowired
    private OrdersDetailRepository ordersDetailRepository;

    @Autowired
    private CartRepository cartRepository;

	@Autowired
	PaymentUtil paymentUtil;

	@Override
	// 주문 등록 메소드
	public int register(PaymentRequest request) {

		// 1. 주문 데이터를 먼저 저장
		//dto를 엔티티로 변환
		OrdersDTO dto = request.getOrders();
		Orders order = dtoToEntity(dto);
		
		//oders 테이블에 저장
		Orders savedOrder = ordersRepository.save(order);
		
		//장바구니 데이터로 orders_detail 엔터티 생성 및 저장
		List<Cart> cartList = cartRepository.findByMemberId(dto.getId());
		for (Cart cart : cartList) {
			 OrdersDetail orderDetail = OrdersDetail.builder()
		                .od(savedOrder) // 주문 번호
		                .pd(cart.getPd()) // 상품 정보
		                .odCount(cart.getCtCount()) //수량
		                .odOption(cart.getPo() != null ? cart.getPo().getPoOption() : null) //옵션
		                .build();
	        ordersDetailRepository.save(orderDetail);
		}
		
		//장바구니 비우기
		cartRepository.deleteAll(cartList);

		// 2. 결제 프로세스 시작
//		paymentUtil.payComplete();
		
		return savedOrder.getOdNo();
	}
	
	@Override
	//일반사용자 목록 조회
    public List<OrdersDTO> getListByMemberId(String memberId) {
        // 1️. 일반사용자의 주문 목록 조회
        List<Orders> orders = ordersRepository.findByMember_Id(memberId);

        // 2️. 엔티티를 DTO로 변환
        return orders.stream()
                .map(entity -> entityToDto(entity)) // entityToDto 메서드 사용
                .collect(Collectors.toList());
    }

	@Override
	// 전체주문 목록조회 메소드(관리자)
	public List<OrdersDTO> getList(String memberId) {
		// 모든 주문 조회
	    List<Orders> entityList = ordersRepository.findAll();

	    // 엔티티를 DTO로 변환
	    List<OrdersDTO> dtoList = entityList.stream()
	            .map(entity -> entityToDto(entity))
	            .collect(Collectors.toList());

	    return dtoList;
	}
	
	@Override
	//주문 상세 메소드
	public OrdersDTO read(int odNo) {
        Optional<Orders> result = ordersRepository.findById(odNo);
        if(result.isPresent()) {
        	Orders order =  result.get();
        	return entityToDto(order);
        } else {
        	return null;
        }
	}

	@Override
	// 주문 수정 메소드
	public void modify(OrdersDTO dto) {
		Optional<Orders> result = ordersRepository.findById(dto.getOdNo());
        
		//수취인, 배송지, 연락처는 수정할 수 있음
		if(result.isPresent()){
        	Orders entity = result.get();
        	//총금액
        	entity.setTotalPrice(dto.getTotalPrice());
        	//수취인
            entity.setOdName(dto.getOdName());
            //배송지
            entity.setOdAddress(dto.getOdAddress());
            //연락처
            entity.setOdPhone(dto.getOdPhone());
            //결제방법
            entity.setPayMethod(dto.getPayMethod());
            ordersRepository.save(entity);
        }
	}

	@Override
	// 주문 삭제 메소드
	public void remove(int odNo) {
		ordersRepository.deleteById(odNo);
	}
	

}
