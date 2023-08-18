package spring.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import spring.jpa.domain.order.Order;
import spring.jpa.service.OrderSearch;

import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    @Transactional
    public Long save(spring.jpa.domain.order.Order order){
        em.persist(order);
        return order.getId();
    }

    public spring.jpa.domain.order.Order findById(Long id){
        return em.find(spring.jpa.domain.order.Order.class, id);
    }

    public List<spring.jpa.domain.order.Order> findAll() {
        return em.createQuery("select o from Order o", spring.jpa.domain.order.Order.class).getResultList();
    }

    public List<spring.jpa.domain.order.Order> findAllByString(OrderSearch orderSearch){
        //language=JPAQL
        String jpql = "select o From Order o join o.member m";
        boolean isFirstCondition = true;

        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<spring.jpa.domain.order.Order> query = em.createQuery(jpql, spring.jpa.domain.order.Order.class) .setMaxResults(1000); //최대 1000건
        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query = query.setParameter("name", orderSearch.getMemberName());
        }
        return query.getResultList();
    }

    /**
     * JPA Criteria
     */
    public List<spring.jpa.domain.order.Order> findAllByCriteria(OrderSearch orderSearch){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<spring.jpa.domain.order.Order> cq = cb.createQuery(spring.jpa.domain.order.Order.class);
        Root<spring.jpa.domain.order.Order> o = cq.from(spring.jpa.domain.order.Order.class);
        Join<Object, Object> m = o.join("member", JoinType.INNER);
        List<Predicate> criteria = new ArrayList<>();

        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }

        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name = cb.like(m.<String>get("name"), "%" +  orderSearch.getMemberName() + "%");
            criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<spring.jpa.domain.order.Order> query = em.createQuery(cq).setMaxResults(1000); //최대 1000건
        return query.getResultList();
    }

    public List<spring.jpa.domain.order.Order> findAllWithMemberDelivery() {
        return em.createQuery("select o from Order o" + " join fetch o.member m" + " join fetch o.delivery d", Order.class).getResultList();
    }
}
