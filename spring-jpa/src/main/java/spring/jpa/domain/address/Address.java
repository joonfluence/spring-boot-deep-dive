package spring.jpa.domain.address;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String address1;
    private String address2;
    private int zipCode;
}
