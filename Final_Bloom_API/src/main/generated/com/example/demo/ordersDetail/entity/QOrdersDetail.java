package com.example.demo.ordersDetail.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrdersDetail is a Querydsl query type for OrdersDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrdersDetail extends EntityPathBase<OrdersDetail> {

    private static final long serialVersionUID = 857249027L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrdersDetail ordersDetail = new QOrdersDetail("ordersDetail");

    public final NumberPath<Integer> dtNo = createNumber("dtNo", Integer.class);

    public final com.example.demo.orders.entity.QOrders od;

    public final NumberPath<Integer> odCount = createNumber("odCount", Integer.class);

    public final StringPath odOption = createString("odOption");

    public final com.example.demo.product.entity.QProduct pd;

    public QOrdersDetail(String variable) {
        this(OrdersDetail.class, forVariable(variable), INITS);
    }

    public QOrdersDetail(Path<? extends OrdersDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrdersDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrdersDetail(PathMetadata metadata, PathInits inits) {
        this(OrdersDetail.class, metadata, inits);
    }

    public QOrdersDetail(Class<? extends OrdersDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.od = inits.isInitialized("od") ? new com.example.demo.orders.entity.QOrders(forProperty("od"), inits.get("od")) : null;
        this.pd = inits.isInitialized("pd") ? new com.example.demo.product.entity.QProduct(forProperty("pd")) : null;
    }

}

