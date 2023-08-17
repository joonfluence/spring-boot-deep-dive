package spring.jpa.domain.product;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("A")
@PrimaryKeyJoinColumn(name = "product__album_id")
@Table(name = "product__record")
public class Album extends Product {
    private String artist;
    private String etc;
}
