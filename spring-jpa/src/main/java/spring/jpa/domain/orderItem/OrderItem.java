package spring.jpa.domain.orderItem;

import jakarta.persistence.*;
import lombok.*;
import spring.jpa.domain.order.Order;
import spring.jpa.domain.product.Product;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderItem_id", nullable = false)
    private Long id;
    private int orderPrice;
    private int count;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
