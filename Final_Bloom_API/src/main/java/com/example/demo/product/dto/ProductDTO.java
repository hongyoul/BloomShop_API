package com.example.demo.product.dto;

import com.example.demo.product.entity.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
	
	int pdNo; //상품번호
	
	String pdName; //상품이름
	
	String depth1; //대분류
	
	String depth2; //소분류

	String comment; //상품설명
	
	int price; //상품가격

	MultipartFile thumnailFile; //대표이미지 파일 스트림

	String thumnail; //대표이미지

	List<MultipartFile> detailImgFiles; // 상세이미지 파일 스트림

	List<String> detailImgs; //상세이미지
	
	int pdCount; //입고수량

	List<String> option; //상품옵션
	
}
