package com.example.demo.product.entity;

import jakarta.persistence.*;
import lombok.*;

// 상품 옵션

@Entity
@Table(name = "tab_product_option")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int poNo; //번호

    @ManyToOne
	Product pd; //상품

	@Column(length = 100)
	String poOption; //옵션

}
