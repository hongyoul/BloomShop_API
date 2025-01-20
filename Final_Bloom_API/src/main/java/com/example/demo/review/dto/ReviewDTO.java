package com.example.demo.review.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {
	int reNo; //리뷰번호
	
	String id; //아이디
	
	int pdNo; //상품번호
	
	String content; //리뷰내용
	
	LocalDateTime regDate; //등록일
	
	LocalDateTime modDate; //수정일
}
