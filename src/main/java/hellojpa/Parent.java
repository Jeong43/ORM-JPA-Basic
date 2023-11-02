package hellojpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Parent {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL) //하나의 부모가 자식들을 관리할 때 의미가 있음
  private List<Child> childList = new ArrayList<>();

  public void addChild(Child child) {
    childList.add(child);
    child.setParent(this);
  }
}
