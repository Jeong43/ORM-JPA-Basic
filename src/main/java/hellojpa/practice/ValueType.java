package hellojpa.practice;

import hellojpa.Address;
import hellojpa.AddressEntity;
import hellojpa.Member;
import hellojpa.Period;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ValueType {

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

  public void collection() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {

      Member member = new Member();
      member.setName("member1");

      member.setHomeAddress(new Address("seoul", "wall", "1235"));

      member.getFavoriteFoods().add("치킨");
      member.getFavoriteFoods().add("족발");
      member.getFavoriteFoods().add("피자");

      member.getAddressHistory().add(new AddressEntity("old1", "at", "63563"));
      member.getAddressHistory().add(new AddressEntity("old2", "kt", "66235"));

      em.persist(member);

      em.flush();
      em.clear();

      System.out.println("============START============");
/*      Member findMember = em.find(Member.class, member.getId());

      //주소 변경
      Address oldAddress = findMember.getHomeAddress();
      Address newAddress = new Address("newCity", oldAddress.getStreet(), oldAddress.getStreet());
      findMember.getAddressHistory().add(new AddressEntity(oldAddress));
      findMember.setHomeAddress(newAddress);

      //좋아하는 음식 변경
      findMember.getFavoriteFoods().remove("치킨");
      findMember.getFavoriteFoods().add("한식");

      em.persist(member);*/

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
