package hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      // create
      //Member member = new Member();
      //member.setId(2L);
      //member.setName("helloB");
      //em.persist(member);

      // find
      //Member findMember = em.find(Member.class, 1L);
      //System.out.println("findMember.id = " + findMember.getId());
      //System.out.println("findMember.name = " + findMember.getName());

      // delete
      //Member findMember = em.find(Member.class, 1L);
      //em.remove(findMember);

      // update
      //Member findMember = em.find(Member.class, 1L);
      //findMember.setName("helloJPA");

      // Query
      List<Member> result = em.createQuery("select m from Member as m", Member.class)
          .setFirstResult(1)
          .setMaxResults(10)
          .getResultList();

      for (Member member : result) {
        System.out.println("member.name = " + member.getName());
      }

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();
  }
}
