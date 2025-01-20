package com.example.demo.review.dataInitializer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.review.dto.ReviewDTO;
import com.example.demo.review.service.ReviewService;

@Component
@Order(3)
public class ReviewDataInitializer implements ApplicationRunner {

	@Autowired
    ReviewService reviewService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		 if (reviewService.getProductList(1).size() > 0) {
	            System.out.println("Dummy review data already exists. Skipping creation.");
	            return;
	        }

	        List<ReviewDTO> dummyReviews = new ArrayList<>();
	        
	        // User1 리뷰 데이터 생성
	        for (int productNo = 1; productNo <= 30; productNo++) {
	            for (int reviewNo = 1; reviewNo <= 5; reviewNo++) {
	                String content = "";
	                switch (reviewNo) {
	                    case 1: content = "배송이 빠르고 만족스러웠습니다."; break;
	                    case 2: content = "포장이 꼼꼼하게 잘 되어 있었습니다."; break;
	                    case 3: content = "디자인이 마음에 들어요."; break;
	                    case 4: content = "친구에게 추천하고 싶어요."; break;
	                    case 5: content = "재구매 의사 100% 있습니다."; break;
	                }
	                ReviewDTO review = ReviewDTO.builder()
	                        .id("user1")
	                        .pdNo(productNo)
	                        .content(content)
	                        .build();
	                dummyReviews.add(review);
	            }
	        }
	        
	        // User2 리뷰 데이터 생성
	        for (int productNo = 1; productNo <= 30; productNo++) {
	            for (int reviewNo = 1; reviewNo <= 5; reviewNo++) {
	                String content = "";
	                switch (reviewNo) {
	                    case 1: content = "제품이 가격대비 성능이 뛰어나서 재구매 했어요~ 너무 좋아요:D"; break;
	                    case 2: content = "친구 생일 선물로 샀어요."; break;
	                    case 3: content = "색상이 아주 예뻐요."; break;
	                    case 4: content = "주변 사람들이 다 예쁘다고 해요."; break;
	                    case 5: content = "고민하지 말고 사세요. 강추합니다!"; break;
	                }
	                ReviewDTO review = ReviewDTO.builder()
	                        .id("user2")
	                        .pdNo(productNo)
	                        .content(content)
	                        .build();
	                dummyReviews.add(review);
	            }
	        }
	        
	        // 데이터 저장
	        for (ReviewDTO review : dummyReviews) {
	            reviewService.register(review);
	        }
	}

}
