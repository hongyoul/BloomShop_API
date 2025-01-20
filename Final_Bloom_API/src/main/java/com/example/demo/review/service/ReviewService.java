package com.example.demo.review.service;

import java.util.List;

import com.example.demo.member.entity.Member;
import com.example.demo.product.entity.Product;
import com.example.demo.review.dto.ReviewDTO;
import com.example.demo.review.entity.Review;

public interface ReviewService {
	// 리뷰 등록 메소드
	int register(ReviewDTO dto);
	
	// 리뷰 목록조회 메소드 (아이디로)
	List<ReviewDTO> getList(String memberId);
	
	// 리뷰 목록조회 메소드 (상품별)
	List<ReviewDTO> getProductList(int pdNo);
	
	// 리뷰 상세 메소드
	ReviewDTO read(int reNo);
	
	// 리뷰 수정 메소드
	void modify(ReviewDTO dto);

	// 리뷰 삭제 메소드
	void remove(int reNo);
	
	//엔티티를 DTO로 변환하는 메소드
	default ReviewDTO entityToDto(Review entity) {
		
		ReviewDTO dto = ReviewDTO.builder()
						.reNo(entity.getReNo())
						.id(entity.getMember().getId())
						.pdNo(entity.getProduct().getPdNo())
						.content(entity.getContent())
						.regDate(entity.getRegDate())
						.modDate(entity.getModDate())
						.build();

		return dto;
	}

	//DTO를 엔티티로 변환하는 메소드
	default Review dtoToEntity(ReviewDTO dto) {
		Product pdNo = Product.builder().pdNo(dto.getPdNo()).build();
		Member id = Member.builder().id(dto.getId()).build();

		
		Review entity = Review.builder()
							.reNo(dto.getReNo())
							.product(pdNo)
							.member(id)
							.content(dto.getContent())
							.build();
		return entity;
	}
}
