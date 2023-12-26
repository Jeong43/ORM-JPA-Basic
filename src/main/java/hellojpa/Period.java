package hellojpa;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import lombok.Value;

@Embeddable
@Value
public class Period {

  LocalDateTime startDate;
  LocalDateTime endDate;

  public Period() {
    startDate = null;
    endDate = null;
  }

  public Period(LocalDateTime startDate, LocalDateTime endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
