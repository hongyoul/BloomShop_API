package com.example.demo.cart.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.cart.dto.CartDTO;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.product.entity.Product;
import com.example.demo.product.entity.ProductOption;
import com.example.demo.product.repository.ProductOptionRepository;
import com.example.demo.product.repository.ProductRepository;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartRepository repository;
	
    @Autowired
    ProductRepository productRepository; // 상품 데이터 조회용 Repository
    
    @Autowired
    ProductOptionRepository productOptionRepository;
	
	@Override
	//장바구니 등록 메소드
	public int register(CartDTO dto) {
	    // 1. 상품 정보 조회
	    Optional<Product> productOpt = productRepository.findById(dto.getPdNo());
	    if (productOpt.isEmpty()) {
	        throw new IllegalArgumentException("상품 정보가 존재하지 않습니다: " + dto.getPdNo());
	    }
	    Product product = productOpt.get();

	    // 2. ProductOption 조회 및 검증
		// 옵션이 없어도 등록가능하도록 수정
	    Optional<ProductOption> productOptionOpt = productOptionRepository.findByPoOptionAndPd_PdNo(dto.getOption(), dto.getPdNo());
		ProductOption productOption = null;
		if (productOptionOpt.isEmpty()) {
			System.out.println("선택한 옵션이 존재하지 않습니다: " + dto.getOption());
//	        throw new IllegalArgumentException("선택한 옵션이 존재하지 않습니다: " + dto.getOption());
	    } else {
			productOption = productOptionOpt.get();
		}

	    // 3. 장바구니 중복 상품 확인 및 수량 추가
	    Optional<Cart> existingCart = repository.findByMember_IdAndPd_PdNo(dto.getId(), dto.getPdNo());
	    if (existingCart.isPresent()) {
	        Cart cart = existingCart.get();
	        cart.setCtCount(cart.getCtCount() + dto.getCtCount()); // 수량 추가
	        repository.save(cart);
	        return cart.getCtNo();
	    } else {
	        // Cart 엔티티 생성 및 저장
	        Cart entity = dtoToEntity(dto);
	        entity.setPo(productOption); // 연관된 ProductOption 설정
	        repository.save(entity);
	        return entity.getCtNo();
	    }
	}


	@Override
	//장바구니 목록조회 메소드
	public List<CartDTO> getList(String memberId) {
		 // 로그인한 회원의 장바구니 목록만 조회
	    List<Cart> entityList = repository.findByMemberId(memberId);

	    // 데이터가 없으면 빈 리스트 반환
	    if (entityList.isEmpty()) {
	        return Collections.emptyList();
	    }

	    // Cart 엔터티 목록을 DTO 목록으로 변환
	    return entityList.stream()
	            .map(this::entityToDto)
	            .collect(Collectors.toList());
	}

	@Override
	//장바구니 상세조회 메소드
	public CartDTO read(int ctNo) {
	  Optional<Cart> result = repository.findById(ctNo);
        if(result.isPresent()) {
        	Cart cart =  result.get();
        	return entityToDto(cart);
        } else {
        	return null;
        }
	}

	@Override
	//장바구니 수정 메소드
	public void modify(CartDTO dto) {
		Optional<Cart> result = repository.findById(dto.getCtNo());
        
		//주문수량을 수정할 수 있음
		if(result.isPresent()){
			Cart entity = result.get();
			//주문수량
			entity.setCtCount(dto.getCtCount());

			// 2. ProductOption 조회 및 검증
			// 옵션이 없어도 등록가능하도록 수정
			Optional<ProductOption> productOptionOpt = productOptionRepository.findByPoOptionAndPd_PdNo(dto.getOption(), dto.getPdNo());
			ProductOption productOption = null;
			if (productOptionOpt.isEmpty()) {
				System.out.println("선택한 옵션이 존재하지 않습니다: " + dto.getOption());
			} else {
				productOption = productOptionOpt.get();
			}
			entity.setPo(productOption); // 연관된 ProductOption 설정

            repository.save(entity);
        }
		
	}

	@Override
	//장바구니 삭제 메소드
	public void remove(int ctNo) {
		repository.deleteById(ctNo);
	}
}
