package spring.jpa.domain.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Long save(Member member) {
        entityManager.persist(member);
        return member.getId();
    }

    public Member findById(Long id) {
        return entityManager.find(Member.class, id);
    }
}
