package hellojpa;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@SequenceGenerator(
    name = "MEMBER_SEQ_GENERATOR",
    sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
    initialValue = 1, allocationSize = 1
)
public class Member {

  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "MEMBER_SEQ_GENERATOR"
  )
  private Long id;
  @Column(name = "name")
  private String username;
  private Integer age;
  @Enumerated(EnumType.STRING) // default 값인 ORDINAL 로 할 경우 심각한 오류 발생할 수 있음
  private RoleType roleType;
  @Temporal(TemporalType.TIMESTAMP) // LocalDate, LocalDateType 일 경우 생략 가능
  private Date createdDate;
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastModifiedDate;
  @Lob
  private String description;
  @Transient //--> 컬럼에 매핑시키기 않음
  private int temp;

  //기본 생성자 필수
  public Member() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public RoleType getRoleType() {
    return roleType;
  }

  public void setRoleType(RoleType roleType) {
    this.roleType = roleType;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
