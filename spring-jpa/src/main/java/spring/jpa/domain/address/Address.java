package spring.jpa.domain.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String address1;
    private String address2;
    @Column(columnDefinition = "integer default 0")
    private Integer zipCode;
}
