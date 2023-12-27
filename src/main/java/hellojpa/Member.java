package hellojpa;

import static javax.persistence.CascadeType.ALL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Member {

  @Id
  @GeneratedValue
  @Column(name = "MEMBER_ID")
  private Long id;
  @Column(name = "USERNAME")
  private String name;

  @Embedded
  private Period workPeriod;
  @Embedded
  private Address homeAddress;
  @Embedded
  @AttributeOverride(name = "city", column = @Column(name = "WORK_CITY"))
  @AttributeOverride(name = "street", column = @Column(name = "WORK_STREET"))
  @AttributeOverride(name = "zipcode", column = @Column(name = "WORK_ZIPCODE"))
  private Address workAddress;

  @ElementCollection
  @CollectionTable(
      name = "FAVORITE_FOOD",
      joinColumns = @JoinColumn(name = "MEMBER_ID")
  )
  @Column(name = "FOOD_NAME")
  private Set<String> favoriteFoods = new HashSet<>();

//  @ElementCollection
//  @CollectionTable(
//      name = "ADDRESS",
//      joinColumns = @JoinColumn(name = "MEMBER_ID")
//  )
//  private List<Address> addressHistory = new ArrayList<>();

  @OneToMany(cascade = ALL, orphanRemoval = true)
  @JoinColumn(name = "MEMBER_ID")
  private List<AddressEntity> addressHistory = new ArrayList<>();
}
