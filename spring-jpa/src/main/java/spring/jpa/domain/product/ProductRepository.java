package spring.jpa.domain.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Long save(Product product){
        entityManager.persist(product);
        return product.getId();
    }

    public Product findById(Long id){
        return entityManager.find(Product.class, id);
    }
}
