package com.example.demo.review.repository;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.review.entity.Review;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Transactional
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	 // 사용자별 리뷰 목록 조회
    List<Review> findByMember_Id(String memberId);

    // 상품별 리뷰 목록 조회
    List<Review> findByProduct_PdNo(int pdNo);

    // 상품별 리뷰 리스트 삭제
    @Query(value = "delete FROM review WHERE product_pd_no = :pdNo", nativeQuery = true)
    @Modifying
    void deleteByPdNo(@Param("pdNo") int pdNo);
}
