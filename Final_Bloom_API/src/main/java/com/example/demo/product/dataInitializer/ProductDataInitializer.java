package com.example.demo.product.dataInitializer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.product.entity.Product;
import com.example.demo.product.repository.ProductRepository;

@Component
@Order(2) // 실행 순서를 지정 (1번 뒤에 실행)
public class ProductDataInitializer implements ApplicationRunner {


    private final ProductRepository productRepository;

    public ProductDataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	
    	if (productRepository.count() > 0) {
            System.out.println("Product data already exists. Skipping creation.");
            return;
        }
    	
        List<String> depth1List = Arrays.asList("스킨케어", "메이크업", "마스크팩", "바디", "클렌징");
        List<String> depth2Skincare = Arrays.asList("에센스", "앰플", "세럼", "크림", "로션");
        List<String> depth2Makeup = Arrays.asList("립", "베이스", "아이");
        List<String> depth2Mask = Arrays.asList("시트팩", "패드", "코팩", "패치");
        List<String> depth2Body = Arrays.asList("로션", "오일", "샤워", "립케어", "핸드케어", "바디미스트");
        List<String> depth2Cleansing = Arrays.asList("폼", "젤", "오일", "스크럽", "티슈");

        Random random = new Random();

        for (int i = 1; i <= 30; i++) {
            String depth1 = depth1List.get(random.nextInt(depth1List.size()));
            String depth2 = switch (depth1) {
                case "스킨케어" -> depth2Skincare.get(random.nextInt(depth2Skincare.size()));
                case "메이크업" -> depth2Makeup.get(random.nextInt(depth2Makeup.size()));
                case "마스크팩" -> depth2Mask.get(random.nextInt(depth2Mask.size()));
                case "바디" -> depth2Body.get(random.nextInt(depth2Body.size()));
                case "클렌징" -> depth2Cleansing.get(random.nextInt(depth2Cleansing.size()));
                default -> "";
            };

            String pdName = String.format("%s %s 상품 %d", depth1, depth2, i);
            String comment = String.format("%s %s의 특별한 효과를 경험해보세요!", depth1, depth2);
            int price = (random.nextInt(10) + 1) * 10000; // 랜덤 가격 (1~10만 원)

            productRepository.save(Product.builder()
                    .pdName(pdName)
                    .depth1(depth1)
                    .depth2(depth2)
//                    .option("기본 옵션")
                    .comment(comment)
                    .price(price)
                    .thumnail("") // 빈값
                    .pdCount(10) // 입고수량 10개
                    .build());
        }
    }
}