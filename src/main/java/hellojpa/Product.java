package hellojpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Product {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  //@ManyToMany(mappedBy = "MEMBER_PRODUCT")
  //private List<Member> members = new ArrayList<Member>();
  @OneToMany(mappedBy = "product")
  private List<MemberProduct> memberProducts = new ArrayList<MemberProduct>();
}
