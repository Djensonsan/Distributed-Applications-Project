package dev.entities;

import dev.embeddables.AddressEmbeddable;
import dev.embeddables.PersonEmbeddable;
import dev.entities.OrderEntity;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2020-06-23T11:26:19", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(CustomerEntity.class)
public class CustomerEntity_ { 

    public static volatile SingularAttribute<CustomerEntity, AddressEmbeddable> address;
    public static volatile SingularAttribute<CustomerEntity, PersonEmbeddable> person;
    public static volatile SingularAttribute<CustomerEntity, Long> customerId;
    public static volatile ListAttribute<CustomerEntity, OrderEntity> orders;

}