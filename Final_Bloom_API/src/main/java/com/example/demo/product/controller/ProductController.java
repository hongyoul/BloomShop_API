package com.example.demo.product.controller;

import java.util.List;

import com.example.demo.product.dto.ProductParamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.product.dto.ProductDTO;
import com.example.demo.product.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    //상품 등록
  	@PostMapping("/register")
  	public ResponseEntity<Integer> register(ProductDTO dto) {
  		int newNo = service.register(dto);
  		return new ResponseEntity<>(newNo, HttpStatus.CREATED); //201성공코드와 새로운 글번호를 반환한다
  	}

	// 상품 목록 - 조건 검색
	// 주소 예시
	// localhost:8080/
	// localhost:8080/products?depth1=메이크업
	// localhost:8080/products?depth2=로션&depth2=앰플&depth2=에센스
	// localhost:8080/products?depth1=메이크업&priceStart=100&priceEnd=500
	@GetMapping("/list")
	public ResponseEntity<List<ProductDTO>> getList(
			@ModelAttribute ProductParamDTO param
	) {
		List<ProductDTO> list = service.getList(param);
		return new ResponseEntity<>(list, HttpStatus.OK); //200성공코드와 게시물목록을 반환한다
	}
  	
  	//상품 상세
  	@GetMapping("/read")
  	public ResponseEntity<ProductDTO> read(@RequestParam(name = "no") int pdNo) {
  		ProductDTO dto = service.read(pdNo);
  		return new ResponseEntity<>(dto, HttpStatus.OK); //200성공코드와 게시물정보를 반환한다
  	}
  	
  	//상품 수정
  	@PutMapping("/modify")
  	public ResponseEntity modify(ProductDTO dto) {
  		 service.modify(dto);
  		 return new ResponseEntity(HttpStatus.NO_CONTENT); //204성공코드를 반환한다
  	}
  	
  	//상품 삭제
  	@DeleteMapping("/remove")
  	public ResponseEntity remove(@RequestParam(name = "no") int pdNo) {
  		service.remove(pdNo);
  		return new ResponseEntity(HttpStatus.NO_CONTENT); //204성공코드를 반환한다
  	}

}
