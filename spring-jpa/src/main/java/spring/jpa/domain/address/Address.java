package spring.jpa.domain.address;

import jakarta.persistence.*;
import lombok.*;
import spring.jpa.domain.BaseTimeEntity;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Address extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;
    private String address1;
    private String address2;
    private int zipCode;
}
