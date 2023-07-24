package jpabasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");;
    private static EntityManager em = emf.createEntityManager();

    public Main(EntityManagerFactory emf, EntityManager em) {
        this.emf = emf;
        this.em = em;
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("B");
            em.persist(member);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

    public static Member find(Long memberId){
        return em.find(Member.class, memberId);
    }

    public static void save(Member member){
        em.persist(member);
    }

    public static void changeName(String name){
        // Dirty Checking
        Member member = em.find(Member.class, 1L);
    }

}