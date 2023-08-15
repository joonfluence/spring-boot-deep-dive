package spring.jpa.domain.delivery;

import jakarta.persistence.*;
import lombok.*;
import spring.jpa.domain.BaseTimeEntity;
import spring.jpa.domain.address.Address;
import spring.jpa.domain.order.Order;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Entity
public class Delivery extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @OneToOne(mappedBy = "delivery", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Order order;
}
