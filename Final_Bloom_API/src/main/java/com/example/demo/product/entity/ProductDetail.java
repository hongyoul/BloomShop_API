package com.example.demo.product.entity;

import jakarta.persistence.*;
import lombok.*;

// 상품 상세 이미지 리스트

@Entity
@Table(name = "tab_product_detail")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int pddNo; //번호

    @ManyToOne
	Product pd; //상품

	@Column(length = 200)
	String detailImg; //상세이미지

}
