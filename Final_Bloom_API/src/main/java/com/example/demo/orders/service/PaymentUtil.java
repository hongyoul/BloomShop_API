package com.example.demo.orders.service;

import com.example.demo.orders.dto.PaymentRequest;
import com.example.demo.orders.dto.PaymentResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

// 상품 결제를 위한 유틸 클래스
// 포트원의 결제 조회 API를 호출하여, 해당 결제 건의 상태를 확인하고 결제 완료 처리
// 구매 상품 리스트, 사용자 정보, 배송지, 결제 금액 세부사항 입력

@Service
public class PaymentUtil {

    // 키값은 temp.txt파일을 확인해주세요
    String portOneApiSecret;

    public ResponseEntity<Object> payComplete(PaymentRequest paymentRequest) {

        RestTemplate restTemplate = new RestTemplate();

        HashMap<String,String> map = new HashMap<>();

        try {
            String paymentId = paymentRequest.getPaymentId();

            // 1. 포트원 결제내역 단건조회 API 호출
            String url = "https://api.portone.io/payments/" + URLEncoder.encode(paymentId, StandardCharsets.UTF_8);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "PortOne " + portOneApiSecret);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<PaymentResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    PaymentResponse.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("결제 API 호출 실패: " + response.getBody());
            }

            PaymentResponse payment = response.getBody();

            // 결제가 정상적으로 처리되었다면
            if(payment.getStatus().equals("PAID")){
                // 결제 완료 처리 로직
                map.put("status", payment.getStatus());
                map.put("text", "결제 검증 및 처리 완료");
                return new ResponseEntity(map, HttpStatus.OK);
            } else {
                // 알 수 없는 결제 상태
                map.put("status", payment.getStatus());
                map.put("text", "결제 검증 실패");
                return ResponseEntity.badRequest().body(map);
            }
        } catch (Exception e) {
            map.put("status", "");
            map.put("text", "결제 검증 실패: " + e.getMessage());
            return ResponseEntity.badRequest().body(map);
        }
    }

}
