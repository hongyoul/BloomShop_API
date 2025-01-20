package com.example.demo.orders.dto;

// 포트원 결제 API를 호출 하기 위해 요청데이터를 저장하기 위한 클래스
// 화면에서 전달받은 결제 정보 저장

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {

    String paymentId; // 결제번호

    OrdersDTO orders; // 주문정보 

}
