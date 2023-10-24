package hellojpa;

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
      Member member = new Member(200L, "member200");
      em.persist(member);

      em.flush();
      //--> flush를 해도 영속성 컨텍스트(1차 캐시)는 유지되며,
      // 영속성 컨텍스트의 변경내용(쓰기 지연 SQL 저장소에 있는 SQL)을 DB에 동기화한다.
      //--> 트랜잭션이라는 작업 단위가 중요! 커밋 직전에만 동기화하면 됨!

      System.out.println("=================================");
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();
  }
}
