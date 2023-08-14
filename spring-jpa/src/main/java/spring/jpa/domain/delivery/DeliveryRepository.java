package spring.jpa.domain.delivery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Long save(Delivery delivery) {
        entityManager.persist(delivery);
        return delivery.getId();
    }

    public Delivery findById(Long id){
        return entityManager.find(Delivery.class, id);
    }
}
