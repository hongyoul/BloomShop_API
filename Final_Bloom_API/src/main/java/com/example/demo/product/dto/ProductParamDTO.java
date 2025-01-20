package com.example.demo.product.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductParamDTO {
	
	String depth1; //대분류
	
	List<String> depth2; //소분류

	int priceStart; //시작 가격

	int priceEnd; //마지막 가격
}
