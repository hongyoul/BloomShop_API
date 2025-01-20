package com.example.demo.ordersDetail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ordersDetail.dto.OrdersDetailDTO;
import com.example.demo.ordersDetail.service.OrdersDetailService;

@RestController
@RequestMapping("/orders_detail")
public class OrdersDetailController {
	@Autowired
	OrdersDetailService service;

	//주문내역 목록
	@GetMapping("/list")
	public ResponseEntity<List<OrdersDetailDTO>> getList() {
		List<OrdersDetailDTO> list = service.getList();
		return new ResponseEntity<>(list, HttpStatus.OK); //200성공코드와 게시물목록을 반환한다
	}

}
