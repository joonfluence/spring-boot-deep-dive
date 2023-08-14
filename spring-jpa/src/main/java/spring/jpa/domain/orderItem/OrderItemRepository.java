package spring.jpa.domain.orderItem;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemRepository {
    @PersistenceContext
    EntityManager entityManager;
}
