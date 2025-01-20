package com.example.demo.orders.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrders is a Querydsl query type for Orders
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrders extends EntityPathBase<Orders> {

    private static final long serialVersionUID = -985190751L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrders orders = new QOrders("orders");

    public final com.example.demo.common.QBaseEntity _super = new com.example.demo.common.QBaseEntity(this);

    public final com.example.demo.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath odAddress = createString("odAddress");

    public final StringPath odName = createString("odName");

    public final NumberPath<Integer> odNo = createNumber("odNo", Integer.class);

    public final StringPath odPhone = createString("odPhone");

    public final ListPath<com.example.demo.ordersDetail.entity.OrdersDetail, com.example.demo.ordersDetail.entity.QOrdersDetail> orderDetails = this.<com.example.demo.ordersDetail.entity.OrdersDetail, com.example.demo.ordersDetail.entity.QOrdersDetail>createList("orderDetails", com.example.demo.ordersDetail.entity.OrdersDetail.class, com.example.demo.ordersDetail.entity.QOrdersDetail.class, PathInits.DIRECT2);

    public final StringPath payMethod = createString("payMethod");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public QOrders(String variable) {
        this(Orders.class, forVariable(variable), INITS);
    }

    public QOrders(Path<? extends Orders> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrders(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrders(PathMetadata metadata, PathInits inits) {
        this(Orders.class, metadata, inits);
    }

    public QOrders(Class<? extends Orders> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.demo.member.entity.QMember(forProperty("member")) : null;
    }

}

