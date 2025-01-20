package com.example.demo.review.entity;

import com.example.demo.common.BaseEntity;
import com.example.demo.member.entity.Member;
import com.example.demo.product.entity.Product;

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
@Table(name = "tab_review")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int reNo; //리뷰번호
	
	@ManyToOne
	Member member; //회원
	
	@ManyToOne
	Product product; //상품
	
	@Column(length = 255, nullable = false)
	String content; //리뷰내용
}
