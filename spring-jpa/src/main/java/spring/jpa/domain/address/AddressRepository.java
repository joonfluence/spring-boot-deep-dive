package spring.jpa.domain.address;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import spring.jpa.domain.address.Address;

@Repository
public class AddressRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Long save(Address address) {
        entityManager.persist(address);
        return address.getId();
    }

    public Address findById(Long id){
        return entityManager.find(Address.class, id);
    }
}
