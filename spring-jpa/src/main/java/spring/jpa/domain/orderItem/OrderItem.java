package spring.jpa.domain.orderItem;

import jakarta.persistence.*;
import lombok.*;
import spring.jpa.domain.order.Order;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderItem_id", nullable = false)
    private Long id;
    private int orderPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
