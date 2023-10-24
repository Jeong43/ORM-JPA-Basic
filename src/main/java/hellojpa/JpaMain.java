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
/*
      List<Member> result = em.createQuery("select m from Member as m", Member.class)
          .setFirstResult(1)
          .setMaxResults(10)
          .getResultList();

      for (Member member : result) {
        System.out.println("member.name = " + member.getName());
      }
*/

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
}
