package hellojpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Team extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "TEAM_ID")
  private Long id;
  private String name;
  @OneToMany(mappedBy = "team")
  private List<Member> members = new ArrayList<Member>();

  //연관관계 체크 메소드
  public void addMember(Member member) {
    member.setTeam(this);
    members.add(member);
  }
}
