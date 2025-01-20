package com.example.demo.orders.entity;

import java.util.List;

import com.example.demo.common.BaseEntity;
import com.example.demo.member.entity.Member;
import com.example.demo.ordersDetail.entity.OrdersDetail;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tab_orders") // 주문할 때 입력받는 배송정보 테이블
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int odNo; //주문번호
	
	@ManyToOne
	Member member; //아이디
	
	@Column(nullable = false)
	int totalPrice; //총금액

	@Column(length = 255, nullable = false)
	String odAddress; //배송지

	@Column(length = 20, nullable = false)
	String odName; //수취인

	@Column(nullable = false)
	String odPhone; //연락처

	@Column(nullable = false)
	String payMethod; //결제방법
	
    // **OrdersDetail과의 연관 관계 매핑**
    @OneToMany(mappedBy = "od", cascade = CascadeType.REMOVE) 
    private List<OrdersDetail> orderDetails; // 주문 상세 목록
	
}
