package hellojpa;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseEntity {

  @Column(name = "INSERT_MEMBER")
  private String createBy;
  private LocalDateTime createdDate;
  @Column(name = "UPDATE_MEMBER")
  private String lastModifiedBy;
  private LocalDateTime lastModifiedDate;

}
