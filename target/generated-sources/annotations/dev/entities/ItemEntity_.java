package dev.entities;

import dev.entities.ItemDisplayEntity;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2020-06-27T11:50:37", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(ItemEntity.class)
public class ItemEntity_ { 

    public static volatile SingularAttribute<ItemEntity, Long> itemId;
    public static volatile SingularAttribute<ItemEntity, Float> price;
    public static volatile ListAttribute<ItemEntity, ItemDisplayEntity> itemDisplays;
    public static volatile SingularAttribute<ItemEntity, String> name;
    public static volatile SingularAttribute<ItemEntity, String> description;
    public static volatile SingularAttribute<ItemEntity, Integer> stockQuantity;
    public static volatile SingularAttribute<ItemEntity, String> quantityUnit;

}