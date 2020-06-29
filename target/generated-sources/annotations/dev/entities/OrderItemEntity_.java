package dev.entities;

import dev.entities.ItemEntity;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2020-06-28T16:13:09", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(OrderItemEntity.class)
public class OrderItemEntity_ { 

    public static volatile SingularAttribute<OrderItemEntity, ItemEntity> item;
    public static volatile SingularAttribute<OrderItemEntity, Integer> quantity;
    public static volatile SingularAttribute<OrderItemEntity, Long> orderItemId;
    public static volatile SingularAttribute<OrderItemEntity, String> comment;

}