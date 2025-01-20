package com.example.demo.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.cart.dto.CartDTO;
import com.example.demo.cart.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService service;
	
	//장바구니 등록
	@PostMapping("/register")
  	public ResponseEntity<Integer> register(@RequestBody CartDTO dto, Authentication authentication) {
		// 로그인한 사용자의 ID 가져오기
        String memberId = authentication.getName();

        // DTO에 로그인한 사용자 ID를 설정
        dto.setId(memberId);
        
        // 장바구니 등록
		int newNo = service.register(dto);
  		
		return new ResponseEntity<>(newNo, HttpStatus.CREATED); //201성공코드와 새로운 글번호를 반환한다
  	}
	
	//장바구니 목록
	@GetMapping("/list")
  	public ResponseEntity<List<CartDTO>> getList(Authentication authentication) {
		   // 로그인한 사용자의 ID 가져오기
        String memberId = authentication.getName(); // 인증 객체에서 사용자 ID 추출

        // 서비스 호출하여 해당 사용자의 장바구니 목록 조회
        List<CartDTO> list = service.getList(memberId);

        // HTTP 상태 코드 200과 함께 목록 반환
        return new ResponseEntity<>(list, HttpStatus.OK);
  	}
	
	// 장바구니 상세 조회 (로그인한 사용자만 자신의 장바구니 조회 가능)
    @GetMapping("/read")
    public ResponseEntity<CartDTO> read(@RequestParam(name = "no") int ctNo, Authentication authentication) {
        // 로그인한 사용자의 ID 가져오기
        String memberId = authentication.getName();

        // 장바구니 항목 조회
        CartDTO dto = service.read(ctNo);

        // 소유자 검증
        if (dto == null || !dto.getId().equals(memberId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403 Forbidden
        }

        // 성공 응답
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // 장바구니 수정 (로그인한 사용자만 자신의 장바구니 수정 가능)
    @PutMapping("/modify")
    public ResponseEntity<Void> modify(@RequestBody CartDTO dto, Authentication authentication) {
        // 로그인한 사용자의 ID 가져오기
        String memberId = authentication.getName();

        // 기존 장바구니 항목 조회
        CartDTO existingDto = service.read(dto.getCtNo());

        // 소유자 검증
        if (existingDto == null || !existingDto.getId().equals(memberId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403 Forbidden
        }
  
        // 수정 수행
        service.modify(dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }

    // 장바구니 삭제 (로그인한 사용자만 자신의 장바구니 삭제 가능)
    @DeleteMapping("/remove")
    public ResponseEntity<Void> remove(@RequestParam(name = "no") int ctNo, Authentication authentication) {
        // 로그인한 사용자의 ID 가져오기
        String memberId = authentication.getName();

        // 기존 장바구니 항목 조회
        CartDTO existingDto = service.read(ctNo);

        // 소유자 검증
        if (existingDto == null || !existingDto.getId().equals(memberId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403 Forbidden
        }

        // 삭제 수행
        service.remove(ctNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
}
