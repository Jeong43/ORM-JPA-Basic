package hellojpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Value;

@Embeddable
@Value
public class Address {

  String city;
  String street;
  @Column(name = "ZIPCODE")
  String zipcode;

  public Address() {
    city = null;
    street = null;
    zipcode = null;
  }

  public Address(String city, String street, String zipcode) {
    this.city = city;
    this.street = street;
    this.zipcode = zipcode;
  }
}
