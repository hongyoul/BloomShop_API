package com.example.demo.ordersDetail.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.ordersDetail.dto.OrdersDetailDTO;
import com.example.demo.ordersDetail.entity.OrdersDetail;
import com.example.demo.ordersDetail.repository.OrdersDetailRepository;

@Service
public class OrdersDetailServiceImpl implements OrdersDetailService{
	
    @Autowired
    private OrdersDetailRepository repository;

	
	@Override
	//주문내역 등록 메소드
	public int register(OrdersDetailDTO dto) {
		OrdersDetail entity = dtoToEntity(dto);
		repository.save(entity);
		
		return entity.getDtNo();
	}

	@Override
	//주문내역 목록조회 메소드
	public List<OrdersDetailDTO> getList() {
		List<OrdersDetail> entityList = repository.findAll();
		List<OrdersDetailDTO> dtoList = entityList.stream()
				.map(entity -> entityToDto(entity))
				.collect(Collectors.toList());
		
		return dtoList;
	}

	@Override
	//주문내역 상세 메소드
	public OrdersDetailDTO read(int dtNo) {
		Optional<OrdersDetail> result = repository.findById(dtNo);
        if(result.isPresent()) {
        	OrdersDetail OrdersDetail =  result.get();
        	return entityToDto(OrdersDetail);
        } else {
        	return null;
        }
	}

	@Override
	//주문내역 수정 메소드
	public void modify(OrdersDetailDTO dto) {
		Optional<OrdersDetail> result = repository.findById(dto.getOdCount());
		        
		//주문수량을 수정할 수 있음
		if(result.isPresent()){
			OrdersDetail entity = result.get();
			//주문수량
			entity.setOdCount(dto.getOdCount());
            repository.save(entity);
        }
		
	}

	@Override
	public void remove(int dtNo) {
		repository.deleteById(dtNo);
	}

}
