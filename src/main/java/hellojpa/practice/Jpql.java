package hellojpa.practice;

import hellojpa.Address;
import hellojpa.Member;
import hellojpa.Period;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Jpql {

  public void basic() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Member member = new Member();
      member.setName("name1");
      member.setHomeAddress(new Address("seoul", "wall", "1235"));
      member.setWorkPeriod(new Period());
      em.persist(member);

      //flush -> commit, query

      System.out.println("==================================================");
      List<Member> memberList = em.createQuery(
          "select m from Member m", Member.class
      ).getResultList();

      for (Member m : memberList) {
        System.out.println("member = " + member);
      }

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      System.out.println(e);
    } finally {
      em.close();
    }
    emf.close();
  }

}
