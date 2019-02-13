package net.sp.referenceapp.domain;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "accounts")
@Audited
public class Account extends Person {

  @Column(name = "username", unique = true)
  @NotBlank
  private String username;

  @Column(name = "password")
  @NotBlank
  private String password;

  @Column(name = "contact")
  @NotBlank
  @Digits(fraction = 0, integer = 10)
  private String contact;

  @Column(name = "ssn", unique = true)
  @NotBlank
  @Digits(fraction = 0, integer = 9)
  private String ssn;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }
}
