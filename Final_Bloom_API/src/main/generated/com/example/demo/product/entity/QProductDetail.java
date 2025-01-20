package com.example.demo.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductDetail is a Querydsl query type for ProductDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductDetail extends EntityPathBase<ProductDetail> {

    private static final long serialVersionUID = -889920690L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductDetail productDetail = new QProductDetail("productDetail");

    public final StringPath detailImg = createString("detailImg");

    public final QProduct pd;

    public final NumberPath<Integer> pddNo = createNumber("pddNo", Integer.class);

    public QProductDetail(String variable) {
        this(ProductDetail.class, forVariable(variable), INITS);
    }

    public QProductDetail(Path<? extends ProductDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductDetail(PathMetadata metadata, PathInits inits) {
        this(ProductDetail.class, metadata, inits);
    }

    public QProductDetail(Class<? extends ProductDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pd = inits.isInitialized("pd") ? new QProduct(forProperty("pd")) : null;
    }

}

