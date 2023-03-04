package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.boot.jaxb.mapping.internal.InheritanceTypeMarshalling;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
@Getter @Setter
public class Album extends Item {
    private String artist;
    private String etc;
}