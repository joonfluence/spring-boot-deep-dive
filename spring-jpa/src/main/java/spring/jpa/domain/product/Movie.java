package spring.jpa.domain.product;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("M")
@PrimaryKeyJoinColumn(name = "product__movie_id")
@Table(name = "product__movie")
public class Movie extends Product {
    private String director;
    private String actor;
}
