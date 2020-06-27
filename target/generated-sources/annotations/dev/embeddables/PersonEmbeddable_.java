package dev.embeddables;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2020-06-27T11:50:37", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(PersonEmbeddable.class)
public class PersonEmbeddable_ { 

    public static volatile SingularAttribute<PersonEmbeddable, String> firstname;
    public static volatile SingularAttribute<PersonEmbeddable, String> password;
    public static volatile SingularAttribute<PersonEmbeddable, String> phoneNumber;
    public static volatile SingularAttribute<PersonEmbeddable, String> salt;
    public static volatile SingularAttribute<PersonEmbeddable, String> email;
    public static volatile SingularAttribute<PersonEmbeddable, String> lastname;

}