package com.example.demo.product.service;

import java.util.List;

import com.example.demo.product.dto.ProductDTO;
import com.example.demo.product.dto.ProductParamDTO;
import com.example.demo.product.entity.Product;

public interface ProductService {

	// 상품 등록 메소드
	int register(ProductDTO dto);
	
	// 상품 목록조회 메소드
	List<ProductDTO> getList(ProductParamDTO paramDTO);

	// 상품 상세조회 메소드
	ProductDTO read(int pdNo);

	// 상품 수정 메소드
	void modify(ProductDTO dto);

	// 상품 삭제 메소드
	void remove(int pdNo);
	
	//엔티티를 DTO로 변환하는 메소드
		default ProductDTO entityToDto(Product entity) {
			ProductDTO dto = ProductDTO.builder()
							.pdNo(entity.getPdNo())
							.pdName(entity.getPdName())
							.depth1(entity.getDepth1())
							.depth2(entity.getDepth2())
							.comment(entity.getComment())
							.price(entity.getPrice())
							.thumnail(entity.getThumnail())
							.pdCount(entity.getPdCount())
							.build();

			return dto;
		}

		//DTO를 엔티티로 변환하는 메소드
		default Product dtoToEntity(ProductDTO dto) {
			Product entity = Product.builder()
							.pdNo(dto.getPdNo())
							.pdName(dto.getPdName())
							.depth1(dto.getDepth1())
							.depth2(dto.getDepth2())
							.comment(dto.getComment())
							.price(dto.getPrice())
							.thumnail(dto.getThumnail())
							.pdCount(dto.getPdCount())
							.build();
			return entity;
		}

}
