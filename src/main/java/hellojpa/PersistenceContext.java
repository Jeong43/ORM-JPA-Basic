package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceContext {

  void persistence() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      // 비영속
      Member member = new Member();
      member.setId(101L);
      member.setName("HelloJPA");

      // 영속
      System.out.println("=== BEFORE ===");
      em.persist(member);
      System.out.println("=== AFTER ===");

      Member findMember = em.find(Member.class, 101L);
      System.out.println("findMember.Id = " + findMember.getId());
      System.out.println("findMember.Name = " + findMember.getName());
      //--> 영속성 컨텍스트에서 값을 가져오므로, 조회 쿼리를 사용하지 않고 값을 조회한다.

      Member findMember1 = em.find(Member.class, 1L);
      //--> 첫 번째 조회할 때는 DB에서 값을 가져온다. (-> 조회 후 영속성 컨텍스트에 값이 올라감)
      Member findMember2 = em.find(Member.class, 1L);
      //--> 같은 key 로 조회하는 경우, 영속성 컨텍스트(1차 캐시)에서 조회할 수 있으므로 조회 쿼리를 사용하지 않고 값을 조회한다.

      // 영속 엔티티의 동일성 보장
      System.out.println("result = " + (findMember1 == findMember2));

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();
  }

  void persistence2() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Member member1 = new Member(150L, "A");
      Member member2 = new Member(160L, "B");

      em.persist(member1);
      em.persist(member2);

      System.out.println("=================================");
      //--> 커밋 시점에서 쿼리가 실행됨

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();
  }

  void persistence3() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      // 변경감지(Dirty Check)
      // Entity 와 스냅샷을 비교하여 SQL 생성
      Member member = em.find(Member.class, 150L);
      member.setName("ZZZZZ");
      System.out.println("=================================");

      Member member2 = em.find(Member.class, 1L);
      em.remove(member2);
      System.out.println("=================================");

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();
  }

  void persistence4() {
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

  public persistence5() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Member member = em.find(Member.class, 150L);
      member.setName("AAAAA");

      em.detach(member);
      //--> 준영속 상태가 되어 변경사항에서 빠짐

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
