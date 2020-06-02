package dev.entities;

import dev.embeddables.AddressEmbeddable;
import dev.entities.OrderItemEntity;
import dev.enums.StatusEnum;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2020-05-30T15:13:25", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(OrderEntity.class)
public class OrderEntity_ { 

    public static volatile SingularAttribute<OrderEntity, Date> deliveredDate;
    public static volatile SingularAttribute<OrderEntity, AddressEmbeddable> address;
    public static volatile SingularAttribute<OrderEntity, Long> orderId;
    public static volatile SingularAttribute<OrderEntity, Date> requiredDateStart;
    public static volatile SingularAttribute<OrderEntity, String> comment;
    public static volatile ListAttribute<OrderEntity, OrderItemEntity> orderedItems;
    public static volatile SingularAttribute<OrderEntity, Date> requiredDateEnd;
    public static volatile SingularAttribute<OrderEntity, Date> orderDate;
    public static volatile SingularAttribute<OrderEntity, StatusEnum> status;

}