package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("A")
public class Album extends Item {

  private String artist;
}
