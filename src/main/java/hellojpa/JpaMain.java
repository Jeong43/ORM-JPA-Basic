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
      //team.getMembers().add(member); //연관관계의 주인이 아니기 때문에 읽기 전용
      em.persist(team);

      Member member = new Member();
      member.setName("member1");
      //member.changeTeam(team); //**
      em.persist(member);

      team.addMember(member);
      //team.getMembers().add(member); //**

      //em.flush();
      //em.clear();

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
