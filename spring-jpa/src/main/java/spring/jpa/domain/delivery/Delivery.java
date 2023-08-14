package spring.jpa.domain.delivery;

import jakarta.persistence.*;
import lombok.*;
import spring.jpa.domain.BaseTimeEntity;
import spring.jpa.domain.address.Address;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Delivery extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
