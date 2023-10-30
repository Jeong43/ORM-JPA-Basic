/*
package hellojpa.practice;

import hellojpa.Member;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityMapping {

  public void identity() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Member member = new Member();
      member.setUsername("C");

      System.out.println("=================================");
      em.persist(member);
      System.out.println("member.Id = " + member.getId());
      System.out.println("=================================");
      // GenerationType.IDENTITY
      // -> DB에 insert 해야만 ID 를 알 수 있기 때문에, persist 시점에서 insert 한다. (다른 경우 commit 해야 insert 하는 것과 대조적)

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();
  }

  public void mapping() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      // 저장
      Team team = new Team();
      team.setName("TeamA");
      em.persist(team);

      Member member = new Member();
      member.setName("member1");
      em.persist(member);

      team.addMember(member);

      // 조회
      Member findMember = em.find(Member.class, member.getId()); //1차 캐시
      System.out.println("findMember.Team = " + findMember.getTeam().getName());

      // 양방향 연관관계
      List<Member> members = findMember.getTeam().getMembers();
      System.out.println("=================================");
      for (Member m : members) {
        System.out.println("m.name = " + m.getName());
      }
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
*/
