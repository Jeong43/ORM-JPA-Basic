package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Child {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  @ManyToOne
  @JoinColumn(name = "PARENT_ID")
  private Parent parent;
}
