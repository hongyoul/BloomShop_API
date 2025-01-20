package com.example.demo.product;

import com.example.demo.product.entity.Product;
import com.example.demo.product.entity.QProduct;
import com.example.demo.product.repository.ProductRepository;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;

    // 예: 스킨케어
    @Test
    public void 대분류로검색(){

        QProduct product = QProduct.product;

        // 대분류
        Predicate predicate = product.depth1.contains("스킨케어");

        Iterable<Product> products = repository.findAll(predicate);
        
        // Iterable => List 변환
        List<Product> productList = new ArrayList<>();

        for(Product p : products){
            productList.add(p);
        }

        for(Product p : productList){
            System.out.println(p);
        }
    }

    // 예: 앰플, 로션
    @Test
    public void 소분류여러개로_검색(){

        QProduct product = QProduct.product;

        // 소분류
        List<String> kewords = Arrays.asList("앰플", "로션");
        BooleanExpression depth2Condition = product.depth2.in(kewords);

        Iterable<Product> products = repository.findAll(depth2Condition);

        // Iterable => List 변환
        List<Product> productList = new ArrayList<>();

        for(Product p : products){
            productList.add(p);
        }

        for(Product p : productList){
            System.out.println(p);
        }
    }

    // 예시: 스킨케어 + 10000~50000
    @Test
    public void 대분류와_가격으로_검색(){
        QProduct product = QProduct.product;

        // 대분류
        String depth1Keword = "스킨케어";
        BooleanExpression depth1Condition = product.depth1.contains(depth1Keword);

        // 가격
        int priceKewordStart = 10000;
        int priceKewordEnd = 50000;
        BooleanExpression priceCondition = product.price.between(priceKewordStart, priceKewordEnd);

        BooleanExpression condition = depth1Condition.and(priceCondition);

        Iterable<Product> products = repository.findAll(condition);

        // Iterable => List 변환
        List<Product> productList = new ArrayList<>();

        for(Product p : products){
            productList.add(p);
        }

        for(Product p : productList){
            System.out.println(p);
        }
    }

    // 예시: 앰플, 로션 + 10000~50000
    @Test
    public void 소분류와_가격으로_검색(){
        QProduct product = QProduct.product;

        // 소분류
        List<String> kewords = Arrays.asList("앰플", "로션");
        BooleanExpression depth2Condition = product.depth2.in(kewords);

        // 가격
        int priceKewordStart = 70000;
        int priceKewordEnd = 100000;
        BooleanExpression priceCondition = product.price.between(priceKewordStart, priceKewordEnd);

        BooleanExpression condition = depth2Condition.and(priceCondition);

        Iterable<Product> products = repository.findAll(condition);

        // Iterable => List 변환
        List<Product> productList = new ArrayList<>();

        for(Product p : products){
            productList.add(p);
        }

        for(Product p : productList){
            System.out.println(p);
        }
    }

}
