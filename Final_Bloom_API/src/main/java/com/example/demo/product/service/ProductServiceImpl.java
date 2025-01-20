package com.example.demo.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.review.repository.ReviewRepository;
import com.example.demo.util.S3FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.product.dto.ProductDTO;
import com.example.demo.product.dto.ProductParamDTO;
import com.example.demo.product.entity.Product;
import com.example.demo.product.entity.ProductDetail;
import com.example.demo.product.entity.ProductOption;
import com.example.demo.product.entity.QProduct;
import com.example.demo.product.repository.ProductDetailRepository;
import com.example.demo.product.repository.ProductOptionRepository;
import com.example.demo.product.repository.ProductRepository;
import com.example.demo.util.FileUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

@Service 
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository repository;

	@Autowired
	ProductDetailRepository detailRepository;

	@Autowired
	ProductOptionRepository optionRepository;

	@Autowired
	ReviewRepository reviewRepository;
	
//	@Autowired
//	FileUtil fileUtil;

	@Autowired
	S3FileUtil fileUtil;

	@Override
	//상품 등록 메소드
	public int register(ProductDTO dto) {
	
		// 상품 저장
		Product entity = dtoToEntity(dto);

		// 상품 썸네일 저장
		MultipartFile thumnailFile = dto.getThumnailFile();
		if(thumnailFile != null){
			String filename = fileUtil.fileUpload(thumnailFile);
			entity.setThumnail(filename);
		}

		repository.save(entity);
		int pdNo = entity.getPdNo();

		// 상품 상세 이미지 저장
		List<MultipartFile> detailImgFiles = dto.getDetailImgFiles();
		// fileUtil을 사용하여 폴더에 파일을 저장한 후 파일명 반환
		// 반환받은 파일의 이름을 상세테이블에 저장
		for( MultipartFile file : detailImgFiles ){
			String filename = fileUtil.fileUpload(file);
			ProductDetail detail = ProductDetail.builder()
													.pd(entity)
													.detailImg(filename)
													.build();
			detailRepository.save(detail);
		}
		
		// 옵션 저장
		List<String> options = dto.getOption();

		if(options != null){
			for( String option : options ){
				ProductOption productOption = ProductOption.builder()
						.pd(entity)
						.poOption(option)
						.build();
				optionRepository.save(productOption);
			}
		}

		return pdNo;
	}
	
	//상품 목록 메소드
	@Override
	public List<ProductDTO> getList(ProductParamDTO param) {

		// 검색 조건 확인
		boolean isParam = false;

		List<Product> entityList = null;

		BooleanExpression expression = Expressions.asBoolean(true).isTrue();

		QProduct product = QProduct.product;

		// param validation
		if(param != null){
			if(param.getDepth1() != null){
				isParam = true;
				BooleanExpression depth1Condition = product.depth1.contains(param.getDepth1());
				expression = expression.and(depth1Condition);
			}
			if(param.getDepth2() != null){
				isParam = true;
				BooleanExpression depth2Condition = product.depth2.in(param.getDepth2());
				expression = expression.and(depth2Condition);
			}
			if(param.getPriceEnd() != 0){
				isParam = true;
				BooleanExpression priceCondition = product.price.between(param.getPriceStart(), param.getPriceEnd());
				expression = expression.and(priceCondition);
			}
		}

		if(isParam){
			Iterable<Product> iterable = repository.findAll(expression);
			entityList = new ArrayList<>();
			for(Product p : iterable){
				entityList.add(p);
			}
		}else{
			entityList = repository.findAll();
		}

		List<ProductDTO> dtoList = entityList.stream()
				.map(entity -> {
					ProductDTO dto = entityToDto(entity);
					List<String> options = optionRepository.selectByPdNo(entity.getPdNo());
					dto.setOption(options);
					return dto;
				})
				.collect(Collectors.toList());

		return dtoList;
	}

	@Override
	//상품 상세 메소드
	public ProductDTO read(int pdNo) {
		Optional<Product> result = repository.findById(pdNo); // 특정 상품 정보 가져오기

		if (result.isPresent()) {
			Product product = result.get();
			ProductDTO dto = entityToDto(product);

			List<String> list = detailRepository.selectPdNo(dto.getPdNo());
			dto.setDetailImgs(list);

			// 옵션
			List<String> options = optionRepository.selectByPdNo(dto.getPdNo());
			dto.setOption(options);

			return dto; // DTO 반환
		} else {
			return null;
		}
	}

	@Override
	//상품 수정 메소드
	public void modify(ProductDTO dto) {
		// 전달받은 DTO에서 상품 번호 꺼내고, 해당 상품 조회
		Optional<Product> result = repository.findById(dto.getPdNo());
		if (result.isPresent()) { // 해당 상품이 존재하는지 확인
			Product entity = result.get();
			
			//상품 이름
			entity.setPdName(dto.getPdName());
			//대분류
			entity.setDepth1(dto.getDepth1());
			//소분류
			entity.setDepth2(dto.getDepth2());
			//설명
			entity.setComment(dto.getComment());
			//가격
			entity.setPrice(dto.getPrice());
			//입고수량
			entity.setPdCount(dto.getPdCount());

			//썸네일
			MultipartFile thumnailFile = dto.getThumnailFile();
			if(thumnailFile!=null){
				String filename = fileUtil.fileUpload(thumnailFile);
				entity.setThumnail(filename);
			}

			// 상품 상세 이미지
			List<MultipartFile> detailImgFiles = dto.getDetailImgFiles();
			if(detailImgFiles!=null){
				// 기존 이미지 삭제
				detailRepository.deleteByPdNo(entity.getPdNo());
				// 새로운 이미지 저장
				for( MultipartFile file : detailImgFiles ){
					String filename = fileUtil.fileUpload(file);
					ProductDetail detail = ProductDetail.builder()
							.pd(entity)
							.detailImg(filename)
							.build();
					detailRepository.save(detail);
				}
			}

			// 옵션
			// 기존 데이터 삭제
			optionRepository.deleteByPdNo(dto.getPdNo());
			List<String> options = dto.getOption();
			if(options != null){
				// 새로 추가
				for( String option : options ){
					ProductOption productOption = ProductOption.builder()
							.pd(entity)
							.poOption(option)
							.build();
					optionRepository.save(productOption);
				}
			}

			// 다시 저장
			repository.save(entity);
		}
	}

	@Override
	//주문 삭제 메소드
	public void remove(int pdNo) {
		// 상품 상세 이미지 삭제
		detailRepository.deleteByPdNo(pdNo);
		// 옵션 삭제
		optionRepository.deleteByPdNo(pdNo);
		// 리뷰 삭제
		reviewRepository.deleteByPdNo(pdNo);
		// 상품 삭제
		repository.deleteById(pdNo);
	}

}
