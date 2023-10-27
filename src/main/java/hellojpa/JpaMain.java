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
      // 저장
      Team team = new Team();
      team.setName("TeamA");
      em.persist(team);

      Member member = new Member();
      member.setName("member1");
      member.setTeam(team);
      em.persist(member);

      em.flush();
      em.clear();

      // 조회
      Member findMember = em.find(Member.class, member.getId());
      System.out.println("findMember.Team = " + findMember.getTeam().getName());

      // 양방향 연관관계
      List<Member> members = findMember.getTeam().getMembers();
      for (Member m : members) {
        System.out.println("m.name = " + m.getName());
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
