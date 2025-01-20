package com.example.demo.member.service;

import java.util.List;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Member;

public interface MemberService {
	//회원 목록조회
	List<MemberDTO> getList(); 
	
	//회원 등록
	boolean register(MemberDTO dto);
	
	//회원 단건 조회
	MemberDTO read(String id);
	
	//회원 수정 메소드
	void modify(MemberDTO dto);
	
	//회원 삭제 메소드
	void remove(String id);
	
	//엔티티를 DTO로 변환하는 메소드
	default MemberDTO entityToDto(Member entity) {
		MemberDTO dto = MemberDTO.builder()
						.id(entity.getId())
						.password(entity.getPassword())
						.name(entity.getName())
						.phone(entity.getPhone())
						.address(entity.getAddress())
						.email(entity.getEmail())
						.role(entity.getRole())
						.build();

		return dto;
	}

	//DTO를 엔티티로 변환하는 메소드
	default Member dtoToEntity(MemberDTO dto) {
		Member entity = Member.builder()
						.id(dto.getId())
						.password(dto.getPassword())
						.name(dto.getName())
						.phone(dto.getPhone())
						.address(dto.getAddress())
						.email(dto.getEmail())
						.role(dto.getRole())
						.build();
		return entity;
	}

}
