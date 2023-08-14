package spring.jpa.domain.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Long save(Order order){
        entityManager.persist(order);
        return order.getId();
    }

    public Order findById(Long id){
        return entityManager.find(Order.class, id);
    }
}
