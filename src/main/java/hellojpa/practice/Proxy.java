package hellojpa.practice;

import hellojpa.Member;
import hellojpa.Team;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.hibernate.Hibernate;

public class Proxy {

  public void reference() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Member member = new Member();
      member.setName("user1");
      member.setCreateBy("kim");
      member.setCreatedDate(LocalDateTime.now());

      em.persist(member);

      em.flush();
      em.clear();

      /*
      System.out.println("=====================================");
      Member findMember = em.find(Member.class, member.getId());
      System.out.println("findMember.Id = " + findMember.getId());
      System.out.println("findMember.Name = " + findMember.getName());
      */

      System.out.println("=====================================");
      Member refMember = em.getReference(Member.class, member.getId());
      System.out.println("refMember = " + refMember.getClass()); //Proxy
      System.out.println("refMember.Id = " + refMember.getId());
      System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

      //System.out.println("refMember.Name = " + refMember.getName());
      //System.out.println("refMember.Name = " + refMember.getName()); //위에서 DB 조회해와서 실제 Entity가 생성되었으므로, 2번째로 호출할 때는 DB 조회 안함
      Hibernate.initialize(refMember); //Proxy 강제 초기화
      System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();
  }

  public void lazyLoading() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Team team = new Team();
      team.setName("TeamA");
      em.persist(team);

      Member member = new Member();
      member.setName("user1");
      member.setCreateBy("kim");
      member.setCreatedDate(LocalDateTime.now());
      member.setTeam(team);

      em.persist(member);

      em.flush();
      em.clear();

      System.out.println("=====================================");
      Member refMember = em.getReference(Member.class, member.getId());
      System.out.println("refMember = " + refMember.getClass()); //Proxy
      System.out.println("refMember.Team = " + refMember.getTeam().getClass()); //Proxy

      System.out.println("=====================================");
      System.out.println("refMember.Team.Name = " + refMember.getTeam().getName()); //초기화
      System.out.println("refMember.Team = " + refMember.getTeam().getClass());

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      System.out.println(e);
    } finally {
      em.close();
    }

    emf.close();
  }

  public void eagerLoading() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Team team = new Team();
      team.setName("TeamA");
      em.persist(team);

      Team team2 = new Team();
      team2.setName("TeamB");
      em.persist(team2);

      Member member = new Member();
      member.setName("user1");
      member.setCreateBy("kim");
      member.setCreatedDate(LocalDateTime.now());
      member.setTeam(team);
      em.persist(member);

      Member member2 = new Member();
      member.setName("user2");
      member.setCreateBy("kim2");
      member.setCreatedDate(LocalDateTime.now());
      member.setTeam(team2);
      em.persist(member2);

      em.flush();
      em.clear();

      System.out.println("=====================================");
      List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
          .getResultList();

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
