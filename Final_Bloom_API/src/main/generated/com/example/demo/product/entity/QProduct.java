package com.example.demo.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -1623619427L;

    public static final QProduct product = new QProduct("product");

    public final StringPath comment = createString("comment");

    public final StringPath depth1 = createString("depth1");

    public final StringPath depth2 = createString("depth2");

    public final NumberPath<Integer> pdCount = createNumber("pdCount", Integer.class);

    public final StringPath pdName = createString("pdName");

    public final NumberPath<Integer> pdNo = createNumber("pdNo", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath thumnail = createString("thumnail");

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

