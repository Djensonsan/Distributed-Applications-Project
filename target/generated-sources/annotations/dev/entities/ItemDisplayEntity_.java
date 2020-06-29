package dev.entities;

import dev.entities.ItemEntity;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2020-06-28T16:13:09", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(ItemDisplayEntity.class)
public class ItemDisplayEntity_ { 

    public static volatile SingularAttribute<ItemDisplayEntity, byte[]> image;
    public static volatile SingularAttribute<ItemDisplayEntity, String> imageName;
    public static volatile SingularAttribute<ItemDisplayEntity, String> contentType;
    public static volatile ListAttribute<ItemDisplayEntity, ItemEntity> items;

}