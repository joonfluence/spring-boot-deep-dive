package spring.jpa.domain.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager entityManager;

    @Transactional
    public void save(Product product){
        if(product.getId() == null) {
            entityManager.persist(product);
        } else {
            entityManager.merge(product);
        }
    }

    public Product findById(Long id){
        return entityManager.find(Product.class, id);
    }

    public List<Product> findAll(){
        return entityManager.createQuery("select p from Product p", Product.class).getResultList();
    }
}
