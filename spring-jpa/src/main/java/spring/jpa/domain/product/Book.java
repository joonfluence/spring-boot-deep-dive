package spring.jpa.domain.product;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("B")
@PrimaryKeyJoinColumn(name = "product__book_id")
@Table(name = "product__book")
public class Book extends Product {
    private String author;
    private Integer isbn;
}
