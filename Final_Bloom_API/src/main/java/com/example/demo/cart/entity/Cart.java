package com.example.demo.cart.entity;

import com.example.demo.member.entity.Member;
import com.example.demo.product.entity.Product;
import com.example.demo.product.entity.ProductOption;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tab_cart")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int ctNo; //장바구니번호
	
	@ManyToOne
	Product pd; //상품번호
	
	@ManyToOne
	Member member; //아이디
	
	@Column(nullable = false)
	int ctCount; //주문수량
	
	@ManyToOne
	ProductOption po; //옵션번호

}
