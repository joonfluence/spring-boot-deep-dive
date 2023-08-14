package spring.jpa.domain.product;


import jakarta.persistence.*;
import lombok.*;
import spring.jpa.domain.BaseTimeEntity;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String name;
    private int price;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private ProductType type;
}
