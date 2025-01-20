package com.example.demo.review.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.product.repository.ProductRepository;
import com.example.demo.review.dto.ReviewDTO;
import com.example.demo.review.entity.Review;
import com.example.demo.review.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	ReviewRepository repository;
	
	@Autowired
	ProductRepository productRepository;
	@Override
	// 리뷰 등록 메소드
	public int register(ReviewDTO dto) {
		Review entity = dtoToEntity(dto);
		
		repository.save(entity);
		int reNo = entity.getReNo();
		
		return reNo;
	}

	@Override
	// 리뷰 목록조회 메소드 (아이디로)
	public List<ReviewDTO> getList(String memberId) {
		// 1. 회원 ID로 리뷰 조회
	    List<Review> reviews = repository.findByMember_Id(memberId);
	    
	    return reviews.stream()
	            .map(this::entityToDto)
	            .collect(Collectors.toList());
	}
	
	@Override
	// 리뷰 목록조회 메소드 (상품별)
	public List<ReviewDTO> getProductList(int pdNo) {
		// 1. 상품 번호로 리뷰 조회
	    List<Review> reviews = repository.findByProduct_PdNo(pdNo);
	    return reviews.stream()
	            .map(this::entityToDto)
	            .collect(Collectors.toList());
	}

	@Override
	//리뷰 상세조회 메소드
	public ReviewDTO read(int reNo) {
		Optional<Review> result = repository.findById(reNo);
		 if(result.isPresent()) {
	        	Review review =  result.get();
	        	return entityToDto(review);
	        } else {
	        	return null;
	        }
	}

	@Override
	//리뷰 수정 메소드
	public void modify(ReviewDTO dto) {
		Optional<Review> result = repository.findById(dto.getReNo());
		
		if(result.isPresent()) {
			Review entity = result.get();
			
			//내용
			entity.setContent(dto.getContent());
			repository.save(entity);
		}
		
	}

	@Override
	//리뷰 삭제 메소드
	public void remove(int reNo) {
		 repository.deleteById(reNo);
	}


	
}
