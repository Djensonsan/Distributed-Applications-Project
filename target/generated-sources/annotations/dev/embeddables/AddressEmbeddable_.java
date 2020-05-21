package dev.embeddables;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2020-05-21T10:45:19", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(AddressEmbeddable.class)
public class AddressEmbeddable_ { 

    public static volatile SingularAttribute<AddressEmbeddable, String> country;
    public static volatile SingularAttribute<AddressEmbeddable, String> address2;
    public static volatile SingularAttribute<AddressEmbeddable, String> city;
    public static volatile SingularAttribute<AddressEmbeddable, String> address1;
    public static volatile SingularAttribute<AddressEmbeddable, Integer> postalCode;
    public static volatile SingularAttribute<AddressEmbeddable, String> state;

}