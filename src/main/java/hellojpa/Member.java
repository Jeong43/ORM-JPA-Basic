package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Member {

  @Id
  @GeneratedValue
  private Long id;
  @Column(name = "USERNAME")
  private String name;
}
