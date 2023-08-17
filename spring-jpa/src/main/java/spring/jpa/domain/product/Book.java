package spring.jpa.domain.product;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")
@PrimaryKeyJoinColumn(name = "product__book_id")
@Table(name = "product__book")
public class Book extends Product {
    private String author;
    private String isbn;
}
