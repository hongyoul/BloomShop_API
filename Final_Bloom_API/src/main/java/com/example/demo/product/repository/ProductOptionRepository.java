package com.example.demo.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.product.entity.ProductOption;

import jakarta.transaction.Transactional;

@Transactional
public interface ProductOptionRepository extends JpaRepository<ProductOption, Integer> {

    @Query(value = "SELECT po_option FROM product_option WHERE pd_pd_no = :pdNo", nativeQuery = true)
    List<String> selectByPdNo(@Param("pdNo") int pdNo);
    
    @Query("SELECT po FROM ProductOption po WHERE po.poOption = :poOption AND po.pd.pdNo = :pdNo")
    Optional<ProductOption> findByPoOptionAndPd_PdNo(@Param("poOption") String poOption, @Param("pdNo") int pdNo);

    // 상품번호를 기준으로 옵션 리스트 삭제
    @Query(value = "delete FROM product_option WHERE pd_pd_no = :pdNo", nativeQuery = true)
    @Modifying
    void deleteByPdNo(@Param("pdNo") int pdNo);

}