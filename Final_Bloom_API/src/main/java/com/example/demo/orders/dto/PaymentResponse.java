package com.example.demo.orders.dto;

// 포트원 결제 API 호출 후 응답데이터를 저장하기 위한 클래스

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    String status;
    String id;
    String transactionId;
    String merchantId;
    String storeId;

    Method method;
    Channel channel;

    String version;
    LocalDateTime requestedAt;
    LocalDateTime updatedAt;
    LocalDateTime statusChangedAt;

    String orderName;

    Amount amount;
    String currency;

    Customer customer;

    String promotionId;
    boolean isCulturalExpense;
    String customData;
    LocalDateTime paidAt;

    String pgTxId;
    String pgResponse;
    String receiptUrl;

    @Data
    public static class Method {
        String type;
        String provider;

        @JsonProperty("easyPayMethod")
        EasyPayMethod easyPayMethod;

        @Data
        public static class EasyPayMethod {
            String type;
        }
    }

    @Data
    public static class Channel {
        String type;
        String id;
        String key;
        String name;
        String pgProvider;
        String pgMerchantId;
    }

    @Data
    public static class Amount {
        int total;
        int taxFree;
        int vat;
        int supply;
        int discount;
        int paid;
        int cancelled;
        int cancelledTaxFree;
    }

    @Data
    public static class Customer {
        String id;
        String name;
        String email;
        String phoneNumber;
    }
}
