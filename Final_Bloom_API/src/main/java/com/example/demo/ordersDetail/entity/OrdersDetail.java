package com.example.demo.ordersDetail.entity;

import com.example.demo.orders.entity.Orders;
import com.example.demo.product.entity.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tab_orders_detail") //장바구니에서 가져온 주문할 상품에 대한 데이터
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int dtNo; //주문상세 번호
	
	@ManyToOne
	@JoinColumn(name = "od_no")
	Product pd; //상품번호
	
	@ManyToOne
	Orders od; //주문번호
	
	@Column(nullable = false)
	int odCount; //주문수량
	
	@Column(length = 100, nullable= true)
	String odOption; //옵션
}
