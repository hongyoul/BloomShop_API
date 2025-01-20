package com.example.demo.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tab_product")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int pdNo; //상품번호
	
	@Column(length = 20, nullable = false)
	String pdName; //상품이름
	
	@Column(length = 20, nullable = false)
	String depth1; //대분류
	
	@Column(length = 20, nullable = false)
	String depth2; //소분류

	@Column(length = 100)
	String comment; //상품설명
	
	@Column(nullable = false)
	int price; //상품가격
	
	@Column(length = 200, nullable = false)
	String thumnail; //대표이미지
//
//	@Column(length = 50)
//	String detailImg; //상세이미지
	
	@Column
	int pdCount; //입고수량

}
