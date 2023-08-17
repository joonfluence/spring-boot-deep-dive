package spring.jpa.domain.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.jpa.domain.product.Product;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager entityManager;

    @Transactional
    public Long save(Order order){
        entityManager.persist(order);
        return order.getId();
    }

    public Order findById(Long id){
        return entityManager.find(Order.class, id);
    }

    public List<Order> findAll(){
        return entityManager.createQuery("select o from Order o", Order.class).getResultList();
    }
}
