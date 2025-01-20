package com.example.demo.cart.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCart is a Querydsl query type for Cart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCart extends EntityPathBase<Cart> {

    private static final long serialVersionUID = 969118743L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCart cart = new QCart("cart");

    public final NumberPath<Integer> ctCount = createNumber("ctCount", Integer.class);

    public final NumberPath<Integer> ctNo = createNumber("ctNo", Integer.class);

    public final com.example.demo.member.entity.QMember member;

    public final com.example.demo.product.entity.QProduct pd;

    public final com.example.demo.product.entity.QProductOption po;

    public QCart(String variable) {
        this(Cart.class, forVariable(variable), INITS);
    }

    public QCart(Path<? extends Cart> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCart(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCart(PathMetadata metadata, PathInits inits) {
        this(Cart.class, metadata, inits);
    }

    public QCart(Class<? extends Cart> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.demo.member.entity.QMember(forProperty("member")) : null;
        this.pd = inits.isInitialized("pd") ? new com.example.demo.product.entity.QProduct(forProperty("pd")) : null;
        this.po = inits.isInitialized("po") ? new com.example.demo.product.entity.QProductOption(forProperty("po"), inits.get("po")) : null;
    }

}

