package com.example.demo.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tab_member")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member { // BaseEntity 상속받기

	@Id
	@Column(length = 50)
	String id; // 아이디

	@Column(length = 200, nullable = false)
	String password; // 패스워드

	@Column(length = 20, nullable = false)
	String name; // 이름
	
	@Column(length = 30, nullable = false)
	String phone; // 연락처
	
	@Column(length = 255)
	String address; // 주소
	
	@Column(length = 50)
	String email; // 이메일
	
	@Column(length = 20, nullable = false)
	String role; // 등급

}
