package hellojpa.practice;

import hellojpa.Address;
import hellojpa.Member;
import hellojpa.Period;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Embedded {

  public void embedded() {
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
