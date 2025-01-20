package com.example.demo.member.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;

//@Service
public class MemberServiceImpl implements MemberService { //서비스 인터페이스 상속받기

	@Autowired
	MemberRepository repository; //리파지토리 필드 선언
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	//회원 조회
	@Override
	public List<MemberDTO> getList() {
		
		List<Member> entityList = repository.findAll();		
		
		List<MemberDTO> dtoList = entityList.stream()
				.map(entity -> entityToDto(entity))
				.collect(Collectors.toList());

		return dtoList;
	}
	
	//회원 등록
	@Override
	public boolean register(MemberDTO dto) {
		String id = dto.getId();
		MemberDTO getDto = read(id);
		if (getDto != null) {
			System.out.println("사용중인 아이디입니다.");
			return false;
		}
		Member entity = dtoToEntity(dto);

		// 패스워드 인코더로 패스워드 암호화하기
		String enPw = passwordEncoder.encode(entity.getPassword());
		entity.setPassword(enPw);

		repository.save(entity);
		return true;
	}

	//회원 상세 조회
	@Override
	public MemberDTO read(String id) {
		Optional<Member> result = repository.findById(id);
		if (result.isPresent()) {
			Member member = result.get();
			return entityToDto(member);
		} else {
			return null;
		}
	}

	//회원 수정
	@Override
	public void modify(MemberDTO dto) {
		Optional<Member> result = repository.findById(dto.getId());
		
		if(result.isPresent()) {
			Member entity = result.get();
			//비밀번호
			String enPw = passwordEncoder.encode(dto.getPassword());
			entity.setPassword(enPw);
			//주소
			entity.setAddress(dto.getAddress());
			//이메일
			entity.setEmail(dto.getEmail());
			//연락처
			entity.setPhone(dto.getPhone());
			repository.save(entity);
		}
	}

	//회원 삭제
	@Override
	public void remove(String id) {
		repository.deleteById(id);
	}

}
