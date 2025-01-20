package com.example.demo.product.repository;

import com.example.demo.product.entity.ProductDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {

    @Query(value = "SELECT detail_img FROM product_detail WHERE pd_pd_no = :pdNo", nativeQuery = true)
    List<String> selectPdNo(@Param("pdNo") int pdNo);

    @Query(value = "delete FROM product_detail WHERE pd_pd_no = :pdNo", nativeQuery = true)
    @Modifying
    void deleteByPdNo(@Param("pdNo") int pdNo);

}