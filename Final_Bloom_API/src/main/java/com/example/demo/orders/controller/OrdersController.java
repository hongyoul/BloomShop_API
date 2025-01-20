package com.example.demo.orders.controller;

import java.security.Principal;
import java.util.List;

import com.example.demo.orders.dto.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cart.dto.CartDTO;
import com.example.demo.cart.service.CartService;
import com.example.demo.orders.dto.OrdersDTO;
import com.example.demo.orders.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
    OrdersService service;
	
	@Autowired
	CartService cartService;
	
	// 주문하기 페이지로 이동
	@GetMapping("/checkout")
	public ResponseEntity<List<CartDTO>> checkout(Principal principal) {
	    // 로그인한 사용자 ID 가져오기
	    String memberId = principal.getName();
	    
	    // 장바구니 목록 조회
	    List<CartDTO> cartList = cartService.getList(memberId);
	    
	    // HTTP 상태코드 200과 함께 장바구니 목록 반환
	    return new ResponseEntity<>(cartList, HttpStatus.OK);
	}

	// 파라미터: OrdersDTO => PaymentRequest (결제번호와 주문정보)
	// 리턴값: 결제성공시 200, 실패시400
	// 주문에 결제 기능 추가
	//주문 등록
	@PostMapping("/register")
	public ResponseEntity<Integer> register(@RequestBody PaymentRequest request, Principal principal) {
		OrdersDTO dto = request.getOrders();
		dto.setId(principal.getName());
		int newNo = service.register(request);
		return new ResponseEntity<>(newNo, HttpStatus.CREATED); //201성공코드와 새로운 글번호를 반환한다
	}
	
	@GetMapping("/list")
	//관리자 전체 주문목록 조회
	public ResponseEntity<List<OrdersDTO>> getList(Authentication authentication) {
	    // 사용자의 권한 확인
	    boolean isAdmin = authentication.getAuthorities().stream()
	            .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

	    if (!isAdmin) {
	        return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403 Forbidden
	    }

	    List<OrdersDTO> ordersList = service.getList(null);

	    return new ResponseEntity<>(ordersList, HttpStatus.OK);
	}

	@GetMapping("/myList")
	//일반사용자 주문목록 조회
	public ResponseEntity<List<OrdersDTO>> getMyList(Principal principal) {
	    // 로그인한 사용자 ID 가져오기
	    String memberId = principal.getName();

	    // 사용자의 주문 목록 조회
	    List<OrdersDTO> ordersList = service.getListByMemberId(memberId);

	    return new ResponseEntity<>(ordersList, HttpStatus.OK);
	}
	
	//주문 상세
	 @GetMapping("/read")
    public ResponseEntity<OrdersDTO> read(@RequestParam(name = "no") int odNo, Principal principal) {
        // 로그인한 사용자 ID 가져오기
        String memberId = principal.getName();

        // 주문 상세 조회
        OrdersDTO dto = service.read(odNo);

        // 사용자가 자신의 주문인지 확인
        if (!dto.getId().equals(memberId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403 Forbidden
        }

        return new ResponseEntity<>(dto, HttpStatus.OK); // 200 OK
    }
	
	//주문 수정
	@PutMapping("/modify")
	public ResponseEntity<Void> modify(@RequestBody OrdersDTO dto, Principal principal) {
        // 로그인한 사용자 ID 가져오기
        String memberId = principal.getName();

        // 수정하려는 주문이 사용자의 것인지 확인
        OrdersDTO existingOrder = service.read(dto.getOdNo());
        if (!existingOrder.getId().equals(memberId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403 Forbidden
        }

        // 주문 수정
        service.modify(dto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
	
	//주문 삭제
	@DeleteMapping("/remove")
	public ResponseEntity<Void> remove(@RequestParam(name = "no") int odNo, Principal principal) {
        // 로그인한 사용자 ID 가져오기
        String memberId = principal.getName();

        // 삭제하려는 주문이 사용자의 것인지 확인
        OrdersDTO existingOrder = service.read(odNo);
        if (!existingOrder.getId().equals(memberId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403 Forbidden
        }

        // 주문 삭제
        service.remove(odNo);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }

}
