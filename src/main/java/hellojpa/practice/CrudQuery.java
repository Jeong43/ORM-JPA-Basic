//package hellojpa;
//
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
//public class CrudQuery {
//
//  void crud() {
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//    EntityManager em = emf.createEntityManager();
//
//    EntityTransaction tx = em.getTransaction();
//    tx.begin();
//
//    try {
//      // create
//      Member member = new Member();
//      member.setId(2L);
//      member.setName("helloB");
//      em.persist(member);
//
//      // find
//      Member findMember = em.find(Member.class, 1L);
//      System.out.println("findMember.id = " + findMember.getId());
//      System.out.println("findMember.name = " + findMember.getName());
//
//      // delete
//      Member findMember2 = em.find(Member.class, 1L);
//      em.remove(findMember2);
//
//      // update
//      Member findMember3 = em.find(Member.class, 1L);
//      findMember3.setName("helloJPA");
//
//      tx.commit();
//    } catch (Exception e) {
//      tx.rollback();
//    } finally {
//      em.close();
//    }
//
//    emf.close();
//  }
//
//  void query() {
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//    EntityManager em = emf.createEntityManager();
//
//    EntityTransaction tx = em.getTransaction();
//    tx.begin();
//
//    try {
//      // Query
//      List<Member> result = em.createQuery("select m from Member as m", Member.class)
//          .setFirstResult(1)
//          .setMaxResults(10)
//          .getResultList();
//
//      for (Member member : result) {
//        System.out.println("member.name = " + member.getName());
//      }
//
//      tx.commit();
//    } catch (Exception e) {
//      tx.rollback();
//    } finally {
//      em.close();
//    }
//
//    emf.close();
//  }
//}
